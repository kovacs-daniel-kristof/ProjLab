package sheldon;

import java.util.ArrayList;

public class Station extends Rail {
	private Color myColor;
	private ArrayList<Color> waitingPassengers;

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
@Override
public boolean isWaitingPassengerSameColor(TrainPart tp){
	for(int i =0; i<waitingPassengers.size();i++){
		if(waitingPassengers.equals(tp.getColor())){
			return true;
		}
	}
	return  false;
}

}

