package LSystem;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PShape;
import processing.core.PVector;

public class Actor {

    public PVector o; //origin
    public PVector r; //x y z rotation in vector for the objects orientation
    public PVector[] HLU;  //0: Heading 1: Left 2: Up
    public PShape s; //pshape
    PApplet c; //applet

    public Actor(PApplet applet){
        o = new PVector(0,0,0);
        r = new PVector(0,0,0);

        HLU = new PVector[3];
        HLU[0] = new PVector(0,1,0);
        HLU[1] = new PVector(1,0,0);
        HLU[2] = new PVector(0,0,1);

        c = applet;
    }

    public Actor(PApplet applet, PShape shape){
        o = new PVector(0,0,0);
        r = new PVector(0,0,0);

        HLU = new PVector[3];
        HLU[0] = new PVector(0,1,0);
        HLU[1] = new PVector(1,0,0);
        HLU[2] = new PVector(0,0,1);

        s = shape;
        c = applet;
    }

    public Actor(PApplet applet, PShape shape, PVector origin, PVector rotation){
        o = origin;
        r = rotation;
        s = shape;
        c = applet;

        HLU = new PVector[3];
        HLU[0] = new PVector(0,1,0);
        HLU[1] = new PVector(1,0,0);
        HLU[2] = new PVector(0,0,1);
    }

    public void draw(){

        float rX, rY, rZ; //roation last this frame;

        //c.rotateX(PApplet.radians(r.x));

        //TODO: print H X L = U to see if that's true for your setup
        c.pushMatrix();  //this push to set the direction through rotation
        c.rotate(PApplet.radians(r.z));
        c.rotateX(PApplet.radians(r.x));


        c.pushMatrix(); //Orientation and move forward
        c.translate(o.x, o.y, o.z);
        c.rotateY(PApplet.radians(r.y));
        c.rotate(PApplet.radians(180f)); //orientation down for this pshape
        c.scale(5);
        c.shape(s);
        c.popMatrix();
        c.popMatrix();

    }

    public void forward(int units){
        o = o.add(PVector.mult(HLU[0], units));
    }

    public void rotate(float x, float y, float z){
        r.x = r.x + x;
        r.y = r.y + y;
        r.z = r.z + z;
    }
    public PVector getO() {
        return o;
    }

    public void setO(PVector o) {
        this.o = o;
    }

    public PShape getS() {
        return s;
    }

    public void setS(PShape s) {
        this.s = s;
    }

}
