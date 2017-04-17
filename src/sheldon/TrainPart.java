package sheldon;

public abstract class TrainPart {
	public TrainPart(){

	}
protected TrainPart behindMe;
protected TrainPart inFrontOfMe;
protected Rail currentRail;
protected boolean hasPassengers ;
protected Color myColor;

	public  void MoveToRail (Rail next){
		Rail tmp=currentRail; // tmpbe tároljuk azt a sínt ahonnan ellépünk
		currentRail=next; // a current rail az lesz ahová lépünk

		tmp.Clear(); // lelépünk az előző sínről
		if(HasNextTrain()){
			MoveToRail(tmp);
			// ha van mögötte kocsi akkor rekurzívan azon is meghívjuk a mozgatást
		}
		currentRail.AddTrainToRail(this); // az új sínen a vonatot is eltároljuk

	}
	public boolean HasPassenger (){// űlnek e a vonaton
		System.out.println("This carriage has passangers : " + hasPassengers);
		return hasPassengers;
	}
	public boolean HasNextTrain(){
		return behindMe!=null;
		//ellenőrzii hogy van e mögötte vonat
	}
	public  void move(){

    }

    public Color getMyColor() {
        return myColor;
    }
}
