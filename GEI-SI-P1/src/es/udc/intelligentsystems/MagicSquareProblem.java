package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MagicSquareProblem extends SearchProblem{
    public static class MagicSquareState extends State{

        private List<List<Integer>> square;

        public MagicSquareState(List<List<Integer>> square) {
            this.square = square;
        }

        @Override
        public String toString() {
            String rtnString = "[";
            for(int i = 0; i < square.size(); i++){
                rtnString = rtnString + "[";
                for(int j = 0; j < square.size(); j++){
                    rtnString = rtnString + square.get(i).get(j).toString();
                    if(j != square.size() - 1)
                        rtnString = rtnString + ",";
                }
                if(i != square.size() - 1)
                    rtnString = rtnString + "],";
                else
                    rtnString = rtnString + "]";
            }
            rtnString = rtnString + "]";
            return rtnString;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MagicSquareState that)) return false;
            return Objects.equals(square, that.square);
        }

        @Override
        public int hashCode() {
            return Objects.hash(square);
        }
    }

    public static class MagicSquareAction extends Action{
        private int boxX, boxY;
        private int number;

        public MagicSquareAction(int boxX, int boxY, int number) {
            this.boxX = boxX;
            this.boxY = boxY;
            this.number = number;
        }

        @Override
        public String toString() {
            return number + " -> (" + boxX + "," + boxY + ")";
        }

        @Override
        public boolean isApplicable(State st) {
            MagicSquareState squareState = (MagicSquareState) st;

            if(squareState.square.get(boxX - 1).get(boxY - 1) != 0)
                return false;

            for(int i = 0; i < squareState.square.size(); i++){
                if(squareState.square.get(i).contains(number))
                    return false;
            }
            return true;
        }

        @Override
        public State applyTo(State st) {
            MagicSquareState squareState = (MagicSquareState) st;
            squareState.square.get(boxX - 1).set(boxY - 1, number);
            return squareState;
        }
    }

    public MagicSquareProblem(State initialState) {
        super(initialState);
    }

    @Override
    public boolean isGoal(State st) {
        MagicSquareState squareState = (MagicSquareState) st;
        int n = squareState.square.size() * squareState.square.size();
        int total = (squareState.square.size() * (n + 1))/2 ;
        int totalRow, totalCol;

        for(int i = 0; i < squareState.square.size(); i++){
            totalRow = 0;
            totalCol = 0;
            for(int j = 0; j < squareState.square.get(i).size(); j++){
                totalRow += squareState.square.get(i).get(j);
                totalCol += squareState.square.get(j).get(i);
            }
            if(totalRow!=total)
                return false;
            if(totalCol!=total)
                return false;
        }

        return true;
    }

    @Override
    public Action[] actions(State st) {
        MagicSquareState squareState = (MagicSquareState) st;
        int n = squareState.square.size() * squareState.square.size();
        boolean isContained;
        ArrayList<Action> actions= new ArrayList<>();

        for (int i = 1; i <= n; i++){
            isContained = false;
            for(int j = 0; j < squareState.square.size(); j++){
                if(squareState.square.get(j).contains(i)){
                    isContained = true;
                    break;
                }
            }
            if(!isContained){
                for(int j = 0; j < squareState.square.size(); j++){
                    for(int k = 0; k < squareState.square.size(); k++){
                        if(squareState.square.get(j).get(k) == 0)
                            actions.add(new MagicSquareAction(j+1, k+1, i));
                    }
                }
            }
        }

        return actions.toArray(new Action[0]);
    }
}
