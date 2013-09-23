package com.ronny.TutOpenGL;

/**
 * Created with IntelliJ IDEA.
 * User: usuario
 * Date: 23/09/13
 * Time: 08:48 AM
 * To change this template use File | Settings | File Templates.
 */

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer{
    Piramide piramide = new Piramide();

    @Override

    public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
    // Establece el color de fondo (r,g,b,a)
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
    // Habilita el sombreado suave
        gl.glShadeModel(GL10.GL_SMOOTH);
    // Configura el buffer de profundidad
        gl.glClearDepthf(1.0f);
    // Habilita el testeo de profundidad
        gl.glEnable(GL10.GL_DEPTH_TEST);
    // El tipo de testeo de profundidad a hacer
        gl.glDepthFunc(GL10.GL_LEQUAL);
    // Calculo de perspectivas
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    @Override

    public void onDrawFrame(GL10 gl) {
        // Limpia la pantalla y el buffer de profundidad
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // Reemplaza la matriz actual con la matriz identidad
        gl.glLoadIdentity();
        // Traslada 4 unidades en el eje Z
        gl.glTranslatef(0, 0, -4);
        // Dibuja nuestra piramide
        piramide.draw(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Establece el puerto de vista actual  al nuevo tamaño
        gl.glViewport(0, 0, width, height);
        // Selecciona la matriz de proyeccion
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reinicia la matriz de proyeccion
        gl.glLoadIdentity();
        // Calcula la proporcion del aspecto de la ventana
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,100.0f);
        // Selecciona la matriz de la vista del modelo
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reinicia la matriz de la vista del modelo
        gl.glLoadIdentity();
    }

}



