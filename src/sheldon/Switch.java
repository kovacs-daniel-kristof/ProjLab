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
		}else{
			System.out.println("Can not change switch.");
		}
	}


	public void setNeighbourSwitch( ArrayList<Rail> jc){
		junctions = jc;
	}

	public void setNeighbours(ArrayList<Rail> railek, int allas){
		neighbours[0] = railek.get(0);
		railek.remove(0);
		junctions = railek;
		n = allas;
		neighbours[1] = junctions.get(n);

	}
	@Override
	public void AskID (int a){
		if(a == 0 || a == 1) {
			System.out.println("Switch, ID : " + ID);
		}
	}


}
