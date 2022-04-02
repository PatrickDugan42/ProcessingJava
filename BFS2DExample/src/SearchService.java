import processing.core.PVector;

import java.util.*;
import java.util.stream.Collectors;

public class SearchService {

    public Stack<PVector> twoDimBreadthFirstSearch(int width, int height, PVector start, PVector target) {
        Set<PVector> known = new HashSet<>();
        known.add(start);
        Stack<PVector> finalPath = new Stack<>();
        Map<PVector, PVectorNode> map = new HashMap<>();
        generateChildMap(width, height, start, map);

        if (bfs(map, start, target, finalPath)) {
            System.out.format("Found final target, path is %s\n", finalPath.toString());
        } else System.out.println("Not found");
        return finalPath;
    }

    private boolean bfs(Map<PVector, PVectorNode> map, PVector root, PVector target, Stack<PVector> path){
        Queue<PVectorNode> nodes = new LinkedList<>();
        map.get(root).setExplored(true);
        nodes.add(map.get(root));
        while(!nodes.isEmpty()){
            PVectorNode node = nodes.remove();
            path.add(node.getRoot());
            if(node.getRoot().equals(target)){
                return true;
            }
            for(PVector v : node.getChildren()){
                PVectorNode childNode = map.get(v);
                if(!childNode.isExplored()){
                    childNode.setExplored(true);
                    nodes.add(childNode);
                }
            }
        }
        return false;
    }

    private Queue<PVector> getChildren(int width, int height, PVector root, Set<PVector> known) {
        Queue<PVector> children = new LinkedList<>();

        PVector[] childOrder = {
                new PVector(0, -1),
                new PVector(1, -1),
                new PVector(1, 0),
                new PVector(1, 1),
                new PVector(0, 1),
                new PVector(-1, 1),
                new PVector(-1, 0),
                new PVector(-1, -1)
        };

        for (PVector v : childOrder) {
            PVector temp = PVector.add(root, v);

            //check if in bounds
            if (temp.x < 0 || temp.x > width - 1) continue;
            if (temp.y < 0 || temp.y > height - 1) continue;

            //check if it's in the roots above
            if (known.contains(temp)) continue;

            //otherwise it's a child
            children.add(temp);
        }
        return children;
    }

    private List<PVector> getChildrenList(int width, int height, PVector root, Set<PVector> known) {
        List<PVector> children = new ArrayList<>();

        PVector[] childOrder = {
                new PVector(0, -1),
                new PVector(1, -1),
                new PVector(1, 0),
                new PVector(1, 1),
                new PVector(0, 1),
                new PVector(-1, 1),
                new PVector(-1, 0),
                new PVector(-1, -1)
        };

        for (PVector v : childOrder) {
            PVector temp = PVector.add(root, v);

            //check if in bounds
            if (temp.x < 0 || temp.x > width - 1) continue;
            if (temp.y < 0 || temp.y > height - 1) continue;

            //check if it's in the roots above
            if (known.contains(temp)) continue;

            //otherwise it's a child
            children.add(temp);
        }
        return children;
    }

    public Queue<PVector> genChildOrder2d(int width, int height, PVector root){
        return childOrder(width, height, root);
    }

    private Queue<PVector> childOrder(int width, int height, PVector root){
        Queue<PVector> order = new LinkedList<>();
        Set<PVector> known = new HashSet<>();
        known.add(root);
        order.add(root);
        order.addAll(getChildren(width, height, root, known));
        known.addAll(order);

        Queue<PVector> temp = new LinkedList<>();
        for(PVector v : order){
            temp.addAll(getChildren(width, height, v, known));
            known.addAll(temp);
        }
        temp.addAll(childOrder(width, height, temp, known));
        order.addAll(temp);
        return order;
    }

    private Queue<PVector> childOrder(int width, int height, Queue<PVector> roots, Set<PVector> known){

        Queue<PVector> order = new LinkedList<>();
        if (roots.isEmpty()) return order;

        Queue<PVector> temp = new LinkedList<>();
        for(PVector v : roots){
            temp.addAll(getChildren(width, height, v, known));
            known.addAll(temp);
        }
        temp.addAll(childOrder(width, height, temp, known));
        return temp;
    }

    public void generateChildMap(int width, int height, PVector root, Map<PVector, PVectorNode> map){
        Set<PVector> known = new HashSet<>();
        known.add(root);

        Queue<PVector> calcOrder = childOrder(width, height, root);
        for(PVector v : calcOrder){
            PVectorNode node = new PVectorNode(v, getChildrenList(width, height, v, known));
            known.addAll(node.getChildren());
            map.put(v, node);
        }
    }
}
