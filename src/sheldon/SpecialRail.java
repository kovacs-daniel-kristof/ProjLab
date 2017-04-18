package sheldon;

import java.util.ArrayList;

public class SpecialRail extends Rail{
	boolean isActive = false;
	Rail[] originalRails = neighbours;
	int nextRailIndex = 0;

	public SpecialRail(){}

	@Override
	public Rail GetNextRail (Rail r){
		if (!isActive)				//nincs alagút
		{
			if(originalRails[0] == r)
				return originalRails[1];
			else
				return originalRails[0];
		}
		else						//van alagút
		{
			if (r == neighbours[0]) //jön ki az alagútból
				return originalRails[nextRailIndex];
			else					//megy be az alagútba
				return neighbours[0];
		}
	}
	@Override
	public void setNeighbours(Rail r1, Rail r2){
		neighbours[0] = r1;
		neighbours[1] = r2;
		originalRails = neighbours;
	}

	public void setNeighbours(ArrayList<Rail> railek, int allas){
		neighbours[0] = railek.get(0);
		neighbours[1] = railek.get(1);
		originalRails = neighbours;
	}

	public void Activate (){ // kapu lesz belĹ‘le
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
	}
	public void ChangeGate (){
		if(!HasTrain())
		{
			if (nextRailIndex == 0)
				nextRailIndex = 1;
			else
				nextRailIndex = 0;
		}
	}
}