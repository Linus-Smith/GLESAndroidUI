package com.linus.glesandroidui;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.linus.glesandroiduilib.GLES.glrenderer.GLCanvas;
import com.linus.glesandroiduilib.GLES.glrenderer.GLPaint;
import com.linus.glesandroiduilib.GLES.ui.GLRootView;
import com.linus.glesandroiduilib.GLES.ui.GLView;

import processing.android.PFragment;


public class MainActivity extends AppCompatActivity {

    private PFragment mPFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPFragment = new PFragment();
        View view = findViewById( R.id.gl_root);
        Plexu_5 mPlexu_5 = new Plexu_5();
        mPFragment.setSketch(mPlexu_5 ,view , getSupportFragmentManager());
    }
}
