import java.util.ArrayList;
public abstract class Agent{
    protected int x;
    protected int y;
    protected String type;
    private static ArrayList<Agent> l = new ArrayList<Agent>();
    public Agent(int x,int y,String type){
        this.x=x;
        this.x=y;
        this.type=type;
        l.add(this);
    }
    public Agent(String type){
        this((int)(Math.random()*10),(int)(Math.random()*10),type);
    }
    public void seDeplacer(int xnew,int ynew){
        for(Agent a:l){
            if(a.x==xnew && a.y==ynew)return;
        }
        x=xnew;
        y=ynew;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public double distance(int x,int y){
        return Math.sqrt((this.x-x)*(this.x-x)+((this.y-y)*(this.y-y)));
    }

    public String toString(){
        return type+" en position "+x+" "+y;
    }
    public String getType(){ return type; }
}