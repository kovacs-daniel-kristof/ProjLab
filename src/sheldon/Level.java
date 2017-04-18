package sheldon;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level {
	public ArrayList<Rail> rails = new ArrayList<Rail>();
	public ArrayList<TrainPart> trainparts = new ArrayList<TrainPart>();
	public UnderPassCreator u_p_c;

	public Level(String levelname, String trainname){
		int haspassanger = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(levelname))) {

			String CurrentLine;

			/*első paramétere a sín ID-ja
			második paramétere: A sín típusa (1-Rail, 2-SpecialRail, 3-Switch, 4- station, 5-Cross)
			harmadik paraméter: Szín (csak a stationnek lehet)
			negyedik paraméter: váltó iránya, ha nem váltó akkor 0;
			további paramétere: melyik sín van előtte
			további paramétere: melyik sín van utána
			*/
			ArrayList<String> readedrail = new ArrayList<String>();
			while ((CurrentLine = br.readLine()) != null) {

				readedrail.add(CurrentLine);
				String[] splitted = CurrentLine.split(",");
				Rail temprail = null;
				//létrehozzuk a Rail típúsokat,
				if(Integer.parseInt(splitted[1]) == 1){
					temprail = new Rail();
				}
				if(Integer.parseInt(splitted[1]) == 2){
					temprail = new SpecialRail();
				}
				if(Integer.parseInt(splitted[1]) == 3){
					temprail = new Switch();
				}
				if(Integer.parseInt(splitted[1]) == 4){
					temprail = new Station();
					temprail.changecolor(Color.valueOf(splitted[2]));
				}
				if(Integer.parseInt(splitted[1]) == 5){
					temprail = new Crossing();
				}

				rails.add(temprail);
			}
			for(int i = 0; i < readedrail.size(); i++){
				String[] splitted = readedrail.get(i).split(",");
				ArrayList<Rail> railek = new ArrayList<Rail>();
				for(int j = 4; j < splitted.length; j++)
					railek.add(rails.get(Integer.parseInt(splitted[j])));
				//beállítjuk a railek elöte, utána lévő Railjeit
				rails.get(i).setNeighbours(railek, Integer.parseInt(splitted[3]));
//
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(trainname))) {

			String CurrentLine;
			/*
			első paramétere: a mozdony Id-ja
			második paraméter: a mozdony melyik sínen van
			harmadik típusa: mi van előtte
			negyedik paraméter: mi van mögöttem
			ötödik paraméter: 1- Engine, 2- Carriage, 3 CoalCargo
			hatodik paraméter: szín
			hetedik paraméter: Van-e rajta utas  :  1-van, 0-nincs*/
			ArrayList<String> readed = new ArrayList<String>();
			while ((CurrentLine = br.readLine()) != null) {
				readed.add(CurrentLine);
				String[] splitted = CurrentLine.split(",");
					TrainPart train = null;
				if(Integer.parseInt(splitted[5]) == 1){//engine
					train = new Engine();
					train.myColor = Color.NONE;
					haspassanger++;
				}
				if(Integer.parseInt(splitted[5]) == 2){ //carriage
					train = new Carriage();
					train.myColor = Color.valueOf(splitted[5]);
					train.hasPassengers = Integer.parseInt(splitted[6]) == 1;
					if(Integer.parseInt(splitted[6]) == 0){
						haspassanger++;
					}
				}
				if(Integer.parseInt(splitted[5]) == 3){ //CoalCargo
					train = new CoalCargo();
					train.myColor = Color.NONE;
					haspassanger++;
				}
				train.currentRail = rails.get(Integer.parseInt(splitted[1]) - 1);
				rails.get(Integer.parseInt(splitted[1]) - 1).AddTrainToRail(train);



				train.inFrontOfMe = null;
				train.behindMe = null;
				trainparts.add(train);

			}
			//
			for(int i = 0; i < readed.size(); i++){
				String[] splitted = readed.get(i).split(",");
				if(Integer.parseInt(splitted[2]) != 0){
					trainparts.get(i).inFrontOfMe = trainparts.get(Integer.parseInt(splitted[2]) -1 );
				}
				if(Integer.parseInt(splitted[3]) != 0){
					trainparts.get(i).behindMe = trainparts.get(Integer.parseInt(splitted[3]) -1 );
				}
				if(Integer.parseInt(splitted[5]) == 1){//engine

					trainparts.get(i).setPreviousRail(trainparts.get(i).behindMe.currentRail);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		Program.Emptytraincount = haspassanger;
		u_p_c = new UnderPassCreator();
	}



	
	public void MoveEngines(){ // az összes mozdonynak meghívja a move függvényét ezzel mozgatja a vonatokat a sineken
		System.out.println("Engines are being moved");
		for(int i = 0; i < trainparts.size(); i++){
			trainparts.get(i).move();
		}
	}

	
	public int GetEngineCount(){
		return trainparts.size();
	}
}
