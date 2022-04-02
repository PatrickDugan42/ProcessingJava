import org.junit.Test;
import processing.core.PVector;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchServiceTest {

    @Test
    public void myTest(){
        SearchService uut = new SearchService();

        int width = 10;
        int height = 10;

        PVector root = new PVector(5,5);
        PVector target = new PVector(7, 7);

        uut.twoDimBreadthFirstSearch(width, height, root, target);
    }

    @Test
    public void myTest2(){
        SearchService uut = new SearchService();

        int width = 5;
        int height = 5;

        PVector root = new PVector(0,0);
        PVector target = new PVector(4, 4);

        Stack<PVector> path = uut.twoDimBreadthFirstSearch(width, height, root, target);
        assertTrue(!path.empty());
    }

    @Test
    public void childGenTest(){
        SearchService uut = new SearchService();

        PVector root = new PVector(0,0);
        Queue<PVector> order = uut.genChildOrder2d(5, 5, root);

        System.out.println(order);
        System.out.println(order.size());
        Map<PVector, PVectorNode> map = new HashMap<>();
        uut.generateChildMap(5, 5, root, map);
        System.out.println(map);
    }

}