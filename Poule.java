public class Poule extends AnimauxFerme{
    private int nboeufs=0;

    public Poule(int v,Ressource[] r){
        super(v,"Poule",r);
    }
    public void setOeufs(){
        if(hp>20){
            nboeufs+=10;
            hp-=5;
        }
    }
     public void extraire(int q){
        nboeufs-=q;
        if(nboeufs<0)nboeufs=0;
    }

    public String toString(){
        return super.toString();
    }
}