package es.udc.intelligentsystems;

import java.util.ArrayList;
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

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list3.add(7);
        list3.add(0);
        list3.add(9);

        cuadrado.add(list1);
        cuadrado.add(list2);
        cuadrado.add(list3);

        MagicSquareProblem.MagicSquareState problema = new MagicSquareProblem.MagicSquareState(cuadrado);

        MagicSquareProblem.MagicSquareAction accion = new MagicSquareProblem.MagicSquareAction(3,2,8);


        System.out.println(problema);
        System.out.println(accion);
        System.out.println(accion.isApplicable(problema));
        System.out.println(accion.applyTo(problema));
    }

}
