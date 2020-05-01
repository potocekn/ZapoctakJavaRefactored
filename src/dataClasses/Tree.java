package dataClasses;

import treeParts.ds.DS;

/**This class represents tree that has its own distribution system and direction of investment*/
public class Tree {
    public DS ds;
    public Directions direction;

    public Tree(Directions direction, int numberOfYears)
    {
        this.ds = new DS(numberOfYears);
        this.direction = direction;
    }

    public DS getDS(){return this.ds;}
}
