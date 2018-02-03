package examples;

import processing.core.PApplet;
import processing.core.PVector;

public class Test3D extends PApplet {
    PVector rotation = new PVector();

    @Override
    public void settings() {
        size (500, 500, P3D);
    }

    @Override
    public void setup() {
        colorMode(RGB, 1);
        strokeWeight(2);
    }

    @Override
    public void draw() {
        background(0.1f);

        pushMatrix();
        camera(0, 0, 300, 0, 0, 0, 0, -1, 0);
        scale(-1, 1, 1);
        rotation.y += (mouseX - pmouseX) * 0.01;
        rotation.x += (mouseY - pmouseY) * 0.01;
        rotateY(rotation.y);
        rotateX(rotation.x);

        //translate(width/2, height/2);
        //scale(1, -1, 1);

        drawTriangle();
        drawAxis();
        popMatrix();

        noStroke();
        fill(1);
        text("X " + rotation.x, 10, 10, 200, 20);
        text("Y " + rotation.y, 10, 20, 200, 20);
    }

    private void drawTriangle()
    {
        noStroke();
        beginShape(TRIANGLES);
        vertex(-10, 0, 0);
        vertex(0, 30, 0);
        vertex(10, 0, 0);
        endShape();
    }

    private void drawAxis()
    {
        float a = 100;

        // X axis points right
        stroke(1, 0, 0);
        line(a, 0, 0, 0, 0, 0);

        // Y axis points up
        stroke(0, 1, 0);
        line(0, 0, 0, 0, a, 0);

        // Z axis points forewards (towards viewer)
        stroke(0, 0, 1);
        line(0, 0, 0, 0, 0, a);
    }

    public static void main(String[] args) {
        PApplet.main("examples.Test3D");
    }
}
