import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PShape;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class FunPApplet extends PApplet{


    ArrayList<PShape> shapes;

    float r;
    float t;
    int cloud = 40;


    PVector[][][] startLoc;
    PVector[][][] drawing;

    @Override
    public void settings() {
        size(320, 240, P3D);
    }

    @Override
    public void setup(){
        r=0;
        t=0;

        startLoc = new PVector[cloud][cloud][cloud];
        drawing = new PVector[cloud][cloud][cloud];

        for(int x = 0; x < cloud; x+=1) {
            for (int y = 0; y < cloud; y += 1) {
                for (int z =0; z < cloud; z += 1) {
                    float off = cloud/2;
                    startLoc[x][y][z] = new PVector(x -off, y- off , z- off);
                }
            }
        }
    }

    @Override
    public void draw() {
        background(0);




        float forcex = abs(map(mouseX, 0, width,-100,100));
        //println(sin(r));
        //println(sin(radians(r)));

        float rad = 10;
        float cirX = rad * cos(radians(r *5f));
        float cirY = rad * sin(radians(r *5f));


        //float forcex = map(sin(t), -1,1, -20, 20);
        println("force: ", forcex);
        println ("sin of r: ", sin(radians(r)));
        for(int x = 0; x < cloud; x+=1) {
            for (int y = 0; y < cloud; y += 1) {
                for (int z =0; z < cloud; z += 1) {
                    drawing[x][y][z] = new PVector(startLoc[x][y][z].x,startLoc[x][y][z].y,startLoc[x][y][z].z);
                    PVector loc = pullObject(new PVector(cirX  ,cirY, -5), forcex, new PVector(drawing[x][y][z].x,drawing[x][y][z].y,drawing[x][y][z].z));
                    loc = pullObject(new PVector(cirX , 0,0), sin(radians(r))*15f, loc);
                    loc = pullObject(new PVector(0 , 0,cirY), cos(radians(r))*15f, loc);

                    // PVector loc = pullObject(new PVector(0,0, 0), forcex, new PVector(drawing[x][y][z].x,drawing[x][y][z].y,drawing[x][y][z].z));
                    //loc = pullObject(new PVector(-10,forcex,0), forcex, loc);
                    //loc = pullObject(new PVector(forcex,0,forcex), forcex, loc);
                    //loc = pullObject(new PVector(0,forcex,10), forcex, loc);
                    drawing[x][y][z] = loc;
                }
            }
        }

        //drawing = startLoc.clone();


        translate(width/2, height/2);

        fill(0,0,255);
        pushMatrix();
        rotateY(radians(r));
        rotate(radians(r));
        rotateX(radians(r));
        stroke(cos(radians(t)) *255,sin(radians(r))*255,r%255);
        fill(255 ,0,0);
        drawPointCloud();
        popMatrix();
        pushMatrix();
        translate(cirX *10 , cirY *10);
       // sphere(10);

        popMatrix();

        r+=1;
        t+=.015;
        saveFrame("data/forces-#####.tif");


    }

    public void drawPointCloud(){
        for (PVector[][] p2D: drawing) {
            for(PVector[] p1: p2D){
                for(PVector p: p1){
                    pushMatrix();
//                    translate(p.x*20 , p.y*20, p.z*20);
//                    point(0,0,0);
//
                    point(p.x*10, p.y*10, p.z*10);
                    popMatrix();
                }
            }

        }
    }


    /**
     * the idea is to apply a force to the object that will almost reach the object at it's max.  That
     * doesn't exclude other pullers from pulling something closer to different objects origin than that object
     * allows itslef to pull
     * @param puller
     * @param pullerForce
     * @param object
     * @return
     */
    public PVector pullObject(PVector puller, float pullerForce, PVector object){

//        PVector forceDirection = PVector.sub(puller, object);
//        println(forceDirection.mag());
//        float force = (pullerForce*1*objectMass) / pow(forceDirection.mag(), 2);
//        PVector forNormal = forceDirection.copy().normalize();
//        println("Object {} and the normal forceDir {} and the normalized = {}", object, forceDirection, forNormal, forceDirection.mag(), forNormal.mag());
//        //if(PVector.add(object, forceDirection.normalize().mult(force)))
//        if(force > forNormal.mag()){
//            return puller.copy();
//        }
//        return object.add(forceDirection.normalize().mult(force));

        PVector distance = PVector.sub(puller, object);
        float maxMag = distance.mag();

        if(pullerForce > maxMag) return puller.copy().add(distance.normalize().mult(6));

        return PVector.add(object, distance.normalize().mult(pullerForce));


    }


}
