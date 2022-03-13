package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainEx2a {
    public static void main(String[] args){

        /*
        Esto es codigo de ejemplo para probar
         */

        List<List<Integer>> cuadrado = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        list1.add(2);
        list1.add(7);
        list1.add(6);
        list2.add(9);
        list2.add(5);
        list2.add(1);
        list3.add(4);
        list3.add(3);
        list3.add(8);

        cuadrado.add(list1);
        cuadrado.add(list2);
        cuadrado.add(list3);

        MagicSquareProblem.MagicSquareState state = new MagicSquareProblem.MagicSquareState(cuadrado);

        //MagicSquareProblem.MagicSquareAction accion = new MagicSquareProblem.MagicSquareAction(3,2,8);

        MagicSquareProblem problem = new MagicSquareProblem(state);



        System.out.println(state);
//        System.out.println(accion);
//        System.out.println(accion.isApplicable(state));
//        System.out.println(accion.applyTo(state));
//        System.out.println(Arrays.toString(problem.actions(state)));
        System.out.println(problem.isGoal(state));
    }

}
