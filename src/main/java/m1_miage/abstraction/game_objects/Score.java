package m1_miage.abstraction.game_objects;

public class Score {
    private long startTime= System.currentTimeMillis();
    private long deathTime=-1;
    private int kills=0;

    public void addKill(){
        kills++;
    }

    public void end(){
        deathTime=System.currentTimeMillis();
    }


    @Override
    public String toString() {
        return  getTime() +" s"+
                "\n\tpoints :" + kills;
    }

    private long getTime() {
        if(deathTime==-1) end();
        return (deathTime-startTime)/1000;
    }
}
