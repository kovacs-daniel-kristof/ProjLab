package sheldon;


import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level {
	public ArrayList<Rail> rails = new ArrayList<Rail>();
	public ArrayList<TrainPart> trainparts = new ArrayList<TrainPart>();
	public UnderPassCreator u_p_c;

	public Level(String levelname){
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
				TrainPart train = null;
				boolean RailCreated = true;
				
				int ID__ = Integer.parseInt(splitted[0]);
				
				switch(Integer.parseInt(splitted[1])){	//TYPE
				case 0:
					temprail = new Rail();
					temprail.ID_des(ID__);
					break;
				case 1:
					temprail = new Station();
					temprail.changecolor(Color.valueOf(splitted[2]));
					temprail.ID_des(ID__);
					break;
				case 2:
					temprail = new Switch();
					temprail.ID_des(ID__);
					break;
				case 3:
					temprail = new SpecialRail();
					temprail.ID_des(ID__);
					break;
				case 4:
					temprail = new Crossing();
					temprail.ID_des(ID__);
					break;
				case 5:
					train = new Engine();
					/*train.myColor = Color.NONE;
					haspassanger++;*/
					 RailCreated = false;
					 train.ID_des(ID__);
					break;
				case 6:
					train = new Carriage();
					/*train.myColor = Color.valueOf(splitted[5]);
					train.hasPassengers = Integer.parseInt(splitted[6]) == 1;
					if(Integer.parseInt(splitted[6]) == 0){
						haspassanger++;
					}*/
					 RailCreated = false;
					 train.ID_des(ID__);
					break;
				case 7:
					train = new CoalCargo();
					/*train.myColor = Color.NONE;
					haspassanger++;*/
					RailCreated = false;
					 train.ID_des(ID__);
					break;
				}
				
				if(RailCreated){
					rails.add(temprail);
				}else{
					trainparts.add(train);
				}
			}
			} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(levelname))) {
			String CurrentLine;
			ArrayList<String> readed = new ArrayList<String>();
			while ((CurrentLine = br.readLine()) != null) {
				readed.add(CurrentLine);
				String[] splitted = CurrentLine.split(",");
				TrainPart train = null;
				Rail temprail = null;	
				
				if(		Integer.parseInt(splitted[1]) == 0 || 
						Integer.parseInt(splitted[1]) == 1 ||
						Integer.parseInt(splitted[1]) == 2 ||
						Integer.parseInt(splitted[1]) == 3 ||
						Integer.parseInt(splitted[1]) == 4){
					for(Rail r : rails){											//visszakeressük a sínt
						if(r.ID_rep() == Integer.parseInt(splitted[0])){
							temprail = r;
							break;
						}
					}
					Rail egy = null;
					Rail ketto = null;
					for(Rail r : rails){
						if(r.ID_rep() == Integer.parseInt(splitted[2])){			//Elso szomszed
							egy = r;
						}
						if(r.ID_rep() == Integer.parseInt(splitted[3])){			///masodik szomszed
							ketto = r;
						}
					}
					temprail.setNeighbours(egy, ketto);
					if(Integer.parseInt(splitted[4]) == 1){				//ha van rajta vonat
						TrainPart a = null;
						for(TrainPart t : trainparts){
							if(t.ID_rep() == Integer.parseInt(splitted[5])){//by ID of train
								a = t;
								break;
							}
						}
						temprail.AddTrainToRail(a);
					}
					//EDDDIG TART A SIMA RAIL
					if(	Integer.parseInt(splitted[1]) == 1){		//station
						temprail.changecolor(Color.valueOf(splitted[6]));
						ArrayList<Color> wp = new ArrayList<Color>();
						for(int i = 0; i < Integer.parseInt(splitted[7]); i++){
							wp.add(Color.valueOf(splitted[8+i]));
						}
						temprail.setWaitingPassengers(wp);
					}
					
					if(	Integer.parseInt(splitted[1]) == 2){		//switch
						ArrayList<Rail> junctionz = new ArrayList<Rail>();
						for(int i = 0; i < Integer.parseInt(splitted[6]); i++){
							Rail h = null;
							for(Rail r : rails){
								if(r.ID_rep() == Integer.parseInt(splitted[7+i])){
									h = r;
									break;
								}
							}
							junctionz.add(h);
						}
						temprail.setNeighbourSwitch(junctionz);
					}
					
					if(	Integer.parseInt(splitted[1]) == 3){//specialrail
						temprail.Activate();
					}
					if(	Integer.parseInt(splitted[1]) == 4){//Crossing
						Rail harom = null;
						Rail negy = null;
						for(Rail r : rails){
							if(r.ID_rep() == Integer.parseInt(splitted[6])){			//Elso szomszed
								harom = r;
							}
							if(r.ID_rep() == Integer.parseInt(splitted[7])){			///masodik szomszed
								negy = r;
							}
						}
						temprail.setNeighbours2(harom, negy);
					}
					
					
				}
				if(		Integer.parseInt(splitted[1]) == 5 || 
						Integer.parseInt(splitted[1]) == 6 ||
						Integer.parseInt(splitted[1]) == 7){
					TrainPart egy = null;
					TrainPart ketto = null;
					for(TrainPart r : trainparts){											//visszakeressük a sínt
						if(r.ID_rep() == Integer.parseInt(splitted[0])){
							train = r;
						}
						if(splitted[2] != "null"){
							if(r.ID_rep() == Integer.parseInt(splitted[2])){
								train = egy;
							}
						}
						if(splitted[3] != "null"){
							if(r.ID_rep() == Integer.parseInt(splitted[3])){
								train = ketto;
							}	
						}
					}
					boolean haspo = false;
					if(Integer.parseInt(splitted[5]) == 1) haspo = true;
					Rail harom = null;
					for(Rail r : rails){
						if(r.ID_rep() == Integer.parseInt(splitted[4])){			//Elso szomszed
							harom = r;
						}
					}
					
					train.StandardStartup(egy, ketto,harom, haspo);	//behind_T_ID, in_Front_T_ID, curr_R_ID, hasPass(bool)
					if(	Integer.parseInt(splitted[1]) == 5){//Engine
						if(Integer.parseInt(splitted[5]) == 1) haspo = true;
						Rail negy = null;
						for(Rail r : rails){
							if(r.ID_rep() == Integer.parseInt(splitted[4])){			//Elso szomszed
								negy = r;
							}
						}
						train.ModifyPrevRail(negy);
					}
					
					if(	Integer.parseInt(splitted[1]) == 6){//Carriage
						train.ModifyCol(Color.valueOf(splitted[5]));
					}
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
			trainparts.get(i).Move();
		}
	}

	
	public int GetEngineCount(){
		return trainparts.size();
	}
}

//public class Level {
//	public ArrayList<Rail> rails = new ArrayList<Rail>();
//	public ArrayList<TrainPart> trainparts = new ArrayList<TrainPart>();
//	public UnderPassCreator u_p_c;
//
//	public Level(String levelname, String trainname){
//		
//		Rail r0 = new Rail("r0");
//		Rail r1 = new Rail("r1");
//		Rail r2 = new Switch("r2");
//		Rail r3 = new Rail("r3");
//		Rail r4 = new Rail("r4");
//		Rail r5 = new Rail("r5");
//		
//		
//		r0.setNeighbours(null, r1);
//		r1.setNeighbours(r0, r2);
//		r3.setNeighbours(r2, null);
//		r4.setNeighbours(r2, null);
//		r5.setNeighbours(r2, null);
//		
//		ArrayList<Rail> junctions = new ArrayList<Rail>();
//		junctions.add(r1);
//		junctions.add(r3);
//		junctions.add(r4);
//		junctions.add(r5);
//		
//		TrainPart t1 = new Engine();
//		TrainPart t2 = new Carriage();
//
//
//		r2.setNeighbours(junctions, 0);
//
//		r1.AddTrainToRail(t1);
//		r0.AddTrainToRail(t2);
//
//		
//		t1.setPreviousRail(r0);
//		t1.setCurrentRail(r1);
//		t1.setNeighbours(null, t2);
//		
//		t2.setCurrentRail(r0);
//		t2.setNeighbours(t1, null);
//
//
//		rails.add(r0);
//		rails.add(r1);
//		rails.add(r2);
//		rails.add(r3);
//		rails.add(r4);
//		rails.add(r5);
//		
//		trainparts.add(t1);
//		trainparts.add(t2);

		
		
//		Rail r1 = new Rail("r1");
//		Rail r2 = new Rail("r2");
//		Rail r3 = new Rail("r3");
//		Rail r4 = new Rail("r4");
//		Rail r5 = new Rail("r5");
//		
//		TrainPart t1 = new Engine();
//		TrainPart t2 = new Carriage();
//		TrainPart t3 = new Carriage();
//		
//		r1.setNeighbours(null, r2);
//		r2.setNeighbours(r1, r3);
//		r3.setNeighbours(r2, r4);
//		r4.setNeighbours(r3, r5);
//		r5.setNeighbours(r4, null);
//		r3.AddTrainToRail(t1);
//		r2.AddTrainToRail(t2);
//		r1.AddTrainToRail(t3);
//		
//		t1.setPreviousRail(r2);
//		t1.setCurrentRail(r3);
//		t1.setNeighbours(null, t2);
//		
//		t2.setCurrentRail(r2);
//		t2.setNeighbours(t1, t3);
//		
//		t3.setCurrentRail(r1);
//		t3.setNeighbours(t2, null);
//		
//		rails.add(r1);
//		rails.add(r2);
//		rails.add(r3);
//		rails.add(r4);
//		rails.add(r5);
//		
//		trainparts.add(t1);
//		trainparts.add(t2);
//		trainparts.add(t3);
//		
//		
//
//	}



	
//	public void MoveEngines(){ // az összes mozdonynak meghívja a move függvényét ezzel mozgatja a vonatokat a sineken
//		System.out.println("/n/na mozdony jelenlegi sinje: " + trainparts.get(0).currentRail.name);
//		System.out.println("kocsi jelenlegi sinje: " + trainparts.get(1).currentRail.name);
//		System.out.println("Engines are being moved");
//		for(int i = 0; i < trainparts.size(); i++){
//			trainparts.get(i).Move();
//			
//		}
//		System.out.println("a mozdony jelenlegi sinje: " + trainparts.get(0).currentRail.name);
//		System.out.println("kocsi jelenlegi sinje: " + trainparts.get(1).currentRail.name);
//	}
//
//	
//	public int GetEngineCount(){
//		return trainparts.size();
//	}
//}
