package es.udc.intelligentsystems.g61_05;

public class HeuristicMagicSquare extends Heuristic {
    @Override
    public float evaluate(State e) {
        MagicSquareProblem.MagicSquareState squareState = (MagicSquareProblem.MagicSquareState) e;
        int countEmptyCells = 0;
        int totalRow, totalCol;
        float valueHeuristic, overpass = 0;
        float cellsSquare = squareState.getSquare().size() * squareState.getSquare().size();

        float maxNumber = (squareState.getSquare().size() * (cellsSquare + 1)) / 2;

        for(int i = 0; i < squareState.getSquare().size(); i++){
            for(int j = 0; j < squareState.getSquare().size(); j++){
                if(squareState.getSquare().get(i).get(j) == 0)
                    countEmptyCells++;
            }
        }

        if(countEmptyCells == 0)
            return 0;

        for(int i = 0; i < squareState.getSquare().size(); i++){
            totalRow = 0;
            totalCol = 0;
            for(int j = 0; j < squareState.getSquare().size(); j++){
                totalRow += squareState.getSquare().get(i).get(j);
                totalCol += squareState.getSquare().get(j).get(i);
            }

            if(totalRow > maxNumber || totalCol > maxNumber) {
                overpass += maxNumber*2 * squareState.getSquare().size();
                break;
            }else{
                overpass += ((maxNumber-totalCol) + (maxNumber - totalRow));
            }

        }

        valueHeuristic = (countEmptyCells / cellsSquare) * (overpass);

        return valueHeuristic;
    }
}
