package sheldon;

public class Rail {
	Rail[] neighbours = new Rail[2];
	TrainPart myTrainPart;

	public Rail(){
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
	public void AddTrainToRail (TrainPart tp){ // ha egyszerre több vonat lenne a sinen akkor ütközés --> a játék véget ér
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
	public void setNeighbours(Rail r1, Rail r2){
		neighbours[0] = r1;
		neighbours[1] = r2;
	}

	public boolean hasWaitingPassengers(){
		return false;
	}



	public void ChangeDirection() {
	}
	public void ChangeGate(){
	}
	public void changecolor(Color c){
	}


}