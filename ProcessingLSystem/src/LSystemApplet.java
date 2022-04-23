import LSystem.Actor;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class LSystemApplet extends PApplet{


    PShape group;

    Actor a;

    float rX;
    float rY;
    float rZ;

    @Override
    public void settings() {
        size(1000, 1000, P3D);
    }

    @Override
    public void setup(){
        rX = 1;
        rY=0;
        rZ=0;
        group = createShape(GROUP);

        PShape p;
        p = createShape();
        p.beginShape();
        p.noFill();
        p.strokeWeight(3);
        p.stroke(255,0,0);
        p.vertex(0, -1);
        p.vertex(2, 7);
        p.vertex(0, 5);
        p.vertex(-2,7);
        p.endShape(CLOSE);

        PShape o;
        o = createShape();
        o.beginShape();
        o.noFill();
        o.strokeWeight(3);
        o.stroke(0,255,0);
        o.vertex(0, -1);
        o.vertex(0, 7, 2);
        o.vertex(0, 5);
        o.vertex(0,7, -2);
        o.endShape(CLOSE);

        translate(0,-1);
        PShape circ = createShape(SPHERE, 1f);
        circ.setFill(color(0,0,255));
        circ.setStroke(false);


        group.addChild(p);
        group.addChild(o);
        group.addChild(circ);



        a = new Actor(this, group);

        background(0);

    }

    @Override
    public void draw() {

        background(0);

        translate(width/2, height/2);

        stroke(225,0,0);
        PVector rot = new PVector(rX, rY, rX);

        //rotateY(rX);
        a.forward(1);
        a.rotate(0f,0,.3f);
        a.draw();


        //rotateX(mouseX);
        //rotateY(mouseY);
       // rotateZ(mouseX);
        //scale(25.0f);
        //shape(group);
        //PShape rect = createShape(RECT,0,0,100,100);
        //fill(255,0,0);
        //shape(rect);
    }

}
