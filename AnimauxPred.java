import java.util.ArrayList;
public class AnimauxPred extends Animaux implements Ennemi{

    public AnimauxPred(int v,String nom,Ressource[] r){
        super(v,r,nom);
    }
    public AnimauxPred reproduire(){
        AnimauxPred a=new AnimauxPred(hp/2,this.nom,resmang);
        hp/=2;
        return a;
    }
     public void manger(String type){
        for(int i=0;i<resmang.length;i++){
            if(resmang[i].type==type){
                int q=resmang[i].getQuantite();
                resmang[i].setQuantite(0);
                if(hp<100)hp+=q;
                if(hp>100)hp=100;
                System.out.println("L'animal "+this.nom+" mange"+type);
            }
            else{
                System.out.println("L'animal "+this.nom+" ne peut pas manger "+type);
            }
        }
    }
    public void tuer(ArrayList<AnimauxFerme> l){
        for(int i=0; i<l.size();i++)
        {
            AnimauxFerme tmp=l.get(i);
            if (tmp.x== this.x && tmp.y== this.y){
                this.hp+=tmp.hp;
                if(this.hp>100)this.hp=100;
                tmp.hp=0;
                tmp=null;
            }
        }
    }
    public String toString(){
        return "Animal predateur: "+super.toString();
    }
}