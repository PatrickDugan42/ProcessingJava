package Instruction;

import processing.core.PVector;

import java.time.Instant;

public class LineInstruction implements Instruction {

    PVector origin;
    long duration;
    long start;
    float degrees;
    float length;
    PVector finalPos;

    public LineInstruction(PVector o, long duration, long start, float degrees, float length) {
        this.origin = o;
        this.duration = duration;
        this.start = start;
        this.degrees = degrees;
        this.length = length;
        this.finalPos = PolarUtils.polarFromOrigin(length, degrees, origin);
    }


    @Override
    public PVector getCurPosition(long cur){
        long passed = cur - start;
        if(passed > duration){
            return PolarUtils.polarFromOrigin(length, degrees);
        }
        PVector finalPos = PolarUtils.polarFromOrigin(length, degrees, origin);
        System.out.println(finalPos);
        return PVector.lerp(origin, finalPos, (passed/(float)duration));
    }

    @Override
    public long getStart() {
        return start;
    }

    @Override
    public long setStart(long start) {
        this.start = start;
        return this.start;
    }

    @Override
    public boolean isDone(long passed) {
        if(passed > (start + duration)) return true;
        return false;
    }

    @Override
    public String getId() {
        return "L";
    }

    @Override
    public String setId(String id) {
        return "L";
    }

    @Override
    public InstructionType getType() {
        return InstructionType.LINE;
    }


    @Override
    public PVector getOrigin() {
        return origin;
    }

    @Override
    public PVector setOrigin(PVector v) {
        return origin = v;
    }

    @Override
    public PVector getFinalPos() {
        return finalPos;
    }

    @Override
    public float setDegrees() {
        return 0;
    }

    @Override
    public float getDegrees() {
        return degrees;
    }


}
