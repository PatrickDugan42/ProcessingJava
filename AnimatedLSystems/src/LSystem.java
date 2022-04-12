public class LSystem {

    String axiom;
    String rule;
    String production;


    int generations;

    LSystem(){
        axiom = "ORLRLORR";
        rule = "OLRRRLR";
        production = axiom;
    }

    void reset(){
        production = axiom;
        generations = 0;
    }

    int getGenerations(){
        return generations;
    }

    String iterate() {
        generations++;
        production = production.replaceAll("O", rule);
        return production;
    }
}
