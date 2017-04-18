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

	public void setNeighbours(ArrayList<Rail> railek, int allas){
		neighbours[0] = railek.get(0);
		railek.remove(0);
		junctions = railek;
		n = allas;
		neighbours[1] = junctions.get(n);

	}


}
