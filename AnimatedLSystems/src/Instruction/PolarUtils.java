package Instruction;

import processing.core.PVector;

public class PolarUtils {

    public static PVector polarFromOrigin(float radius, float degrees){

        double x = radius * Math.cos(Math.toRadians(degrees));
        double y = radius * Math.sin(Math.toRadians(degrees));
        return new PVector((float) x,(float) y);

    }

    public static PVector polarFromOrigin(float radius, float degrees, PVector origin){

        double x = radius * Math.cos(Math.toRadians(degrees)) + origin.x;
        double y = radius * Math.sin(Math.toRadians(degrees)) + origin.y;
        return new PVector((float) x,(float) y);

    }
}
