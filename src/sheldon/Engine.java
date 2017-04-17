package sheldon;

public class Engine extends TrainPart {

    protected Rail previousRail;

public Engine(){
hasPassengers=false;
}

	public void MoveToRail(Rail next) {

	}

	public void Move (){ // mivel a Train Part specializációja ezért a current rail segítségével el tudja dönteni hogy hova kell lépnie
        Rail nextrail;
        Rail tmp = currentRail;
        nextrail=currentRail.GetNextRail(previousRail); // elmentjük a következő sínt
        tmp.Clear();//lelépünk a sínről
        previousRail=currentRail;
        currentRail=nextrail; // a mozdonynak is megadjuk hogy melyik sinen áll


        if(HasNextTrain()){
            MoveToRail(tmp); // ha van mögötte kocsi azon meghívjuk a mozgatást
        }
        currentRail.AddTrainToRail(this); // a következő sínhez beállítjuk a vonatot ráléptetjük

	}


    public void setPreviousRail(Rail r){
        previousRail = r;
	}
}
