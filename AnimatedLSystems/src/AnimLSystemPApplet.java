import Instruction.*;
import processing.core.PApplet;
import processing.core.PVector;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AnimLSystemPApplet extends PApplet{


    LSystem l;

    Instant lastClick;

    ArrayList<IArray> arrays;

    long duration = 300;

    long totalPassed = 0;
    float redO = 0;


    /**
     * This class can be turned into a stat machine
     *
     * It will need to overall track the transform that should be used for new
     * machines and instructions added to the machine.
     *
     * state is a combination of vector, rotation, and time to start and end it's animations
     * start state: could be useful to hang onto, can be used to pop and push the transform
     * current state:  should get updated by the instructions which will give their own
     *  intermediate states if they're valid
     * instruction set end state: this state is used to add new instructions as well as
     *  create new machines
     *
     *  part of state is the machine duration, it should apply that to any subsequent new instruction
     *
     *  IT'S IMPORTANT THAT ALL STATE CHANGES CAN BE REPRESENTED BY ONE LETTER THEN SOME NUMBER
     *  REMOVE CHANGES SIMPLY
     *
     *  IF IT's not a new instruction it simply affects the state of the machine
     *
     *
     *
     *  a Que of instructions
     *
     * So it should have it's start state and it's current state tracked.  If a new machine is made
     * with this one as a root then the new machine
     *
     * To vary how fast the machine appears to animate is a ratio to the instructions duration
     * to it's distance to draw for straight lines.
     * Duration can also be an instruction in the language, to change a machines duration
     *
     * Pushs and pops or the state can actually be a statck of stacks managed by the machine itself.
     *
     * Can have a destroy machine grammar so it does terminate
     */
    public class IArray{
        ArrayList<Instruction> instructions;
        PVector o;
        float degrees;
        float start;

        IArray(PVector o, float degrees, float start){
            this.o = o;
            this.degrees = degrees;
            ArrayList<Instruction> newI = new ArrayList<>();
            instructions = newI;
            this.start = start;

        }
    }

    @Override
    public void settings() {

        size(1080, 960, P2D);



    }

    @Override
    public void setup(){

        /**
         * Gonna have to revisit this.  Instead of a bunch of queues there should just be a bunch
         * of different pieces and their state in a list, if they are partial or fully drawn and what not
         * is just done every frame instead of just drawing dots or what not, it will be much easier to manage
         *
         * Also if it's in the list, each new one simply gets the final of the last
         */
        redO = 0;
        arrays = new ArrayList<>();
        duration = 300;
        l = new LSystem();
        lastClick = Instant.now();
        PVector o = new PVector(width/2, height/2);
        println(l.production);
        for(char c : l.production.toCharArray()){
            computeInstruction(c);
        }

        for(int i = 1; i < 4; i++){
            l.iterate();
            println(l.production);
            for(char c : l.production.toCharArray()){
                computeInstruction(c);
            }
        }
    }

    @Override
    public void draw() {
        background(0);

        if(mousePressed && Instant.now().isAfter(lastClick.plus(2, ChronoUnit.SECONDS))){
            println(l.iterate());
            lastClick = Instant.now();
            for(char c : l.production.toCharArray()){
                computeInstruction(c);
            }

        }

        for(IArray a: arrays){
            for(Instruction instruction: a.instructions){
                stroke(255, 255, 50);

                line(instruction.getOrigin().x,
                        instruction.getOrigin().y,
                        instruction.getFinalPos().x,
                        instruction.getFinalPos().y);
            }
        }

        redO+=1;
        if(redO > 255) redO = 0;
        totalPassed+=1;
        println(totalPassed);
    }

    void computeInstruction(char c){
        switch (c) {
            case 'O':
                if(arrays.isEmpty()){
                    arrays.add(new IArray(new PVector(width/2, height/2), 90, 0));
                }
                else{
                    ArrayList<IArray> newIs = new ArrayList<>();

                    for (int k = 0; k < arrays.size(); k++) {
                        int size = arrays.get(k).instructions.size();
                        long start = arrays.get(k).instructions.get(size -1).getStart() + duration;
                        Instruction last = arrays.get(k).instructions.get(size -1);
                        newIs.add(new IArray(last.getFinalPos(), arrays.get(k).degrees += 90, start));


                    }
                    arrays.addAll(newIs);
                }

            case 'L': {
                for (IArray a : arrays) {
                    PVector cop;
                    long start;
                    if(a.instructions.isEmpty()){
                        cop = a.o.copy();
                        start = 0;
                    }
                    else{
                        cop = a.instructions.get(a.instructions.size() - 1).getFinalPos();
                        start = a.instructions.get(a.instructions.size() - 1).getStart() + duration;
                    }

                    LineInstruction line = new LineInstruction(cop, duration, start, a.degrees, 20);
                    a.instructions.add(line);
                }
            }

            case 'R': {
                for (IArray a : arrays) {
                    if(a.instructions.size() % 7 == 0) a.degrees -= 8;
                    else a.degrees += 17;
                }

            }
        }
    }

}
