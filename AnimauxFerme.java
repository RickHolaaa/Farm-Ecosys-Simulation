public class AnimauxFerme extends Animaux{

    public AnimauxFerme(int hp,String[] mangeable,String type){
        super(hp,mangeable,type);
    }
    public AnimauxFerme clone(){
       return new AnimauxFerme(hp, mangeable, type);
    }
    public boolean manger(Ressource res){
        for(int i=0;i<mangeable.length;i++){
            if(mangeable[i].equals(res.type)){
                int q = res.getQuantite();
                res.setQuantite(0);
                if(hp<100){ hp+=q; }
                if(hp>100){ hp=100; }
                System.out.println("L'animal "+this.type+" mange "+res.toString());
                return true;
            }
        }
        System.out.println("L'animal "+this.type+" n'a pas trouve de nourriture...");
        return false;
    }
    public AnimauxFerme reproduire(){
        AnimauxFerme a = clone();
        a.hp/=2;
        return a;
    }
    public void reduceHp(int enleve){
        this.hp-=enleve;
    }
    public String toString(){
        return "Animal de la ferme: "+super.toString();
    }
}