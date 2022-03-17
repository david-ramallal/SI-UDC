package es.udc.intelligentsystems.g61_05.example;

import es.udc.intelligentsystems.g61_05.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphSearchStrategy implements SearchStrategy {

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> frontier = new ArrayList<>();
        List<Node> succ;

        Node node;
        State state;

        Node initialNode = new Node();
        initialNode.setNodeState(p.getInitialState());

        frontier.add(initialNode);

        int i = 1;

        System.out.println((i++) + " - Starting search at " + initialNode.getNodeState());

        while(!frontier.isEmpty()){
            node = frontier.remove(0);
            state = node.getNodeState();
            if(p.isGoal(state)){
                System.out.println((i) + " - END - " + state);
                return reconstruct_sol(node);
            }else {
                System.out.println((i++) + " - " + state + " is not a goal");
                explored.add(node);
                succ = successors(node, p, i);
                i+=succ.size();
            }

            for(Node h: succ) {
                List<State> statesFrontier = new ArrayList<>();
                List<State> statesExplored = new ArrayList<>();

                for (Node item : frontier) {
                    statesFrontier.add(item.getNodeState());
                }
                for (Node value : explored) {
                    statesExplored.add(value.getNodeState());
                }

                if (!statesFrontier.contains(h.getNodeState()) && !statesExplored.contains(h.getNodeState())) {
                    System.out.println((i++) + " - " + h.getNodeState() + " NOT explored");
                    frontier.add(h);
                }
                else
                    System.out.println((i++) + " - " + h.getNodeState() + " already explored");
            }
            System.out.println((i++) + " - Current state changed to " + frontier.get(0).getNodeState());
        }
        throw new Exception("No solution could be found");
    }

    /*
     * Function for reconstructing the solution.
     * It receives the goal node and, using its parents, it
     * reconstructs the solution and return a Node list.
     */
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

    /*
     * Function for obtaining the expansion of a node.
     * It receives a node,a search problem and a counter,
     * and, applying the action, it obtains the list of
     * nodes of which the current node is the parent.
     */
    public List<Node> successors(Node node, SearchProblem p, int counter)  {
        List<Node> successors = new ArrayList<>();

        Action[] availableActions = p.actions(node.getNodeState());
        for(Action acc: availableActions){
            if(acc.isApplicable(node.getNodeState())){
                State newState = p.result(node.getNodeState(), acc);
                System.out.println((counter++) + " - RESULT(" + node.getNodeState() + ","+ acc + ")=" + newState);
                Node newNode = new Node(newState,node,acc);
                successors.add(newNode);
            }
        }
        return successors;
    }
}
