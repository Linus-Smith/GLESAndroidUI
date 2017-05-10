package com.linus.glesandroidui;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * Created by Linus on 2017/4/24.
 */

public class Plexu_5 extends PApplet {
    int num = 3000;
    int particleLimit = 240;
    int font_size = 1100;
    int countLimit = 6;
    int disLimit = font_size / 10;
    int aroundRadius_min = font_size / 150;
    int aroundRadius_max = font_size / 30;
    int line_weight = 2;
    float randomH;
    //color line_Color = color(50, 55, 100);
//color bg = color(15,14,19);
    int bg = color(8, 8, 8);

    String inputText = "W";
    float theta;
    ArrayList<Plexus> plexuses;
    ArrayList<Plexus> plexusesPre;
    Plexus plexus;
    Plexus otherPlexus;
    PGraphics PGra;
    PFont font;
    float dis;
    int countP;
    int coutParticle;

    boolean is_reset = false;

    boolean is_save = false;
    int savecount = 30 * 10;


    @Override
    public void settings() {
        //It'll be empty. Will be overridden by user's sketch class.
        size(1080, 1920, OPENGL);
    }


    @Override
  public  void setup() {

        blendMode(ADD);
        colorMode(HSB, 360);
        PGra = createGraphics(width ,height, OPENGL);
      //  font = createFont("Zeusis_PFDinTextCondPro-Regular.ttf", 32);
        font = createDefaultFont(32);
        plexuses = new ArrayList();
        plexusesPre = new ArrayList();
        reset();
        frameRate(30);
        is_reset = true;
    }

    @Override
   public void draw() {
        timer();
        background(bg);
        //timer();
        theta += 0.03;   //move speed
        for (int i = 0; i < plexuses.size(); i++) {
            //plexus = (Plexus) plexuses.get(i);
            plexuses.get(i).display();
            plexuses.get(i).arrive();
            plexuses.get(i).update();
        }


        if (is_save) {
            if (frameCount < savecount) {
                saveFrame("ex/image-###.png");
                //println(frameCount);
            }
        }


    }


   public  void reset() {
        plexuses.clear();
        coutParticle = 0;
        randomH = random(-200, 20);
        // make a mask;
        PGra.beginDraw();
        PGra.noStroke();
        PGra.background(0);
        // draw a white font on the mask
        PGra.fill(255);
        PGra.textFont(font, font_size);              //font size
        PGra.textAlign(CENTER, CENTER);
        PGra.text(inputText, width / 2, height / 2 - 40);  //font loc
        PGra.endDraw();
        PGra.loadPixels();

        //while (plexuses.size() < particleLimit) {
        for (int i = 0; i < num; i++) {
            // sprinkle some point
            int x = (int) random(width);
            int y = (int) random(height);
            int c = PGra.pixels[x + y * width];

            if (brightness(c) > 0 && coutParticle < particleLimit) {
                // get some point in the mask where is white
                PVector anchor = new PVector(x, y);
                PVector loc = new PVector(anchor.x, anchor.y);
                float radius = random(aroundRadius_min, aroundRadius_max);
                float offset = random(TWO_PI);
                int turnAround = random(1) > 0.5 ? 1 : -1;

                if (is_reset) {
                    Plexus myplexus = new Plexus(anchor, plexusesPre.get(coutParticle).loc, plexusesPre.get(coutParticle).radius, plexusesPre.get(coutParticle).offset, plexusesPre.get(coutParticle).turnAround, plexusesPre.get(coutParticle).anchor);
                    plexuses.add(myplexus);
                } else {
                    Plexus myplexus = new Plexus(anchor, loc, radius, offset, turnAround, anchor);
                    plexuses.add(myplexus);
                }
                coutParticle++;
            }
        }

        //plexusesPre.clear();
        while (plexusesPre.size() > particleLimit) {
            plexusesPre.remove(plexusesPre.size() - 1);
        }
        for (int i = 0; i < coutParticle; i++) {
            plexusesPre.add(plexuses.get(i));
        }
    }

    // change text
    @Override
  public  void keyPressed() {
        if (key != CODED) {
            inputText = str(key);
            reset();
        }
    }


    // the Countdown function
    void timer() {
        if (frameCount == 30) {
            inputText = "4";
            reset();
        }
        if (frameCount == 60) {
            inputText = "3";
            reset();
        }
        if (frameCount == 90) {
            inputText = "2";
            reset();
        }
        if (frameCount == 120) {
            inputText = "1";
            reset();
        }
        if (frameCount == 150) {
            inputText = "0";
            reset();
        }

        if (frameCount > 180) {
            if (frameCount % 2 == 1) {
                inputText = str(floor(random(9)));
                reset();
            }
        }
    }

    class Plexus {
        PVector anchor = new PVector(random(width), random(height));
        PVector loc, anchorNew;
        float radius, offset, lineAllpha;
        int turnAround, cout;
        ArrayList<Plexus> neighbours;
        PVector preAnchor;

        PVector desired;
        PVector acc = new PVector(0,0);
        PVector vel = new PVector(0,0);
        PVector steer = new PVector(0,0);
        float  distance, speed;
        float maxforce = 10.2f;
        float maxspeed = 400f;

        Plexus(PVector _anchor, PVector _loc, float _radius, float _offset, int _turnAround, PVector anchorPre) {
            anchorNew = _anchor;
            loc = _loc;
            radius = _radius;
            offset = _offset;
            turnAround = _turnAround;
            anchor = anchorPre;
        }

        void update() {
            vel.add(acc);
            vel.limit(maxspeed);
            anchor.add(vel);
            loc.x = anchor.x + cos(theta*turnAround + offset) * radius;
            loc.y = anchor.y + sin(theta*turnAround + offset) * radius;
            acc.mult(0);
        }

        void arrive() {
            desired = PVector.sub(anchorNew, anchor);
            distance = desired.mag();
            desired.normalize();
            if (distance < 1200) {
                speed = map(distance, 0, 1200, 0, maxspeed);
                desired.mult(speed);
            } else {
                desired.mult(maxspeed);
            }

            steer = desired.sub(vel);
            steer.limit(maxforce);
            acc.add(steer);
        }



        void getLine() {
            countP = 0;
            neighbours = new ArrayList<Plexus>();
            for (int i = 0; i < plexuses.size(); i++) {
                otherPlexus = (Plexus) plexuses.get(i);
                dis = loc.dist(otherPlexus.loc);
                if (dis > 2 && dis < disLimit && countP < countLimit) {
                    // make a line
                    strokeWeight(line_weight);

                    //stroke(randomH + 210+countP*countP*4, 360, 270, 80);                 // line color
                    stroke(210+countP*countP*4, 360, 270, 80);
                    //stroke(212,360,270,70);

                    line(loc.x, loc.y, otherPlexus.loc.x, otherPlexus.loc.y);
                    countP++;
                }
            }
        }

        void display() {
            //noStroke();
            //fill(255);
            //ellipse(loc.x, loc.y, 2, 2);
            getLine();
        }
    }
}
