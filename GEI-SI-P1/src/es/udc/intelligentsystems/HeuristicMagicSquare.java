package es.udc.intelligentsystems;

public class HeuristicMagicSquare extends Heuristic {
    @Override
    public float evaluate(State e) {
        MagicSquareProblem.MagicSquareState squareState = (MagicSquareProblem.MagicSquareState) e;
        int countEmptyCells = 0;
        float valueHeuristic;
        float cellsSquare = squareState.getSquare().size() * squareState.getSquare().size();

        for(int i = 0; i < squareState.getSquare().size(); i++){
            for(int j = 0; j < squareState.getSquare().size(); j++){
                if(squareState.getSquare().get(i).get(j) == 0)
                    countEmptyCells++;
            }
        }

        if(countEmptyCells == 0)
            return 0;
        else{
            valueHeuristic = countEmptyCells / cellsSquare;
        }
        return valueHeuristic;
    }
}
