package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.List;

public class Strategy4 implements SearchStrategy {

    private List<Node> nodeList;

    public Strategy4() {
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception{
        ArrayList<Node> explored = new ArrayList<Node>();
        Node currentNode = new Node();
        currentNode.setNodeState(p.getInitialState());
        explored.add(currentNode);

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentNode.getNodeState());

        while (!p.isGoal(currentNode.getNodeState())){
            System.out.println((i++) + " - " + currentNode.getNodeState() + " is not a goal");
            Action[] availableActions = p.actions(currentNode.getNodeState());
            boolean modified = false;
            for (Action acc: availableActions) {
                State sc = p.result(currentNode.getNodeState(), acc);
                System.out.println((i++) + " - RESULT(" + currentNode.getNodeState() + ","+ acc + ")=" + sc);
                if (!explored.contains(sc)) {
                    explored.add(new Node(sc,currentNode,acc));
                    currentNode = explored.get(explored.size() - 1);
                    System.out.println((i++) + " - " + sc + " NOT explored");
                    modified = true;
                    System.out.println((i++) + " - Current state changed to " + currentNode.getNodeState());
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " already explored");
            }
            if (!modified) throw new Exception("No solution could be found");
        }
        System.out.println((i++) + " - END - " + currentNode.getNodeState());
        Node[] exploredNodes = (Node[]) explored.toArray();
        return exploredNodes;
    }
}
