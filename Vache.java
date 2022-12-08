import java.util.ArrayList;

public class Vache extends AnimauxFerme{
    private int qlait;
  
    public Vache(int v,String[] r,int q){
        super(v,r,"Vache");
        qlait=q;
    }
    public static void ajouterVache(ArrayList<Agent> agents, int nbLigne, int nbColonne){
        // Definir sa position
        int ligneVache2 = (int) (Math.random() * (nbLigne - nbLigne / 2) + nbLigne / 2); // Entre [0,taille/2[
        int colonneVache2 = (int) (Math.random() * (nbColonne)); // Entre [0,taille[
        // Initialiser la liste des ressources mangeables par la vache
        String[] mangeable2 = new String[3]; // Herbe, Eau, Foin
        mangeable2[0] = "Herbe";
        mangeable2[1] = "Eau";
        mangeable2[2] = "Foin";
        // Initialiser et placer la Vache dans la liste d'agents
        Vache vache2 = new Vache(100, mangeable2, 24);
        vache2.seDeplacer(ligneVache2, colonneVache2);
        agents.add(vache2);
    }

    public void setLait(){
        if(hp>50){
            qlait+=24;
            hp-=20;
        }
    }
    public int extraire(){
      int qtt = qlait;
      qlait=0;
      System.out.println("Le fermier trait la vache !");
      return qtt;
    }
    public String toString(){
        return super.toString();
    }
  }