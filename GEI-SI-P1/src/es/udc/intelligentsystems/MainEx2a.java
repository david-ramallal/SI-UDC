package es.udc.intelligentsystems;

import es.udc.intelligentsystems.example.GraphSearchStrategy;

import java.util.ArrayList;
import java.util.List;

public class MainEx2a {
    public static void main(String[] args) throws Exception {

        /*
        This is an example to check Breadth-first and Depth-first
         */

        List<List<Integer>> cuadrado = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        list1.add(0);
        list1.add(0);
        list1.add(2);
        list2.add(0);
        list2.add(5);
        list2.add(7);
        list3.add(0);
        list3.add(0);
        list3.add(0);

        cuadrado.add(list1);
        cuadrado.add(list2);
        cuadrado.add(list3);

        //Output should be: [[4,9,2],[3,5,7],[8,1,6]]

        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(cuadrado);
        SearchProblem magicSquare = new MagicSquareProblem(initialState);

        System.out.println("Breadth-first: ");
        SearchStrategy breadth = new BreadthFirstStrategy();
        Node[] nodesBreadth = breadth.solve(magicSquare);
        System.out.println(nodesBreadth[nodesBreadth.length-1]);

        System.out.println("Depth-first: ");
        SearchStrategy depth = new DepthFirstStrategy();
        Node[] nodesDepth = depth.solve(magicSquare);
        System.out.println(nodesDepth[nodesDepth.length-1]);
    }
}
