package sheldon;

public class Station extends Rail {
	public Station(){
	}
Color myColor;

public boolean CompareColors (){
	System.out.println("Yes the color is matching! We both swept right ;) - claimed the station to the carriage");
	return true;
}
@Override
public boolean GetOffTheTrain (){
	System.out.println("The passangers can get off this carriage! - it is mine, not yours. - ordered the station, to the astonished passangers");
	return true;
}

public boolean CanGetOn(){
	//ádám írd meg a logikáját 0 vagy 1 ret ad random
	return true;
}

public void changecolor (Color c){
myColor = c;
}

}
