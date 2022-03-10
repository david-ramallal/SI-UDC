package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphSearchStrategy implements SearchStrategy{

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

        while(!frontier.isEmpty()){
            node = frontier.remove(0);
            state = node.getNodeState();
            if(p.isGoal(state)){
                return reconstruct_sol(node);
            }else {
                explored.add(node);
                succ = successors(node, p);
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
                    frontier.add(h);
                }

            }

        }

        throw new Exception("No solution could be found");


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

    public List<Node> successors(Node node, SearchProblem p)  {
        List<Node> successors = new ArrayList<>();

        Action[] availableActions = p.actions(node.getNodeState());
        for(Action acc: availableActions){
            if(acc.isApplicable(node.getNodeState())){
                //State newState = acc.applyTo(node.getNodeState());
                State newState = p.result(node.getNodeState(), acc);
                Node newNode = new Node(newState,node,acc);
                successors.add(newNode);
            }
        }
        return successors;
    }
}
