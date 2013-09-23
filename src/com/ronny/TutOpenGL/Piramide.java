package com.ronny.TutOpenGL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Piramide {
//Nuestros Vertices
    private float vertices[] = {
            -1f, -1f, 0f,
            1f, -1f, 0f,
            0f, 0.8f, 0f,
            0f,  0f, 2f
    };

    //Definimos los vértices de nuestra pirámide en (x,y,z)

    // La forma como vamos a conectarlos

    private short caras[] = {
            0, 1, 2,
            0, 2, 3,
            0, 1, 3,
            1, 2, 3
        //2, 3, 4,
        //3, 0, 4

    };

    //Definimos que vértices vamos a unir formando las caras triangulares

    private float colors[] = {
            1f, 0f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f
    };

    //Definimos los colores que vamos a utilizar en cada vertice

    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;
    private ShortBuffer indexBuffer;

    public Piramide() {
        // un float es de 4 bytes, por lo que multiplicaremos el numero de vertices por 4
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // un short es de 2 bytes, por lo que multiplicaremos el numero de vertices por 2
        ByteBuffer ibb = ByteBuffer.allocateDirect(caras.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(caras);
        indexBuffer.position(0);
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    public void draw(GL10 gl) {
    // Contra las agujas del reloj
        gl.glFrontFace(GL10.GL_CCW);
        // Habilitar el sacrificio de caras a ocultar
        gl.glEnable(GL10.GL_CULL_FACE);

        // Aca se indica que cara se sacrificara, en este caso, la de atras
        gl.glCullFace(GL10.GL_BACK);

        // Habilitar el buffer de vertices para la escritura y cuales se usaran para el renderizado
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // Especifica la localizacion y el formato de los datos de un array de vertices a utilizar para el renderizado
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        // Habilita el buffer para el color del grafico
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        // Señala donde se encuentra el buffer del color
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        //Dibujamos las superficies
        gl.glDrawElements(GL10.GL_TRIANGLES, caras.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Desactiva el buffer de los vertices
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        //Desactiva la caracteristica de sacrificios de las caras
        gl.glDisable(GL10.GL_CULL_FACE);

        // Desahilita el buffer del color
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

    }
}
