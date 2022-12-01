public abstract class Animaux extends Agent{
    protected int hp;
    protected Ressource [] resmang;

    public Animaux(int vie,Ressource[] r,String nom){
        super(nom);
        this.hp=vie;
        resmang=r;
    }
    public Animaux(String nom,Ressource[] r){
        this(100,r,nom);
    }
    public abstract void manger(String type);
    public String toString(){
        return super.toString()+" vie restante: "+hp;
    }
    public int getHP(){
        return hp;
    }
}