package sheldon;

import java.util.ArrayList;

public class UnderPassCreator {

	ArrayList<SpecialRail> underPassEdges = new ArrayList<SpecialRail>();
	ArrayList<Rail> railList = new ArrayList<Rail>();
	int temporaryUPLength = 4; //amíg nem lehet a gui alapján távolságot számolni, ilyen hosszú lesz az alagút; minimum 1-nek kell lennie!

	public UnderPassCreator(){}


	public void Generate () //amikor egyik special rail se aktĂ­v akkor a special rail-bĹ‘l kapu lesz
	{

		for (int i = 0; i < temporaryUPLength; i++) //létrehozza az alagútban levő síneket
		{
			railList.add(new Rail());
		}
		if (railList.size() == 1)
			railList.get(0).setNeighbours(underPassEdges.get(0), underPassEdges.get(1));
		else if (railList.size() == 2)
		{
			railList.get(0).setNeighbours(underPassEdges.get(0), railList.get(1));
			railList.get(1).setNeighbours(railList.get(0), underPassEdges.get(1));
		}
		else
		{
			railList.get(0).setNeighbours(underPassEdges.get(0), railList.get(1));
			for (int i = 1; i < railList.size()-1; i++) // beállítja a sínek szomszédait
			{
				railList.get(i).setNeighbours(railList.get(i-1), railList.get(i+1));
			}
			railList.get(railList.size()-1).setNeighbours(railList.get(railList.size()-2), underPassEdges.get(1));
		}
		underPassEdges.get(0).setNeighbours(railList.get(0), null);
		underPassEdges.get(1).setNeighbours(railList.get(railList.size()-1), null);

	}

	public void AddGate (SpecialRail newSpecialRail){
		int railsActive = GetSpecialRailActive();
		if(railsActive == 0)	//amikor egyik special rail se aktĂ­v akkor a special rail-bĹ‘l kapu lesz
		{
			underPassEdges.add(newSpecialRail);
		}
		if(railsActive == 1) // ha mĂˇr van egy kapu akkor is krelĂłdik mĂ©g egy, ezutĂˇn pedig lĂ©trejĂ¶n az alagĂşt a kĂ©t kapu kĂ¶zĂ¶tt
		{
			underPassEdges.add(newSpecialRail);
			Generate();
			underPassEdges.get(0).Activate();
			underPassEdges.get(1).Activate();
			// ha mĂˇr 2 kapu van akkor nem tud tovĂˇbbit hozzĂˇadni
		}
	}
	public int GetSpecialRailActive (){

		return underPassEdges.size();// megadja hogy jelenleg hĂˇny special rail viselkedik kapukĂ©nt
	}
	public void RemoveGate (SpecialRail specialRailToRemove){
		int gates = GetSpecialRailActive();
		if (gates == 1)
		{
			underPassEdges.remove(specialRailToRemove);
		}
		if(gates == 2 && !UPhasTrain())
		{
			underPassEdges.get(0).Deactivate();
			underPassEdges.get(1).Deactivate();
			underPassEdges.remove(specialRailToRemove);
			Remove();
		}

	}
	public boolean UPhasTrain(){// amĂ­g van vonat addig nem szĹ±nhet meg az alagĂşt
		boolean hastrain = false;
		for (int i=0; i < railList.size(); i++)
		{
			if (railList.get(i).HasTrain())
				hastrain = true;
		}
		if (underPassEdges.get(0).HasTrain()|| underPassEdges.get(1).HasTrain())
			hastrain = true;
		return hastrain;
	}
	public void Remove ()
	{
		railList.clear();
	}
}