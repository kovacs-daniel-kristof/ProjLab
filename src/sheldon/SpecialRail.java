package sheldon;

import java.util.ArrayList;

public class SpecialRail extends Rail{
	boolean isActive = false;
	Rail[] originalRails = neighbours;
	//int nextRailIndex = 0;
boolean firstset = false;
	public SpecialRail(){}

	/*@Override
	public Rail GetNextRail (Rail r){

		if (!isActive)				//nincs alagút
		{
			if(originalRails[0] == r) {
				System.out.println("ori iD 0: " + originalRails[0] + " ori iD 1: "+ originalRails[1]);
				return originalRails[1];
			}
			else {
				System.out.println("ori iD 0: " + originalRails[0] + " ori iD 1: "+ originalRails[1]);
				return originalRails[0];
			}
		}
		else						//van alagút
		{
			if (r == neighbours[0]) {//jön ki az alagútból
				System.out.println("isactive true ori iD 0: " + originalRails[0] + " ori iD 1: " + originalRails[1]);
				return originalRails[nextRailIndex];
			}
			else{                    //megy be az alagútba
			System.out.println(" isactive true ori iD 0: " + originalRails[0] + " ori iD 1: " + originalRails[1]);
			return neighbours[0];
		}
		}
	}*/
	/*@Override
	public void setNeighbours(Rail r1, Rail r2){
		neighbours[0] = r1;
		neighbours[1] = r2;
		/*if(!firstset){
			//originalRails = neighbours;
			originalRails[0] = r1;
			originalRails[1] = r2;
			firstset = true;
		}
	}*/


	@Override
	public void AskID (int a){
		if(a == 0 || a == 2) {
			System.out.println("SpecialRail, ID : " + ID);
		}
	}

	/*public void setNeighbours(ArrayList<Rail> railek, int allas){
		neighbours[0] = railek.get(0);
		neighbours[1] = railek.get(1);
		//originalRails = neighbours;
	}*/
	public void setORback () {
		neighbours[0] = originalRails[0];
		neighbours[1] = originalRails[1];
	}
	public void setOR(Rail a, Rail b){
		originalRails[0] = a;
		originalRails[1] = b;
	}
	/*public void Activate (){ // kapu lesz belĹ‘le
		isActive = true;
	}
	public void Deactivate (){ // visszaĂˇll special railba
		isActive = false;
	}
	public boolean getActive (){
		return isActive;
	}
	public void NoOtherTunnel (){
		// ez itt fölösleges
	}*/
	public void ChangeGate (){
		if(!HasTrain())
		{
			if(neighbours[0].equals(originalRails[0])) {
				neighbours[0] = originalRails[1];
			}
			if(neighbours[0].equals(originalRails[1])) {
				neighbours[0] = originalRails[0];
			}

			/*if (nextRailIndex == 0)
				nextRailIndex = 1;
			else
				nextRailIndex = 0;
			}*/
		}
	}
}