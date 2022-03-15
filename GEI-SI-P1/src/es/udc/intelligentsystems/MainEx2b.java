package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.List;

public class MainEx2b {
    public static void main(String[] args){
        List<List<Integer>> square = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();

        list1.add(0);
        list1.add(0);
        list1.add(15);
        list1.add(0);
        list2.add(0);
        list2.add(12);
        list2.add(5);
        list2.add(3);
        list3.add(0);
        list3.add(0);
        list3.add(4);
        list3.add(0);
        list4.add(7);
        list4.add(0);
        list4.add(10);
        list4.add(16);

        square.add(list1);
        square.add(list2);
        square.add(list3);
        square.add(list4);

        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(square);
        SearchProblem magicSquare = new MagicSquareProblem(initialState);
        HeuristicMagicSquare heuristic = new HeuristicMagicSquare();

        System.out.println(heuristic.evaluate(magicSquare.getInitialState()));
    }
}
