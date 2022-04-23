import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MyPApplet extends PApplet{

    int sqr = 10;
    int scale = 1;
    PShape box;
    PShape star;
    int cntWidth;
    int cntHeight;
    ArrayList<Polygon> shapes;
    SearchAnimation anim;


    @Override
    public void settings() {
        size(1000, 1000, P2D);
    }

    @Override
    public void setup(){

        cntHeight = height / sqr;
        cntWidth = width / sqr;

        box = createShape();
        box.beginShape();
        box.noStroke();
        box.fill(255, 25);
        box.vertex(1 * scale,1 * scale);
        box.vertex(sqr -1,1 *scale);
        box.vertex(sqr -1,sqr -1);
        box.vertex(1 * scale,sqr -1);
        box.endShape(CLOSE);


        String[] colors = {"#11260b", "#1d5010", "#356614", "#68991f"};
        anim = new SearchAnimation(width, height, sqr, scale, colors, this);
        System.out.format("The target is %s and the start is %s\n",  anim.target.toString(),anim.start.toString());
        shapes= new ArrayList<>();
        for(int i = 0; i < cntHeight; i++){
            for(int k = 0; k < cntWidth; k++){
                shapes.add(new Polygon(box, k * sqr, i * sqr));
            }
        }

    }

    @Override
    public void draw() {
        Color background = Color.decode("#040d04");
        background(background.getRGB());

        for(Polygon p : shapes){
            p.display(this);
        }
        anim.step();
        anim.display(this);
        //saveFrame("data/line-#####.tif");
    }

}
