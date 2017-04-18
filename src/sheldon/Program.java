package sheldon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TimerTask;

public class Program {
    static boolean gameHasStarted = false;
    public int LevelSzam = 9;
    public int currentLevel = 0;
    static int Emptytraincount = 0;
    public ArrayList<Level> levels = new ArrayList<Level>();
    static public Program m;


//    public class MoveTrains extends TimerTask {
//
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            Program.m.levels.get(Program.m.currentLevel).MoveEngines();
//        }
//
//
//    }

    static public void main (String[] args){
        m = new Program();
        m.Init();
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            while(true){
                System.out.println("Choose an action : ");
                if(gameHasStarted){
                    System.out.println("1 - Change Switch");
                    System.out.println("2 - Add Gate");
                    System.out.println("3 - Remove Gate");
                    System.out.println("4 - Change Gate");
                    System.out.println("5 - Move Engine and Move Carriage");
                }
                System.out.println("6 - Start Game");
                System.out.println("7 - Exit Game");
                System.out.println("8 - Level Complete(?)");
                System.out.println("9 - Switch Level");
                String line;
                line = br.readLine();
                int bela = 0;
                if(line == null) break;
                switch(line){
                    case "1":
                        if(gameHasStarted){
                            bela = 1;
                            for(Rail r : m.levels.get((m.currentLevel)).rails ){
                                r.AskID(bela);
                            }

                            System.out.println("Which Switch?");
                            line = br.readLine();
                            m.levels.get(m.currentLevel).rails.get(Integer.parseInt(line) -1).ChangeDirection();
                        }
                        break;
                    case "2":
                        if(gameHasStarted) {
                            bela = 2;
                            for(Rail r : m.levels.get((m.currentLevel)).rails ){
                                r.AskID(bela);
                            }
                            System.out.println("Which SpecialRail?");
                            line = br.readLine();
                            m.levels.get(m.currentLevel).u_p_c.AddGate((SpecialRail) m.levels.get(m.currentLevel).rails.get(Integer.parseInt(line) -1));
                        }
                        break;
                    case "3":
                        if(gameHasStarted) {
                            bela = 2;
                            for(Rail r : m.levels.get((m.currentLevel)).rails ){
                                r.AskID(bela);
                            }
                            System.out.println("Which Gate?");
                            line = br.readLine();
                            m.levels.get(m.currentLevel).u_p_c.RemoveGate((SpecialRail) m.levels.get(m.currentLevel).rails.get(Integer.parseInt(line) -1));
                        }
                        break;
                    case "4":
                        if(gameHasStarted) {
                            bela = 2;
                            for(Rail r : m.levels.get((m.currentLevel)).rails ){
                                r.AskID(bela);
                            }
                            System.out.println("Which Gate?");
                            line = br.readLine();
                            m.levels.get(m.currentLevel).rails.get(Integer.parseInt(line) -1).ChangeGate();
                        }
                        break;
                    case "5":
                        if(gameHasStarted)
                            m.levels.get(m.currentLevel).MoveEngines();
                        break;
                    case "6":
                        m.StartGame();
                        break;
                    case "7":
                        m.ExitGame();
                        break;
                    case "8":
                        if(gameHasStarted)
                            m.NextLevel();
                        break;
                    case "9":
                        System.out.println("Which Level");
                        line = br.readLine();
                        m.currentLevel = Integer.parseInt(line) -1;


                        break;
                }
            }
        }
        //
        catch(Exception e){

        }
    }

    public void Init (){
        //Pályák beolvasása


            for(int i = 1; i <= LevelSzam; i++){

                String levelname = "level" + i + ".txt";
                Level tmplevel = new Level(levelname);
                levels.add(tmplevel);
            }

    }

    public void ExitGame (){
        int engCo = levels.get(currentLevel).GetEngineCount();
        if(gameHasStarted && Emptytraincount == engCo){	//Ha jt�kban vagyunk m�g / elindult-e a j�t�k
            System.out.println("You Won!");
        }else{
            System.out.println("You Lost");
        }
        System.out.println("Press enter to quit");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);		//kilép
    }

    public void NextLevel (){
        int engCo = levels.get(currentLevel).GetEngineCount();
        if(Emptytraincount == engCo){
            System.out.println("You have completed the level!!! ^_^");
            currentLevel++;
            if(currentLevel == LevelSzam){
                System.out.println("You have completed all the levels! Well done!");
                System.out.println("Press any enter to quit!");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(1);		//kilép
            }else{
                StartGame();
            }
        }else{
            System.out.println("There are more trains to empty!");
        }
    }

    public void StartGame(){
        gameHasStarted = true;
//        Timer timer = new Timer();
//        timer.schedule(new MoveTrains(), 0, 500);
        
    }

    static public void GameOver (){
        System.out.println("Game ended, loser");
        gameHasStarted = false;
    }
    static public void EmptyTrainCounterPlusPlus (){
        Emptytraincount++;
        m.NextLevel();
    }

    static public void EmptyTrainCounterMinus(){
        Emptytraincount--;

    }
}
