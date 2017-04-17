package sheldon;

public class Carriage extends TrainPart{
	public Carriage(){
	}

public void MoveToRail (Rail next){ // következő sínre mozgat
	Rail tmp=currentRail; // tmpbe tároljuk azt a sínt ahonnan ellépünk
	currentRail=next; // a current rail az lesz ahová lépünk
	currentRail.AddTrainToRail(this); // az új sínen a vonatot is eltároljuk
	tmp.Clear(); // lelépünk az előző sínről
	if(HasNextTrain()){
		MoveToRail(tmp);
		// ha van mögött kocsi akkor rekurzívan azon is meghívjuk a mozgatásta
	}
	boolean isSameColor = currentRail.CompareColors(myColor);//my color összehasonlítása
		//leszállás
	if(!inFrontOfMe.HasPassenger() && HasPassenger()){
			if(isSameColor){
				System.out.println("I'm at a station, and the colors are matching - told by a carriage");
				System.out.println("So passangers, get off.. - shouted the carriage");
				 hasPassengers= false;// leszállnak utasok
					Program.EmptyTrainCounterPlusPlus(); // egyel növeljük az üres kocsik számát

			}
		}
		//felszállás
		if(currentRail.hasWaitingPassengers()&&!hasPassengers&&currentRail.isWaitingPassengerSameColor(this)
				&&currentRail.RandomGetOn()){
			//akkor szállhatnak fel ha vannak várakozó utasok és a kocsi üres,
			// és a várakozó utasok közül van olyan akinek a szine megegyezik a kocsi szinével
			// és az utasnak van kedve felszállni
			hasPassengers=true;
			Program.EmptyTrainCounterMinus();
		}



}
}
