package Instruction;

import processing.core.PVector;

import java.time.Instant;

public interface Instruction {

    String getId();
    String setId(String id);
    InstructionType getType();

    PVector getOrigin();
    PVector setOrigin(PVector v);
    PVector getFinalPos();
    float setDegrees();
    float getDegrees();

    boolean isDone(long passed);

    PVector getCurPosition(long cur);

    long getStart();
    long setStart(long start);


}
