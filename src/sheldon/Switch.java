package sheldon;

import java.util.ArrayList;

public class Switch extends Rail {
	private ArrayList<Rail> junctions;
	private int n;

	public Switch(){
		n=0;
	}
	public void ChangeDirection(){
		if(!this.HasTrain()){
			if(n < junctions.size()-1)
				n++;
			else
				n = 0;

			neighbours[1] = junctions.get(n);
		}
	}

	public void setNeighbourSwitch(Rail nb1, ArrayList<Rail> jc){
		junctions = jc;
		neighbours[0] = nb1;
		neighbours[1] = junctions.get(0);
		n = 0;
	}

}

