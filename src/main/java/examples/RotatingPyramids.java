package examples;

import processing.core.PApplet;

public class RotatingPyramids extends PApplet {
    // Example 14-10: Pyramid

    float theta = (float) 0.0;

    @Override
    public void settings() {
        size(480, 240, P3D);
    }

    @Override
    public void setup() {
//        size(480, 240, P3D);
    }

    @Override
    public void draw() {
        background(255);
        theta += 0.01;

        translate(width / 2, height / 2, 0);
        rotateX(theta);
        rotateY(theta);
        drawPyramid(50);

        // translate the scene again
        translate(100, 100, 20);
        // call the pyramid drawing function
        drawPyramid(10);
    }

    private void drawPyramid(int t) {
        stroke(0);

        // this pyramid has 4 sides, each drawn as a separate triangle
        // each side has 3 vertices, making up a triangle shape
        // the parameter " t " determines the size of the pyramid
        beginShape(TRIANGLES);

        fill(150, 0, 0, 127);
        vertex(-t, -t, -t);
        vertex(t, -t, -t);
        vertex(0, 0, t);

        fill(0, 150, 0, 127);
        vertex(t, -t, -t);
        vertex(t, t, -t);
        vertex(0, 0, t);

        fill(0, 0, 150, 127);
        vertex(t, t, -t);
        vertex(-t, t, -t);
        vertex(0, 0, t);

        fill(150, 0, 150, 127);
        vertex(-t, t, -t);
        vertex(-t, -t, -t);
        vertex(0, 0, t);

        endShape();
    }

    public static void main(String[] passedArgs) {
        PApplet.main("examples.RotatingPyramids");
    }
}
