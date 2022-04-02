import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.awt.*;
import java.util.*;

public class SearchAnimation {

    private int height;
    private int width;
    private int sqr;
    private int scale;

    public PVector start;
    public PVector target;
    public LinkedList<PVector> stepOrder;
    int stepCnt;

    ArrayList<Color> colors;
    ArrayList<Polygon> polygons;
    Polygon startPoly;
    Polygon endPoly;

    PShape box;

    public SearchAnimation(int width, int height, int sqr, int scale, String[] colors, PApplet pa){

        this.height = height;
        this.width = width;
        this.sqr = sqr;
        this.scale = scale;
        stepCnt = -1;
        SearchService searchService = new SearchService();
        stepOrder = new LinkedList<>();
        int sqrScl = sqr * scale;
        //random start and target
        Random random = new Random();
        int[] x = random.ints(2, 0, width / sqrScl).toArray();
        int[] y = random.ints(2, 0, height / sqrScl).toArray();
        start = new PVector(x[0], y[0]);
        target = new PVector(x[1], y[1]);

        Stack<PVector> path = searchService.twoDimBreadthFirstSearch(width / sqrScl, height / sqrScl, start, target);
        while (!path.isEmpty()) stepOrder.add(path.pop());

        this.colors = new ArrayList<>();
        for(String c : colors){
            this.colors.add(Color.decode(c));
        }

        box = pa.createShape();
        box.beginShape();
        box.noStroke();
        box.fill(255, 25);
        box.vertex(1 * scale,1 * scale);
        box.vertex(sqr -1,1 *scale);
        box.vertex(sqr -1,sqr -1);
        box.vertex(1 * scale,sqr -1);
        box.endShape(pa.CLOSE);
        box.disableStyle();
        System.out.format("stepOrder %s\n" ,stepOrder.toString());
        polygons = new ArrayList<>();

        for(int i = stepOrder.size() - 1; i >=0; i--){
            polygons.add(new Polygon(box, stepOrder.get(i).x * sqrScl, stepOrder.get(i).y * sqrScl));
        }

        startPoly = new Polygon(box, start.x * sqrScl, start.y * sqrScl);
        endPoly = new Polygon(box, target.x * sqrScl, target.y * sqrScl);

    }



    //increment by one step
    public void step(){

        if(stepOrder.size() > stepCnt + 1) stepCnt+=1;
    }

    public void setState(int step){
        stepCnt = step;

    }
    public void display(PApplet pa){

        //draw our start and target
        startPoly.display(pa, Color.WHITE);
        endPoly.display(pa, Color.BLACK);

//        for(int i = stepCnt; i >=0;){
//            for(int c = 0; c < colors.size(); c++){
//                for(int k = 0; k <= c; k++){
//                    polygons.get(i).display(pa, colors.get(c));
//                    i--;
//                    if(i < 0) break;
//                }
//                if(i < 0) break;;
//            }
//        }

        int colorCnt = 0;
        for(int i = stepCnt ; i >= 0; ){

            int amntToChange = colorCnt + 1;
            if(colorCnt > colors.size() - 1){
                amntToChange = i ;
                colorCnt = colors.size() - 1;
            }
            if(amntToChange == 0) break;
            for(int c = 0; c < amntToChange; c++){
                polygons.get(i).display(pa, colors.get(colorCnt));
                i--;
                if(i == -1) break;
            }
            colorCnt++;
        }

    }
}
