package sheldon;

/**
 * Created by szabo on 15/04/2017.
 */
public class Crossing extends Rail{
    Rail[] neighbours2 = new Rail[2];

    public void setNeighbours2(Rail r1, Rail r2){
        neighbours2[0] = r1;
        neighbours2[1] = r2;
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

}