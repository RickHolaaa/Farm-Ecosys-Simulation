import java.util.ArrayList;
public abstract class Agent{
    protected int x;
    protected int y;
    protected String nom;
    private static ArrayList<Agent> l;
    public Agent(int x,int y,String nom){
        this.x=x;
        this.x=y;
        this.nom=nom;
        l.add(this);
    }
    public Agent(String nom){
        this((int)(Math.random()*10),(int)(Math.random()*10),nom);
    }
    public void seDplacer(int xnew,int ynew){
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
        return nom+" en position "+x+" "+y;
    }
    public String getNom(){ return nom; }
}