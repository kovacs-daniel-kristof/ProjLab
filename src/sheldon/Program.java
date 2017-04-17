package sheldon;
import java.io.*;
import java.util.ArrayList;



/*
 * Change Switch
1.1 van a switchen vonat ? Y/N
Add Gate
2.1 van a special railen vonat ? Y/N
2.2 H�ny kapu van lerakva ? 0/1/2
3. Remove Gate
3.1 van akt�v alag�t ? Y/N
3.2 van vonat az alag�tban ? Y/N
4. Move Engine
4.1 van e �tk�z�s ? Y/N
5. Move Carriage
5.1 �llom�son �ll? Y/N
5.2 A kocsi sz�ne megegyezik az �llom�s sz�n�vel ?  Y/N
5.3 Lesz�lltak  m�r az el�z� kocsikr�l? Y/N
5.4 Van utas a kocsin ? Y/N
5.5 van m�g�tte kocsi ? Y/N
6. Start Game
7.Exit Game
7.1 Nyert a j�t�kos ? Y/N
8. Level Complete
8.1 Van k�vetkez� p�lya? Y/N
 */

public class Program {
    static boolean gameHasStarted = false;
    public int LevelSzam = 3;
    public int currentLevel = 0;
    static int Emptytraincount = 0;
    public ArrayList<Level> levels = new ArrayList<Level>();
    static public Program m;
    //public ArrayList<Level> levelList = new ArrayList<Level>();

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
                String line;
                line = br.readLine();
                if(line == null) break;
                switch(line){
                    case "1":
                        if(gameHasStarted)
                            m.levels.get(m.currentLevel).rails.get(3).ChangeDirection();
                        break;
                    case "2":
                        if(gameHasStarted)
                            m.levels.get(m.currentLevel).u_p_c.AddGate();
                        break;
                    case "3":
                        if(gameHasStarted)
                            m.levels.get(m.currentLevel).u_p_c.RemoveGate();
                        break;
                    case "4":
                        m.levels.get(m.currentLevel).rails.get(5).ChangeGate();
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
                }
            }
        }
        catch(Exception e){

        }
    }

    public void Init (){
        //Pályák beolvasása
        try{
            for(int i = 1; i <= LevelSzam; i++){

                String levelname = "level" + i + ".txt";
                String trainname = "train" + i + ".txt";
                Level tmplevel = new Level(levelname, trainname);
                levels.add(tmplevel);
            }


        }catch(Exception e){

        }
    }

    public void ExitGame (){
        int engCo = levels.get(currentLevel).GetEngineCount();
        if(gameHasStarted && Emptytraincount == engCo){	//Ha j�t�kban vagyunk m�g / elindult-e a j�t�k
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
