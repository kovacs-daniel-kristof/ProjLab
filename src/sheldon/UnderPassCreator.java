package sheldon;

import java.util.ArrayList;

public class UnderPassCreator {

	ArrayList<SpecialRail> underPassEdges = new ArrayList<SpecialRail>();
	ArrayList<Rail> railList = new ArrayList<Rail>();
	int temporaryUPLength = 4; //amíg nem lehet a gui alapján távolságot számolni, ilyen hosszú lesz az alagút; minimum 1-nek kell lennie!

	public UnderPassCreator(){}


	public void Generate () //amikor egyik special rail se aktĂ­v akkor a special rail-bĹ‘l kapu lesz
	{
		System.out.println("Creating...");
		for (int i = 0; i < temporaryUPLength; i++) //létrehozza az alagútban levő síneket
		{
			railList.add(new Rail());
		}
		underPassEdges.get(0).setNeighbours(underPassEdges.get(0).neighbours[0],railList.get(0));	//udnerpassEdgesnek 0as szomszédját kapja + railList elso elemét
		underPassEdges.get(1).setNeighbours(underPassEdges.get(1).neighbours[0],railList.get(temporaryUPLength-1));

		for(int i = 0; i < temporaryUPLength; i++){
			if(i == 0) {
				railList.get(i).setNeighbours(underPassEdges.get(0),railList.get(1));
			}else {
				if(i == temporaryUPLength-1) {
					railList.get(i).setNeighbours(underPassEdges.get(1),railList.get(temporaryUPLength-1));
				}else {
					railList.get(i).setNeighbours(railList.get(i - 1), railList.get(i + 1));
				}
			}

		}

		/*if (railList.size() == 1)
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
		underPassEdges.get(1).setNeighbours(railList.get(railList.size()-1), null);*/

	}

	public void AddGate (SpecialRail newSpecialRail){
		int railsActive = underPassEdges.size();
		if(railsActive == 0)	//amikor egyik special rail se aktĂ­v akkor a special rail-bĹ‘l kapu lesz
		{
			underPassEdges.add(newSpecialRail);
		}
		if(railsActive == 1) // ha mĂˇr van egy kapu akkor is krelĂłdik mĂ©g egy, ezutĂˇn pedig lĂ©trejĂ¶n az alagĂşt a kĂ©t kapu kĂ¶zĂ¶tt
		{
			if(!underPassEdges.get(0).equals(newSpecialRail)) {
				underPassEdges.add(newSpecialRail);
				Generate();
			}
			//underPassEdges.get(0).Activate();
			//underPassEdges.get(1).Activate();
			// ha mĂˇr 2 kapu van akkor nem tud tovĂˇbbit hozzĂˇadni
		}
	}

	public void RemoveGate (SpecialRail specialRailToRemove){
		int gates =  underPassEdges.size();
		if (gates == 1)
		{
			underPassEdges.remove(specialRailToRemove);
		}
		if(gates == 2 && !UPhasTrain())
		{
			System.out.println("Destroying...");
			//underPassEdges.get(0).Deactivate();
			//underPassEdges.get(1).Deactivate();
			Remove();
			underPassEdges.remove(specialRailToRemove);
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
		underPassEdges.get(0).setORback();
		underPassEdges.get(1).setORback();
		railList.clear();
	}
}