package es.udc.intelligentsystems.g61_05;

import java.util.ArrayList;
import java.util.List;

public class MainEx2b {
    public static void main(String[] args) throws Exception {
        List<List<Integer>> square = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        list1.add(2);
        list1.add(0);
        list1.add(0);
        list2.add(0);
        list2.add(0);
        list2.add(0);
        list3.add(0);
        list3.add(0);
        list3.add(0);


        square.add(list1);
        square.add(list2);
        square.add(list3);

        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(square);
        SearchProblem magicSquare = new MagicSquareProblem(initialState);
        HeuristicMagicSquare heuristic = new HeuristicMagicSquare();
        InformedSearchStrategy aStar = new AStar();

        Node[] nodes = aStar.solve(magicSquare, heuristic);
        System.out.println(nodes[nodes.length-1]);

    }
}
