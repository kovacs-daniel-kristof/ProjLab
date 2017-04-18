package sheldon;

public abstract class TrainPart {
	public TrainPart(){
		hasPassengers = false;
	}
protected TrainPart behindMe;
protected TrainPart inFrontOfMe;
protected Rail currentRail;
protected boolean hasPassengers ;
protected Color myColor;
protected int ID;


public void ID_des (int D){
	ID = D;
}
public int ID_rep (){
	return ID;
}

public void StandardStartup (TrainPart a,TrainPart b,Rail c ,boolean Hasp){
	behindMe = a;
	inFrontOfMe = b;
	currentRail = c;
	hasPassengers = Hasp;
}

public void ModifyPrevRail (Rail r){
	
}
public void ModifyCol (Color a){
	myColor = a;
}

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
	public boolean HasPassenger(){// űlnek e a vonaton

		if(inFrontOfMe != null){
			if(inFrontOfMe.HasPassenger())			//rekurzivan megnezzuk, hogy az elotte ulo kocsik valamelyiken van e utas
				return true;						//ha van igazzal terunk vissza
		}
		if(hasPassengers)
			return true;							//ha nincs, megnezzuk hogy az utolso kocsin van e utas
		else
			return false;
	}
	public void setPreviousRail(Rail r){}
	
	public void setCurrentRail(Rail r){
		currentRail = r;
	}



	public boolean HasNextTrain(){
		return behindMe!=null;
		//ellenőrzii hogy van e mögötte vonat
	}
	public  void Move(){
		
    }

    public Color getMyColor() {
        return myColor;
    }
    
    public void setNeighbours(TrainPart t1, TrainPart t2){
    	inFrontOfMe = t1;
    	behindMe = t2;
    }
}
