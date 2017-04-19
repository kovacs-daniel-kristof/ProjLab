package sheldon;

public class Carriage extends TrainPart{
	public Carriage(){
	}

	public void AskDetails(){
		System.out.print("ID: " + ID);
		System.out.print(" Type: Carriage ");
		if(behindMe != null)
			System.out.print(" b_t_ID: " + behindMe.ID);
		else
			System.out.print(" b_t_ID: null");
		if(inFrontOfMe != null)
			System.out.print(" in_front_ID: " + inFrontOfMe.ID);
		else
			System.out.print(" in_front_ID: null");
		if(currentRail != null)
			System.out.print(" Curr_R: " + currentRail.ID);
		else
			System.out.print(" Curr_R: null");
		System.out.println(" has ps : " + hasPassengers);
		/*if(hasPassengers){
			System.out.println(" has_passanger");
		}else{
			System.out.println();
		}*/
	}

public void MoveToRail (Rail next){ // következő sínre mozgat
	Rail tmp=currentRail; // tmpbe tároljuk azt a sínt ahonnan ellépünk
	currentRail=next; // a current rail az lesz ahová lépünk
	currentRail.AddTrainToRail(this); // az új sínen a vonatot is eltároljuk
	tmp.Clear(); // lelépünk az előző sínről
	if(HasNextTrain()){
		behindMe.MoveToRail(tmp);
		// ha van mögött kocsi akkor rekurzívan azon is meghívjuk a mozgatásta
	}
	boolean isSameColor = currentRail.CompareColors(myColor);//my color összehasonlítása


		//leszállás
	if(!inFrontOfMe.HasPassenger() && HasPassenger()){
			if(isSameColor){
				 hasPassengers= false;// leszállnak utasok
					Program.EmptyTrainCounterPlusPlus(); // egyel növeljük az üres kocsik számát

			}
		}
		//felszállás
		if(currentRail.hasWaitingPassengers()&&!hasPassengers){
			//akkor szállhatnak fel ha vannak várakozó utasok és a kocsi üres,

			if(currentRail.passengerGetOn(myColor)){    //ha szalltak fel utasok, akkor
				hasPassengers=true;
				Program.EmptyTrainCounterMinus();        //ures kocsik szamat levisszuk egyel
			}
		}



}
}
