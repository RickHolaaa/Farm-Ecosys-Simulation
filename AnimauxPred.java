import java.util.ArrayList;
public class AnimauxPred extends Animaux implements Ennemi{

    public AnimauxPred(int hp,String[] mangeable){
        super(hp,mangeable,"AnimauxPred");
    }

    public static void ajouterPred(ArrayList<Agent> agents, int nbLigne, int nbColonne){
        // Definir sa position
        int lignePred = (int) (Math.random() * (nbLigne) + (nbLigne / 2)); // Entre [taille/2,taille[
        int colonnePred = (int) (Math.random() * (nbColonne)); // Entre [0,taille[
        // Initialiser et placer l'animal prédateur dans la liste d'agents
        AnimauxPred pred = new AnimauxPred(100, null);
        pred.seDeplacer(lignePred, colonnePred);
        agents.add(pred);
    }

    public AnimauxPred clone(){
        return new AnimauxPred(hp, mangeable);
    }

    public AnimauxPred reproduire(){
        AnimauxPred a = new AnimauxPred(hp/2,mangeable);
        hp/=2;
        return a;
    }

    public boolean manger(Ressource res){
        for(int i=0;i<mangeable.length;i++){
            if(mangeable[i]==res.type){
                int q=res.getQuantite();
                res.setQuantite(0);
                if(hp<100)hp+=q;
                if(hp>100)hp=100;
                System.out.println("L'animal "+this.type+" mange"+mangeable[i]);
                return true;
            }
        }
        return false;
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
    public void tuer(ArrayList<Agent> l, Agent a){
        this.hp+=((AnimauxFerme)a).hp;
        l.remove(a);
    }
    public String toString(){
        return "Animal predateur: "+super.toString();
    }
}