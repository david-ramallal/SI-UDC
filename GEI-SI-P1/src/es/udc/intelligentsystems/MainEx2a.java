package es.udc.intelligentsystems;

import es.udc.intelligentsystems.example.GraphSearchStrategy;
import es.udc.intelligentsystems.example.Strategy4;
import es.udc.intelligentsystems.example.VacuumCleanerProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainEx2a {
    public static void main(String[] args) throws Exception {

        /*
        Esto es codigo de ejemplo para probar
         */

        List<List<Integer>> cuadrado = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        list1.add(0);
        list1.add(0);
        list1.add(0);
        list2.add(0);
        list2.add(0);
        list2.add(0);
        list3.add(0);
        list3.add(0);
        list3.add(0);

        cuadrado.add(list1);
        cuadrado.add(list2);
        cuadrado.add(list3);

        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(cuadrado);

        /*//MagicSquareProblem.MagicSquareAction accion = new MagicSquareProblem.MagicSquareAction(3,2,8);

        MagicSquareProblem problem = new MagicSquareProblem(state);



        System.out.println(state);
//        System.out.println(accion);
//        System.out.println(accion.isApplicable(state));
//        System.out.println(accion.applyTo(state));
//        System.out.println(Arrays.toString(problem.actions(state)));
        System.out.println(problem.isGoal(state));
*/



        SearchProblem magicSquare = new MagicSquareProblem(initialState);

        System.out.println("Breadth-first: ");

        SearchStrategy buscador = new BreadthFirstStrategy();
        Node[] nodes = buscador.solve(magicSquare);
        System.out.println("Explored nodes: ");
        for(int i = 0; i < nodes.length; i++){
            System.out.println(nodes[i]);
        }


    }

}
