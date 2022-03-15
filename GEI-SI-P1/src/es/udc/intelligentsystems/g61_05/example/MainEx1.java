package es.udc.intelligentsystems.g61_05.example;

import es.udc.intelligentsystems.g61_05.Node;
import es.udc.intelligentsystems.g61_05.SearchStrategy;
import es.udc.intelligentsystems.g61_05.SearchProblem;

public class MainEx1 {

    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        System.out.println("Strategy4:");

        SearchStrategy buscador1 = new Strategy4();
        Node[] nodes1 = buscador1.solve(aspiradora);
        System.out.println("Explored nodes: ");
        for(int i = 0; i < nodes1.length; i++){
            System.out.println(nodes1[i]);
        }

        System.out.println("GraphSearchStrategy:");

        SearchStrategy buscador2 = new GraphSearchStrategy();
        Node[] nodes2 =  buscador2.solve(aspiradora);
        System.out.println("Explored nodes: ");
        for(int i = 0; i < nodes2.length; i++){
            System.out.println(nodes2[i]);
        }
    }
}
