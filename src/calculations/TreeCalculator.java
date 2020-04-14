package calculations;

import readers.Directions;
import treeParts.ds.DS;

/**This class represents tree that has its own distribution system and direction of investment*/
public class TreeCalculator {
    DS ds;
    Directions direction;

    public TreeCalculator(Directions direction, int numberOfYears)
    {
        this.ds = new DS(numberOfYears);
        this.direction = direction;
    }

    public DS getDS(){return this.ds;}
}
