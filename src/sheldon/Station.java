package sheldon;

import java.util.ArrayList;

public class Station extends Rail {
	Color myColor;
	ArrayList<Color> waitingPassengers;

	public Station(){
	}


	public boolean CompareColors(Color c){
		return c.equals(myColor);
	}


	public void changecolor (Color c){
		myColor = c;
	}

	public void setWaitingPassengers(ArrayList<Color> wp){
		waitingPassengers = wp;
	}

	public boolean hasWaitingPassengers(){
		if(waitingPassengers.size()>0)
			return true;
		else
			return false;
	}

}

