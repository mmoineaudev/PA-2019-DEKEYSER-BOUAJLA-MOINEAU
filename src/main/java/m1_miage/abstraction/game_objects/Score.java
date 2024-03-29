package m1_miage.abstraction.game_objects;

public class Score {
    private long startTime= System.currentTimeMillis();
    private long deathTime=-1;
    private int points =0;

    public void addPoint(){
        points++;
    }

    public void end(){
        deathTime=System.currentTimeMillis();
    }


    @Override
    public String toString() {
        return  getTime() +" s"+
                "; points :" + points;
    }

    public long getTime() {
        if(deathTime==-1) end();
        return (deathTime-startTime)/1000;
    }

    public int getPoints() {
        return points;
    }
}
