package sheldon;

public class CoalCargo extends TrainPart {
    CoalCargo(){
        hasPassengers=false;
        // a coalCargon sose utaznak
        myColor =Color.NONE;
    }

    public void AskDetails(){
        System.out.print("ID: " + ID);
        System.out.print(" Type: CoalCargo ");
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

}