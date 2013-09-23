package com.ronny.TutOpenGL;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
public class TutOpenGL extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView surface = new GLSurfaceView(this);
        MyRenderer renderer = new MyRenderer();
        surface.setRenderer(renderer);
        setContentView(surface);
    }

}


