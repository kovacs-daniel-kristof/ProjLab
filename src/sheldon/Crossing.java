package sheldon;

import java.util.ArrayList;

public class Crossing extends Rail{
    Rail[] neighbours2 = new Rail[2];

    public void setNeighbours(ArrayList<Rail> railek, int allas){
        neighbours[0] = railek.get(0);
        neighbours[1] = railek.get(1);
        neighbours2[0] = railek.get(2);
        neighbours2[1] = railek.get(3);
    }
    public Rail GetNextRail(Rail r){
        if(neighbours[0] == r)
            return neighbours[1];
        if(neighbours[1] == r)
            return neighbours[0];
        if(neighbours2[0] == r)
            return neighbours2[1];
        else
            return neighbours2[0];
    }

    public void AskID (int a){
        if(a == 0 || a == 2) {
            System.out.println("Crossing, ID : " + ID);
        }
    }

}