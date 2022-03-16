package es.udc.intelligentsystems.g61_05_2C;

import es.udc.intelligentsystems.g61_05.*;

import java.util.ArrayList;
import java.util.List;

public class MainImproved {
    public static void main(String[] args) throws Exception {

        /*
        This is an example to check an improved version of Breadth-first and Depth-first
         */

        List<List<Integer>> square = new ArrayList<>();
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

        square.add(list1);
        square.add(list2);
        square.add(list3);

        //Output should be: [[4,9,2],[3,5,7],[8,1,6]]

        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(square);
        SearchProblem magicSquare = new MagicSquareProblem(initialState);

        System.out.println("Breadth-first(Improved): ");
        SearchStrategy breadthImpr = new ImprovedBF();
        Node[] nodesBreadthImpr = breadthImpr.solve(magicSquare);
        System.out.println(nodesBreadthImpr[nodesBreadthImpr.length-1]);
    }
}
