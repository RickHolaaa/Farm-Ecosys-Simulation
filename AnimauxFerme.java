public class AnimauxFerme extends Animaux{

    public AnimauxFerme(int v,String nom,Ressource[] r){
        super(v,r,nom);
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
    public AnimauxFerme reproduire(){
        AnimauxFerme a=new AnimauxFerme(hp/2,this.nom,resmang);
        hp/=2;
        return a;
    }
    public void reduceHP(int enleve){
        this.hp-=enleve;
    }
    public String toString(){
        return "Animal de la ferme: "+super.toString();
    }
}