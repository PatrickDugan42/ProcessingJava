import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.awt.*;

public class Polygon {
    PShape s;
    // The location where we will draw the shape
    float x, y;


    Polygon(PShape s, float x, float y) {
        this.x = x;
        this.y = y;
        this.s = s;
    }

    Polygon(PShape s, PVector v) {
        this.x = v.x;
        this.y = v.y;
        this.s = s;
    }

    // Draw the object
    public void display(PApplet a) {
        a.pushMatrix();
        a.translate(x, y);
        a.shape(s);
        a.popMatrix();
    }

    public void display(PApplet a, Color c) {
        a.pushMatrix();
        a.fill(c.getRGB(), 128);
        a.translate(x, y);
        a.shape(s);
        a.popMatrix();
    }
}
