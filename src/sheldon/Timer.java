package sheldon;

/**
 * Created by Dániel on 2017. 04. 17..
 */
import java.util.TimerTask;


public class Timer extends TimerTask {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        Program.m.levels.get(Program.m.currentLevel).MoveEngines();
    }


}
