import java.util.ArrayList;

public class Poule extends AnimauxFerme{
    private int nboeufs=0;

    public Poule(int v,String[] mangeable){
        super(v,mangeable,"Poule");
    }

    public static void ajouterPoule(ArrayList<Agent> agents, int nbLigne, int nbColonne){
        // Definir sa position
        int lignePoule = (int) (Math.random() * (nbLigne / 2)); // Entre [0,taille/2[
        int colonnePoule = (int) (Math.random() * (nbColonne)); // Entre [0,taille[
        // Initialiser la liste des ressources mangeables par la poule
        String[] mangeable = new String[2]; // Graine, Eau
        mangeable[0] = "Graine";
        mangeable[1] = "Eau";
        // Initialiser et placer la Poule dans la liste d'agents
        Animaux poule = new Poule(100, mangeable);
        poule.seDeplacer(lignePoule, colonnePoule);
        agents.add(poule);
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
    public int recupOeuf(){
        if(nboeufs>0){
            int qtt = (int)(Math.random()*nboeufs);
            this.extraire(qtt);
            return qtt;
        }
        return 0;
    }
    public AnimauxFerme reproduire(){
        AnimauxFerme a = new AnimauxFerme(hp/2,mangeable,this.type);
        hp/=2;
        return a;
    }
    public String toString(){
        return super.toString();
    }
}