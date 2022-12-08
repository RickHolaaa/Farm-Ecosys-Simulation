import java.util.ArrayList;

public class Fermier extends Homme {
  private ArrayList<Ressource> reserve;
  private ArrayList<Ressource> tab;
  protected int taille;
  private static double protMoyenneRelevee=0.9;

  public Fermier(int x, int y, String nom, ArrayList<Ressource> poche) {
    super(x, y, nom);
    tab = poche;
    reserve = new ArrayList<Ressource>();
    Ressource reserveHerbe = new Ressource("Herbe", (int) (Math.random() * (1000)));
    reserveHerbe.setPosition(0, 0);
    reserve.add(reserveHerbe);

    Ressource reserveEau = new Ressource("Eau", (int) (Math.random() * (1000)));
    reserveEau.setPosition(0, 0);
    reserve.add(reserveEau);
    Ressource reserveGraine = new Ressource("Graine", (int) (Math.random() * (1000)));
    reserveGraine.setPosition(0, 0);
    reserve.add(reserveGraine);
    Ressource reserveFoin = new Ressource("Foin", (int) (Math.random() * (1000)));
    reserveFoin.setPosition(0, 0);
    reserve.add(reserveFoin);

    taille = tab.size();
  }

  public void reaprovisionner(Ressource res, String type) {
    for (int i = 0; i < tab.size(); i++) {
      if (type.equals(tab.get(i).type)) {
        res.setQuantite(res.getQuantite() + tab.get(i).getQuantite());
        System.out.println("LE FERMIER RAJOUTE " + res.type + " x" + tab.get(i).getQuantite() + " SUR SA CASE");
        tab.get(i).setQuantite(0);
      }
    }
  }

  public void recupReserve() {
    for (int i = 0; i < reserve.size(); i++) {
      for (int j = 0; j < tab.size(); j++) {
        if (reserve.get(i).type.equals(tab.get(j).type)) {
          int qtt = (int) (Math.random() * 10 + 2);
          if (reserve.get(i).getQuantite() - qtt > 0) {
            System.out.println("LE FERMIER RENTRE CHEZ LUI POUR RECUPERER " + qtt + " " + reserve.get(i).type);
            tab.get(j).setQuantite(tab.get(j).getQuantite() + qtt);
            reserve.get(i).setQuantite(reserve.get(i).getQuantite() - qtt);
          } else {
            System.out.println("LE FERMIER N'A PLUS DE " + reserve.get(i).type + " DANS SA RESERVE !");
          }
        }
      }
    }
  }

  public void updateRessource() {
    for (int i = 0; i < reserve.size(); i++) {
      Ressource r = reserve.get(i);
      r.setQuantite(r.getQuantite() + (int) (Math.random() * (100 - 5 + 1)) + 5);
    }
  }

  public void getRessourceDisponible() {
    System.out.println("LA RESERVE CONTIENT :");
    for (int i = 0; i < reserve.size(); i++) {
      Ressource r = reserve.get(i);
      System.out.println(r.toString());
    }
  }

  public void getRessourceDisponiblePoche() {
    System.out.println("LA POCHE CONTIENT:");
    for (int i = 0; i < tab.size(); i++) {
      Ressource r = tab.get(i);
      System.out.println(r.toString());
    }
  }

  public void setPochePosition() {
    for(int i=0;i<tab.size();i++){
      tab.get(i).setPosition(this.getX(), this.getY());
    }
  }

  // Chaque mois fermier recois stock de ressource
  public Ressource getPoche(int i) {
    return tab.get(i);
  }

    public static void analyseProtMoyenne(String saison,int jours)
    {
        //Jours long avec + de soleil => +de production de lait mais diminution du taux de proteines par effet de dillution
        //Prot depend aussi de l'energie de l'animal lors de l'extraction

        //Plus de lait produit en ete et moins en hiver
        
        if(saison=="hiver")
        {   
            protMoyenneRelevee-=0.0087; //Diminue progressivement au fil de la saison
            return;
        }
        if(saison=="primtemps")
        {
            protMoyenneRelevee-=0.011;
            return;
        }
        if(saison=="ete")
        {
            protMoyenneRelevee+=0.011;
            return;
        }
        if(saison=="automne")
        {
            protMoyenneRelevee+=0.0087; //Diminue progressivement au fil de la saison
            return;
        }
    }

    public static double getProtMoyenneRelevee()
    {
        return protMoyenneRelevee;
    }

}