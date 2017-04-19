package sheldon;

public class Engine extends TrainPart {

    protected Rail previousRail;
    protected int startAt;

public Engine(){
hasPassengers=false;
}



    public void ModifyPrevRail (Rail r){
        previousRail = r;
    }
	public void MoveToRail(Rail next) {

	}
	public void StartSet (int a){
    startAt = a;
    }

	public void Move (){ // mivel a Train Part specializációja ezért a current rail segítségével el tudja dönteni hogy hova kell lépnie
        Rail nextrail;

        Rail tmp = currentRail;
        nextrail=currentRail.GetNextRail(previousRail); // elmentjük a következő sínt
        tmp.Clear();//lelépünk a sínről
        previousRail=currentRail;
        currentRail=nextrail; // a mozdonynak is megadjuk hogy melyik sinen áll


        if(HasNextTrain()){
            behindMe.MoveToRail(tmp); // ha van mögötte kocsi azon meghívjuk a mozgatást
        }
        currentRail.AddTrainToRail(this); // a következő sínhez beállítjuk a vonatot ráléptetjük

	}//

    public void AskDetails(){
        System.out.print("ID: " + ID);
        System.out.print(" Type: Engine ");
        if(behindMe != null)
            System.out.print(" b_t_ID: " + behindMe.ID);
        else
            System.out.print(" b_t_ID: null");
        if(inFrontOfMe != null)
            System.out.print(" in_front_ID: " + inFrontOfMe.ID);
        else
            System.out.print(" in_front_ID: null");
        if(currentRail != null)
            System.out.println(" Curr_R: " + currentRail.ID);
        else
            System.out.println(" Curr_R: null");
    }
   
	public void setPreviousRail(Rail r){ previousRail = r;}
}
