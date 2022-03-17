package es.udc.intelligentsystems.g61_05;

public class Node implements Comparable<Node>{

    private State nodeState;
    private Node parent;
    private Action nodeAction;
    //private float pathCost;
    //private float totalCost;
    private float f;
    private float g;
    private float h;


    public Node(State nodeState, Node parent, Action nodeAction) {
        this.nodeState = nodeState;
        this.parent = parent;
        this.nodeAction = nodeAction;
    }

    public Node() {
    }

    public State getNodeState() {
        return nodeState;
    }

    public void setNodeState(State nodeState) {
        this.nodeState = nodeState;
    }

    public Node getParent() {
        return parent;
    }

    public Action getNodeAction() {
        return nodeAction;
    }

    @Override
    public String toString() {
        return nodeState.toString();
    }

//    public void setPathCost(float pathCost) {
//        this.pathCost = pathCost;
//    }
//
//    public float getPathCost() {
//        return pathCost;
//    }
//
//    public float getTotalCost() {
//        return totalCost;
//    }
//
//    public void setTotalCost(float totalCost) {
//        this.totalCost = totalCost;
//    }


    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    @Override
    public int compareTo(Node o) {
//        if(o == null)
//            throw new NullPointerException();
//        if(this.totalCost + this.pathCost == o.totalCost + o.pathCost)
//            return 0;
//        else if(this.totalCost + this.pathCost < o.totalCost + o.pathCost)
//            return -1;
//        else
//            return 1;
        return Float.compare(this.f, o.f);
    }
}
