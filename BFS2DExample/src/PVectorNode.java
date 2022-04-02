import processing.core.PVector;

import java.util.LinkedList;
import java.util.List;

public class PVectorNode {

    PVector root;
    List<PVector> children;
    boolean explored;

    public PVectorNode(PVector root, List<PVector> children){
        this.root = root;
        this.children = children;
        this.explored = false;
    }

    public PVector getRoot() {
        return root;
    }

    public void setRoot(PVector root) {
        this.root = root;
    }


    public List<PVector> getChildren() {
        return children;
    }

    public void setChildren(List<PVector> children) {
        this.children = children;
    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    @Override
    public String toString() {
        return "PVectorNode{" +
                "root=" + root.toString() +
                ", children=" + children.toString() +
                '}';
    }
}
