package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.SearchProblem;

public class Main {

    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        SearchStrategy buscador = new Strategy4();
        Node[] nodes = buscador.solve(aspiradora);
        System.out.println("Explored nodes: ");
        for(int i = 0; i < nodes.length; i++){
            System.out.println(nodes[i]);
        }
    }
}
