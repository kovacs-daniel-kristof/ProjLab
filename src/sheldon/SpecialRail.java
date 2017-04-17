package sheldon;

public class SpecialRail extends Rail{
	public SpecialRail(){
	}
boolean isActive = false;

public void Activate (){ // kapu lesz belőle
	System.out.println("SpecialRail has been activatedo!");
	isActive = true;
}
public void Deactivate (){ // visszaáll special railba
	System.out.println("SpecialRail has been deactivated!");
	isActive = false;
}
public boolean getActive (){
	System.out.println("SpecialRail is :" + isActive);
	return isActive;
}
public void NoOtherTunnel (){
	System.out.println("There's no tunnel");
}
public void ChangeGate (){
	if(HasTrain()){
		System.out.println("So i cannot be changed :/ - told the special rail to you, with a grim smile on its face");
	}else{
		System.out.println("Upcoming trains will face the other way from now on! - muhaha - says the special rail!");
	}
}
}
