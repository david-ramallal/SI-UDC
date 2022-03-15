package es.udc.intelligentsystems.g61_05;

public class Node implements Comparable<Node>{

    private State nodeState;
    private Node parent;
    private Action nodeAction;
    private float pathCost;
    private float heuristicCost;


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

    public void setPathCost(float pathCost) {
        this.pathCost = pathCost;
    }

    public void setHeuristicCost(float heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    @Override
    public int compareTo(Node o) {
        if(o == null)
            throw new NullPointerException();

        if(this.heuristicCost == o.heuristicCost)
            return 0;
        else if(this.heuristicCost < o.heuristicCost)
            return -1;
        else
            return 1;
    }
}
