package sheldon;

import java.util.Random;
import java.util.ArrayList;

public class Rail {
	Rail[] neighbours = new Rail[2];
	TrainPart myTrainPart;
    private Rail[] neig;
    protected int ID;

    public Rail(){
	}
    
    public void ID_des (int D){
		ID = D;
	}
	public int ID_rep (){
		return ID;
	}

	public void setWaitingPassengers (ArrayList<Color> wp){	
}
	
	public void setNeighbourSwitch( ArrayList<Rail> jc){

}

public void AskID (int a){
		if(a == 0) {
			System.out.println("Rail, ID : " + ID);
		}
}

public void Activate(){
}

public void setNeighbours2(Rail r1, Rail r2){
}
    
	public Rail GetNextRail (Rail r){
		if(neighbours[0] == r)
			return neighbours[1];
		else
			return neighbours[0];
	}
	public boolean CompareColors(Color c){
		return false;
	}
	public void AddTrainToRail (TrainPart tp){ // ha egyszerre t�bb vonat lenne a sinen akkor �tk�z�s --> a j�t�k v�get �r
		if(myTrainPart != null){
			Program.GameOver();
		}else{
			myTrainPart = tp;
		}
	}
	public void Clear (){
		myTrainPart = null;
	}

	public boolean HasTrain(){
		if(myTrainPart == null){
			return false;
		}else{
			return true;
		}
	}
	public void setNeighbours(ArrayList<Rail> railek, int allas){
		neighbours[0] = railek.get(0);
		neighbours[1] = railek.get(1);
	}

	public void setNeighbours(Rail nb1, Rail nb2){
        neighbours[0] = nb1;
        neighbours[1] = nb2;
    }

	public boolean hasWaitingPassengers(){
		return false;
	}

	public void ChangeDirection(){
	}
	public void ChangeGate(){
	}
	public void changecolor(Color c){
	}

    public boolean passengerGetOn(Color myColor) {
	    return false;
    }
}