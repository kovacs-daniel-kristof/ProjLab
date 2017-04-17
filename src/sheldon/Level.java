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
			második paramétere: melyik sín van előtte
			harmadik paramétere: melyik sín van utána
			negyedik paramétere: A sín típusa (1-Rail, 2-SpecialRail, 3-Switch, 4- station, 5-Cross)
			ötödik paraméter: Szín (csak a stationnek lehet)*/

			while ((CurrentLine = br.readLine()) != null) {
				String[] splitted = CurrentLine.split(" ");
				Rail temprail = null;
				if(Integer.parseInt(splitted[3]) == 1){
					temprail = new Rail();
				}
				if(Integer.parseInt(splitted[3]) == 2){
					temprail = new SpecialRail();
				}
				if(Integer.parseInt(splitted[3]) == 3){
					temprail = new Switch();
				}
				if(Integer.parseInt(splitted[3]) == 4){
					temprail = new Station();
					temprail.changecolor(Color.valueOf(splitted[4]));
				}
				if(Integer.parseInt(splitted[3]) == 5){
					temprail = new Crossing();
				}
				temprail.neighbours[0] = rails.get(Integer.parseInt(splitted[1]) -1);
				temprail.neighbours[1] = rails.get(Integer.parseInt(splitted[2]) -1);
				rails.add(temprail);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(levelname))) {

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
				String[] splitted = CurrentLine.split(" ");
					TrainPart train = null;
				if(Integer.parseInt(splitted[5]) == 1){//engine
					train = new Engine();
				}
				if(Integer.parseInt(splitted[5]) == 2){ //carriage
					train = new Carriage();

				}
				if(Integer.parseInt(splitted[5]) == 3){ //CoalCargo
					train = new CoalCargo();
				}
				train.currentRail = rails.get(Integer.parseInt(splitted[1]) - 1);
				if(Integer.parseInt(splitted[6]) == 1){
					haspassanger++;
				}
				train.hasPassengers = Integer.parseInt(splitted[6]) == 1;
				train.myColor = Color.valueOf(splitted[5]);
				train.inFrontOfMe = null;
				train.behindMe = null;
				trainparts.add(train);

			}
			for(int i = 0; i < readed.size(); i++){
				String[] splitted = readed.get(i).split(" ");
				if(Integer.parseInt(splitted[2]) != 0){
					trainparts.get(i).inFrontOfMe = trainparts.get(Integer.parseInt(splitted[2]) -1 );
				}
				if(Integer.parseInt(splitted[3]) != 0){
					trainparts.get(i).behindMe = trainparts.get(Integer.parseInt(splitted[3]) -1 );
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		Program.Emptytraincount = haspassanger;
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
