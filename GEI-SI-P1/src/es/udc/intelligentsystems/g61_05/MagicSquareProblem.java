package es.udc.intelligentsystems.g61_05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MagicSquareProblem extends SearchProblem{
    public static class MagicSquareState extends State{

        private final List<List<Integer>> square;

        public MagicSquareState(List<List<Integer>> square) {
            this.square = square;
        }

        public List<List<Integer>> getSquare() {
            return square;
        }

        @Override
        public String toString() {
            StringBuilder rtnString = new StringBuilder("[");
            for(int i = 0; i < square.size(); i++){
                rtnString.append("[");
                for(int j = 0; j < square.size(); j++){
                    rtnString.append(square.get(i).get(j).toString());
                    if(j != square.size() - 1)
                        rtnString.append(",");
                }
                if(i != square.size() - 1)
                    rtnString.append("],");
                else
                    rtnString.append("]");
            }
            rtnString.append("]");
            return rtnString.toString();
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
        private final int boxX;
        private final int boxY;
        private final int number;

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
            int totalRow, totalCol, totalDiag = 0, totalDiag2 = 0;

            float cellsSquare = squareState.getSquare().size() * squareState.getSquare().size();
            float maxNumber = (squareState.getSquare().size() * (cellsSquare + 1)) / 2;

            if(squareState.square.get(boxX - 1).get(boxY - 1) != 0)
                return false;

            for(int i = 0; i < squareState.square.size(); i++){
                if(squareState.square.get(i).contains(number))
                    return false;
            }

            for(int i = 0; i < squareState.getSquare().size(); i++){
                totalRow = 0;
                totalCol = 0;
                for(int j = 0; j < squareState.getSquare().size(); j++){
                    totalRow += squareState.getSquare().get(i).get(j);
                    totalCol += squareState.getSquare().get(j).get(i);
                }
                totalDiag += squareState.getSquare().get(i).get(i);
                totalDiag2 += squareState.getSquare().get(i).get(squareState.getSquare().size() - 1 - i);

                if(totalRow > maxNumber || totalCol > maxNumber) {
                    return false;
                }
            }
            if(totalDiag > maxNumber || totalDiag2 > maxNumber)
                return false;

            return true;
        }

        @Override
        public State applyTo(State st) {
            MagicSquareState squareState = (MagicSquareState) st;
            List <List<Integer>> newSquare = new ArrayList<>();

            for(int i = 0; i < squareState.square.size(); i++) {
                List <Integer> intList = new ArrayList<>();
                for (int j = 0; j < squareState.square.size(); j++) {
                    intList.add(squareState.square.get(i).get(j));
                }
                newSquare.add(intList);
            }

            newSquare.get(boxX - 1).set(boxY - 1, number);
            return new MagicSquareState(newSquare);
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
        int totalRow, totalCol, totalDiag = 0, totalDiag2 = 0;

        for(int i = 0; i < squareState.square.size(); i++){
            totalRow = 0;
            totalCol = 0;
            for(int j = 0; j < squareState.square.get(i).size(); j++){
                totalRow += squareState.square.get(i).get(j);
                totalCol += squareState.square.get(j).get(i);
            }
            totalDiag += squareState.square.get(i).get(i);
            totalDiag2 += squareState.square.get(i).get(squareState.square.get(i).size() - 1 -i);
            if(totalRow!=total || totalCol!=total)
                return false;
        }

        if(totalDiag != total || totalDiag2 != total)
            return false;

        return true;
    }

    @Override
    public Action[] actions(State st) {
        MagicSquareState squareState = (MagicSquareState) st;
        int n = squareState.square.size() * squareState.square.size();
        boolean isContained = false;
        ArrayList<Action> actions= new ArrayList<>();
        ArrayList<Integer> intNotInitState = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            for (int j = 0; j < squareState.square.size(); j++) {
                if (squareState.square.get(j).contains(i)) {
                    isContained = true;
                    break;
                }
            }
            if(!isContained)
                intNotInitState.add(i);
            isContained = false;
        }

        for(int j = 0; j < squareState.square.size(); j++){
            for(int k = 0; k < squareState.square.get(j).size(); k++){
                if(squareState.square.get(j).get(k) == 0){
                    for (Integer integer : intNotInitState) {
                        actions.add(new MagicSquareAction(j + 1, k + 1, integer));
                    }
                }
            }
        }

        return actions.toArray(new Action[0]);
    }
}
