package es.udc.intelligentsystems.g61_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar implements InformedSearchStrategy {
    @Override
    public Node[] solve(SearchProblem p, Heuristic h) throws Exception {

        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> frontier = new ArrayList<>();
        List<Node> succ;
        int cntCreated = 0, cntExpanded = 0;

        Node node;
        State state;

        Node initialNode = new Node();
        initialNode.setNodeState(p.getInitialState());

        initialNode.setG(0);
        initialNode.setH(h.evaluate(initialNode.getNodeState()));
        initialNode.setF(initialNode.getG() + initialNode.getH());

        frontier.add(initialNode);

        while(!frontier.isEmpty()){
            frontier.sort(null);
            node = frontier.remove(0);
            state = node.getNodeState();
            if(p.isGoal(state)){
                System.out.println("Number of created nodes: " + cntCreated);
                System.out.println("Number of expanded nodes: " + cntExpanded);
                return reconstruct_sol(node);
            }else {
                explored.add(node);
                cntExpanded++;
                succ = successors(node, p);
                cntCreated += succ.size();
            }

            for(Node n: succ) {
                List<State> statesFrontier = new ArrayList<>();
                List<State> statesExplored = new ArrayList<>();

                for (Node item : frontier) {
                    statesFrontier.add(item.getNodeState());
                }
                for (Node value : explored) {
                    statesExplored.add(value.getNodeState());
                }

                n.setG(node.getG() + n.getNodeAction().getCost());
                n.setF(n.getG() + h.evaluate(n.getNodeState()));


                if(statesExplored.contains(n.getNodeState()))
                    continue;

                if(!statesFrontier.contains(n.getNodeState())){
                    frontier.add(n);
                    continue;
                }

                if(n.getF() < node.getF()){
                    System.out.println();
                    frontier.remove(node);
                    frontier.add(n);
                }
            }
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
     * It receives a node and a search problem,
     * and, applying the action, it obtains the list of
     * nodes of which the current node is the parent.
     */
    public List<Node> successors(Node node, SearchProblem p)  {
        List<Node> successors = new ArrayList<>();

        Action[] availableActions = p.actions(node.getNodeState());
        for(Action acc: availableActions){
            if(acc.isApplicable(node.getNodeState())){
                State newState = p.result(node.getNodeState(), acc);
                Node newNode = new Node(newState,node,acc);
                successors.add(newNode);
            }
        }
        return successors;
    }
}
