package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphSearchStrategy implements SearchStrategy{

    @Override
    public Node[] solve(SearchProblem p) throws Exception{
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> frontier = new ArrayList<>();
        List<Node> succ = new ArrayList<>();
        State currentState = p.getInitialState();

        Node node;
        State state;

        Node currentNode = new Node();
        currentNode.setNodeState(currentState);

        if(p.isGoal(currentState))
            return reconstruct_sol(currentNode);

        frontier.add(currentNode);

        while (!p.isGoal(currentState)){

            if(frontier.isEmpty())
                throw new Error("Failure: frontier is empty\n");

            node = frontier.get(0);               // Equivalent to POP
            frontier.remove(0);             // with Arraylist

            state = node.getNodeState();

            explored.add(node);

            succ = successors(node, p);

            for(Node h: succ){
                if(!p.isGoal(h.getNodeState())){
                    Node nodeH = new Node(h.getNodeState(), node, h.getNodeAction());

                    List<State> statesFrontier = new ArrayList<>();
                    List<State> statesExplored = new ArrayList<>();

                    for (Node item : frontier) {
                        statesFrontier.add(item.getNodeState());
                    }
                    for (Node value : explored) {
                        statesExplored.add(value.getNodeState());
                    }

                    if(!statesFrontier.contains(h.getNodeState()) && !statesExplored.contains(h.getNodeState())){
                        frontier.add(nodeH);
                    }
                }
                Node[] reconstr = reconstruct_sol(node);
                Node[] solution = new Node[reconstr.length + 1];
                Node addNode = new Node(h.getNodeState(), null, h.getNodeAction());
                for(int i = 0; i < reconstr.length; i++){
                    solution[i] = reconstr[i];
                }
                solution[reconstr.length] = addNode;
                return solution;
            }
        }

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
                State newState = acc.applyTo(node.getNodeState());
                Node newNode = new Node(newState,node,acc);
                successors.add(newNode);
            }
        }
        return successors;
    }
}
