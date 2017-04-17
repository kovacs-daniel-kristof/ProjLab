package sheldon;

import java.util.ArrayList;
import java.util.Random;

public class Station extends Rail {
	private Color myColor;
	private ArrayList<Color> waitingPassengers;

	public Station(){
	}
	public boolean CompareColors(Color c){
		return c.equals(myColor);
	}

	public void changecolor (Color c){
		myColor = c;
	}

	public void setWaitingPassengers(ArrayList<Color> wp){
		waitingPassengers = wp;
	}

	public boolean hasWaitingPassengers(){
		if(waitingPassengers.size()>0)
			return true;
		else
			return false;
	}

public boolean passengerGetOn(Color c) {   //a c szinu kocsira szallank e fel utasok. True a visszateres ha felszalltak utasok, false ha nem
    boolean ret = false;

    for(int i =0; i<waitingPassengers.size();i++){  //megnezzuk hogy a varakozok kozul ki szallhat fel. Mindenkepp vegig nezzuk a listat, mert tobben is felszallhatnak, de a visszateresi erteknel ezt nem kell jelolni
        if(waitingPassengers.get(i).equals(c)){

            if(RandomGenerate()){                      //random generator, hogy felszall-e
                waitingPassengers.remove(i);        //ha felszallt, akkor kivesszuk a varakozok kozul
                ret = true;
            }
        }
    }
    return ret;
}

	public boolean RandomGenerate(){  //random generator
		Random rand = new Random();
		int n = rand.nextInt(10);
		if(n>=5)
			return true;
		else
			return  false;

	}


}

