package es.udc.intelligentsystems;

import java.util.List;

public class Node {

    private State nodeState;
    private Node parent;
    private Action nodeAction;

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
}
