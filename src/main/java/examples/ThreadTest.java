package examples;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class ThreadTest extends PApplet {
    private MyThread myThread;

    @Override
    public void settings() {
        size(200, 200);

        myThread = new MyThread();
        myThread.start();
    }

    @Override
    public void draw() {
        background(0);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        super.mouseClicked(event);
        myThread.interrupt();
    }

    class MyThread extends Thread {

        public void run() {
            while(true) {
                float r = random(255);
                float g = random(255);
                float b = random(255);

                color(r, g, b);

                delay(1000);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("examples.ThreadTest");
    }
}
