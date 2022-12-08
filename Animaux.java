public abstract class Animaux extends Agent{
    protected int hp;
    protected String [] mangeable;

    public Animaux(int vie,String[] mangeable,String type){
        super(type);
        this.hp=vie;
        this.mangeable=mangeable;
    }
    public Animaux(String[] mangeable,String type){
        this(100,mangeable,type);
    }
    public abstract boolean manger(Ressource res);
    public String toString(){
        return super.toString()+" vie restante: "+hp;
    }
    public int getHP(){
        return hp;
    }
    public void getMangeable(){
        System.out.println("L'animal "+type+" peut manger : ");
        for(int i=0;i<mangeable.length;i++){
            System.out.print(mangeable[i]+" ");
        }
        System.out.println();
    }
}