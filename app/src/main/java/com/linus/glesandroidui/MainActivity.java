package com.linus.glesandroidui;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.linus.glesandroiduilib.GLES.glrenderer.GLCanvas;
import com.linus.glesandroiduilib.GLES.glrenderer.GLPaint;
import com.linus.glesandroiduilib.GLES.ui.GLRootView;
import com.linus.glesandroiduilib.GLES.ui.GLView;


public class MainActivity extends AppCompatActivity {

    private GLRootView mGlRootView;

    private GLView mGLView = new GLView() {
        @Override
        protected void render(GLCanvas canvas) {
            super.render(canvas);
            GLPaint nGlPaint = new GLPaint();
            nGlPaint.setColor(Color.RED);
            canvas.drawLine(0, 0,  500, 500 , nGlPaint);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGlRootView = (GLRootView) findViewById(R.id.gl_root);

        mGlRootView.setContentPane(mGLView);
    }
}
