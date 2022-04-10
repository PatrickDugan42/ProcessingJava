import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PShape;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class MyPApplet extends PApplet{


    float x,y,z;
    float r;
    float sphereX, sphereY, sphereZ;
    float sphere2X, sphere2Y, sphere2Z;
    PVector space;
    PVector p1;
    @Override
    public void settings() {

        size(1000, 1000, P3D);

    }

    @Override
    public void setup(){
        x=0;
        y=1;
        z=0;
        r=1;
        space = new PVector();
        p1 = new PVector(0,0,0);
        sphereX = 0;
        sphereY = 0;
        sphereZ = 0;

        sphere2X = 0;
        sphere2Y = 0;
        sphere2Z = 0;
    }

    @Override
    public void draw() {
        background(0);


       // camera(space.x, space.y, space.z, p1.x, p1.y, p1.z, 0,1,0);


        sphereX = width/2;
        sphereY = height/2;
        sphereZ = 0;


        p1 = new PVector(sphereX, sphereY, sphereZ);
        
        pushMatrix();
        translate(width/2, height/2);




        noFill();
        stroke(255);

        line(0,0,0, 100,100,100);
        stroke(255,0,0);
        line(0,0,0,0,0, 600);
        stroke(0,255,0);
        line(0,0,0,600,0, 0);
        stroke(0,0,255);
        line(0,0,0,0,600, 0);

        pushMatrix();


        rotateX(radians(r));

        sphere(50);
        popMatrix();



        pushMatrix();
            fill(0,155,0);
            translate(100,100,100);
            sphere(25);

            popMatrix();

        popMatrix();

        sphere2X = width/2 + 100;
        sphere2Y = height/2 + 100;
        sphere2Z = 100;

        PVector p2 = new PVector(sphere2X, sphere2Y, sphere2Z);

        PVector v = PVector.sub(p1, p2);
        println("V is " + v);

        float mag = 1;
        space = PVector.add(p1, PVector.mult(v, -4));
        println("Point is : " +  space);


        //camera(space.x, space.y, space.z, p1.x, p1.y, p1.z, 0,1,0);
        r+=1;
    }

}
