package sheldon;

public class CoalCargo extends TrainPart {
    CoalCargo(){
        hasPassengers=false;
        // a coalCargon sose utaznak
        myColor =Color.NONE;
    }

    public void AskDetails(){
        System.out.println("ID: " + ID + " Type: CoalCargo " + " b_t_ID: " + behindMe.ID + " in_front_ID: " + inFrontOfMe.ID + " Curr_R: " + currentRail.ID);
    }

}