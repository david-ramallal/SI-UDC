package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Strategy4 implements SearchStrategy {

    public Strategy4() {
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception{
        ArrayList<State> explored = new ArrayList<>();
        State currentState = p.getInitialState();
        explored.add(currentState);

        ArrayList<Node> nodes = new ArrayList<>();
        Node currentNode = new Node();
        currentNode.setNodeState(currentState);
        nodes.add(currentNode);


        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentState);

        while (!p.isGoal(currentState)){
            System.out.println((i++) + " - " + currentState + " is not a goal");
            Action[] availableActions = p.actions(currentState);
            boolean modified = false;
            for (Action acc: availableActions) {
                State sc = p.result(currentState, acc);
                System.out.println((i++) + " - RESULT(" + currentState + ","+ acc + ")=" + sc);
                if (!explored.contains(sc)) {
                    currentState = sc;
                    nodes.add(new Node(sc,currentNode,acc));
                    currentNode = nodes.get(nodes.size() - 1);
                    System.out.println((i++) + " - " + sc + " NOT explored");
                    explored.add(currentState);
                    modified = true;
                    System.out.println((i++) + " - Current state changed to " + currentState);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " already explored");
            }
            if (!modified) throw new Exception("No solution could be found");
        }
        System.out.println((i++) + " - END - " + currentState);
        return reconstruct_sol(nodes.get(nodes.size() - 1));
    }

    public boolean containsState(List<Node> list, State state) {
        for (Node node : list) {
            if (node.getNodeState() == state)
                return true;
        }
        return false;
    }

    public Node[] reconstruct_sol(Node node)  {
        List<Node> solution = new ArrayList<>();
        Node a = node;

        while(a != null) {
            solution.add(a);
            a = a.getParent();
        }

        Collections.reverse(solution);
        return solution.toArray(new Node[0]);
    }

}
