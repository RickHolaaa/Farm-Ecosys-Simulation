import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Simulation {
  private Terrain terrain;
  private ArrayList<Agent> agents; // Liste des agents
  private Ressource[] ressources; // Liste des ressources possibles
  private int nbRes;
  int taille;

  public Simulation(int taille, int m, int n)
  {
    /*
    Etapes de la simulation :

    - Initialiser un terrain
    - Remplir le terrain
    - Generer une liste d'agents et de ressources
      -> Agents : Fermier, Predateurs, Poule, Vache
      -> Ressources : Eau, Foin, Graine, Herbe
    
      
    /* INITIALISER LE TERRAIN DE TAILLE TAILLE x TAILLE (Attention, si TAILLE > nbColonne (ou nbLigne), alors TAILLE = nbColonne (ou nbLigne)) */

    if ((taille > Terrain.NBCOLONNESMAX) || (taille > Terrain.NBLIGNESMAX)) {
      this.taille = Terrain.NBLIGNESMAX;
      terrain = new Terrain(Terrain.NBLIGNESMAX, Terrain.NBCOLONNESMAX);
    } else {
      this.taille = taille;
      terrain = new Terrain(taille, taille);
    }

    /* CREATION DE LA LISTE DE RESSOURCE */    /// A MODIFIER
    ressources = new Ressource[m];
    nbRes = 0;

    /*
      Proportion de ressources sur le terrain :
      - 35% de rien
      - 25% d'herbe
      - 20% d'eau
      - 10% de graine
      - 10% de foin
    */

    /* INITIALISER LE TERRAIN AVEC DES CASES "VIDES" */
    for (int ligne = 0; ligne < taille; ligne++) { // On parcourt chaque ligne
      for (int colonne = 0; colonne < taille; colonne++) { // On parcourt chaque colonne
        Ressource rien = new Ressource(" ", 0);
        rien.setPosition(ligne, colonne);
        terrain.setCase(ligne, colonne, rien);
      }
    }

    /* ON PARCOURT LA LISTE DES RESSOURCES */
    while (nbRes < m) {
      // 25% DE CHANCE D'AVOIR DE L'HERBE
      if (Math.random() < 0.25) {
        // Initialisation de la quantite et de la Ressource
        int qtt = (int) (Math.random() * (100) + 1);
        Ressource herbe = new Ressource("Herbe", qtt);
        // Coordonees
        int ligne = (int) (Math.random() * (taille - taille / 2) + taille / 2);
        int colonne = (int) (Math.random() * (taille));
        // Tant qu'il y a qlq chose sur la case, on regenère des coordonnees
        while (terrain.getCase(ligne, colonne).type != " ") {
          ligne = (int) (Math.random() * (taille - taille / 2) + taille / 2);
          colonne = (int) (Math.random() * (taille));
        }
        // On definit la position de la Ressource
        herbe.setPosition(ligne, colonne);
        // On ajoute la Ressource à la liste de Ressource et on la place sur le Terrain
        ressources[nbRes++] = herbe;
        terrain.setCase(ligne, colonne, herbe);
      } 
      // 20% DE CHANCE D'AVOIR DE L'EAU
      else if (Math.random() < 0.45) {
        // Initialisation de la quantite et de la Ressource
        int qtt = (int) (Math.random() * (100) + 1);
        Ressource eau = new Ressource("Eau", qtt);
        // Coordonees
        int ligne = (int) (Math.random() * (taille - taille / 2) + taille / 2);
        int colonne = (int) (Math.random() * (taille));
        // Tant qu'il y a qlq chose sur la case, on regenère des coordonnees
        while (terrain.getCase(ligne, colonne).type != " ") {
          ligne = (int) (Math.random() * (taille - taille / 2) + taille / 2);
          colonne = (int) (Math.random() * (taille));
        }
        // On definit la position de la Ressource
        eau.setPosition(ligne, colonne);
        // On ajoute la Ressource à la liste de Ressource et on la place sur le Terrain
        ressources[nbRes++] = eau;
        terrain.setCase(ligne, colonne, eau);
      } 
      // 10% DE CHANCE D'AVOIR DES GRAINES
      else if (Math.random() < 0.55) {
        // Initialisation de la quantite et de la Ressource
        int qtt = (int) (Math.random() * (100) + 1);
        Ressource graine = new Ressource("Graine", qtt);
        // Coordonees
        int ligne = (int) (Math.random() * (taille - taille / 2) + taille / 2);
        int colonne = (int) (Math.random() * (taille));
        // Tant qu'il y a qlq chose sur la case, on regenère des coordonnees
        while (terrain.getCase(ligne, colonne).type != " ") {
          ligne = (int) (Math.random() * (taille - taille / 2) + taille / 2);
          colonne = (int) (Math.random() * (taille));
        }
        // On definit la position de la Ressource
        graine.setPosition(ligne, colonne);
        // On ajoute la Ressource à la liste de Ressource et on la place sur le Terrain
        ressources[nbRes++] = graine;
        terrain.setCase(ligne, colonne, graine);
      } 
      // 10% DE CHANCE D'AVOIR DU FOIN
      else if (Math.random() < 0.65) {
        // Initialisation de la quantite et de la Ressource
        int qtt = (int) (Math.random() * (100) + 1);
        Ressource foin = new Ressource("Foin", qtt);
        // Coordonees
        int ligne = (int) (Math.random() * (taille - taille / 2) + taille / 2);
        int colonne = (int) (Math.random() * (taille));
        // Tant qu'il y a qlq chose sur la case, on regenère des coordonnees
        while (terrain.getCase(ligne, colonne).type != " ") {
          ligne = (int) (Math.random() * (taille - taille / 2) + taille / 2);
          colonne = (int) (Math.random() * (taille));
        }
        // On definit la position de la Ressource
        foin.setPosition(ligne, colonne);
        // On ajoute la Ressource à la liste de Ressource et on la place sur le Terrain
        ressources[nbRes++] = foin;
        terrain.setCase(ligne, colonne, foin);
      }
    }


    /* CREATION DE LA FORET (LA PREMIER MOITIE DU TERRAIN) AVEC DE L'HERBE */
    for (int i = 0; i < taille / 2; i++) { // nbLigne
      for (int j = 0; j < taille; j++) { // nbColonne
        int qtt = (int) (Math.random() * (100) + 1);
        Ressource herbe = new Ressource("Herbe", qtt);
        terrain.setCase(i, j, herbe);
      }
    }


    /* GENERATION DE LA LISTE D'AGENTS */
    agents = new ArrayList<Agent>();

    // Remplir la liste des agents
    /*
    Probabilite :
    Au minimum, on aura :
      1 fermier : 100%
      1 predateur : 100%
      1 vache : 100%
    Sinon :
      Predateurs : 25%
        - Chasseur : 50%
        - AnimauxPredateurs : 50%
      AnimauxFerme : 75%
        - Vache : 50%
        - Poule : 50%
     */


    /* Placer 1 fermier à une position aléatoire : */
    // Coordonées du fermier

    int ligneFermier = (int) (Math.random() * (taille));
    int colonneFermier = (int) (Math.random() * (taille));

    // Initialiser les poches du fermier
    agents.add(new Fermier(ligneFermier, colonneFermier, "Fermier", initPoche(ligneFermier, colonneFermier)));

    /* Placer 1 chasseur */
    Chasseur.ajouterChasseur(agents,taille,taille);

    /* Placer 1 vache */
    Vache.ajouterVache(agents,taille,taille);

    // Initialiser la liste des agents
    while (agents.size() < n) {
      // Si c'est un prédateur
      if (Math.random() < 0.25) {
        if (Math.random() < 0.5) { // C'est un chasseur
          // Initialiser un chasseur et l'ajouter à la liste d'agents
          Chasseur.ajouterChasseur(agents, taille, taille);
        } else {
          // Initialiser un animal prédateur et l'ajouter à la liste d'agents
          AnimauxPred.ajouterPred(agents, taille, taille);
        }
      } 
      // Si c'est un animal de la ferme
      else {
        if (Math.random() < 0.5) { // C'est une vache
          // Initialiser une vache et l'ajouter à la liste d'agents
          Vache.ajouterVache(agents, taille, taille);
        } 
        else { // C'est une poule
          // Initialiser une poule et l'ajouter à la liste d'agents
          Poule.ajouterPoule(agents, taille, taille);
        }
      }
    }
  }

  public void simuler() throws IOException{
    /* INITIALISATION DES COMPTEURS ET DE LA SAISON*/

    int aniTue = 0;
    int aniRepro = 0;
    int nbLait = 0;
    int nbOeuf = 0;
    int nbMortEpuise = 0;
    String[] saison={"hiver","primtemps","ete","automne"};
    //Supposons que chaque saison dur environ 90 jours
    
    /* Initialisation de la liste des protéines */
    double[] tabMoyProt=new double[4];
    double tmpProt=0.0;

    FileWriter fw=new FileWriter(new File("test.txt"));
    //Ecriture dans un fichier qui nous permet de generer un graph sur gnuplot
    //Titre: Affichage du graphique representant l'evolution du taux de proteine dans le lait en fonction des saison
    //plot "test.txt" using 1:2 with lines title "MoyenneProt"

    double cpt=0.0;
    int z;

    for(z=0;z<saison.length;z++)
    {
        for (int it = 0; it < 90; it++) { // On fait 90 iterations de la simulation
            
            fw.write((cpt++)+" "+Fermier.getProtMoyenneRelevee()+"\n");
            fw.flush();
            tmpProt+=Fermier.getProtMoyenneRelevee();
            Fermier.analyseProtMoyenne(saison[z],it);

            if (it % 10 == 0) {
                System.out.println("REAPPROVISIONNEMENT DE LA RESERVE");
                ((Fermier) agents.get(0)).updateRessource();
            }

            System.out.println("------------------------------------------");
            System.out.println("Simulation - JOURS " + it+" DE LA SAISON "+saison[z]);
            System.out.println("------------------------------------------");

            for (int i = 0; i < agents.size(); i++) {
                /* Action de chaque agent : */

                // Pour les animaux de la ferme
                if (agents.get(i) instanceof AnimauxFerme) {
                  // On récupère ses coordonnées
                  int x = agents.get(i).getX();
                  int y = agents.get(i).getY();

                  // Si c'est une Poule, on met à jour son nombre d'oeufs (elle pond des oeufs)
                  if (agents.get(i) instanceof Poule) {
                      ((Poule) agents.get(i)).setOeufs();
                  }

                  // S'il y a une ressource (herbe, foin, eau) sur sa case
                  if (!terrain.getCase(x, y).type.equals(" ")) { 
                    // L'animal mange la ressource sur sa case (seulement les ressources mangeables par l'animal)
                    if(((AnimauxFerme) agents.get(i)).manger(terrain.getCase(x, y))){ // S'il a bien mangé la ressource
                      // On vide la case de la Ressource
                      Ressource rien = new Ressource(" ", 0);
                      rien.setPosition(x, y);
                      terrain.setCase(x, y, rien);
                    }

                    // On déplace l'agent à une postion aléatoire
                    agents.get(i).seDeplacer((int) (Math.random() * (taille)), (int) (Math.random() * (taille)));

                    // Probabilité de se reproduire
                    if (Math.random() < 0.1) {
                      agents.add(((AnimauxFerme) agents.get(i)).reproduire());
                      aniRepro++;
                      System.out.println("L'animal " + agents.get(i).toString() + " se reproduit");
                    } else {
                      System.out.println("Pas de bebe pour " + agents.get(i).toString());
                    }

                    // L'animal se deplace à une position aléatoire
                    x = (int) (Math.random() * taille);
                    y = (int) (Math.random() * taille);
                    agents.get(i).seDeplacer(x, y);

                    // Si l'animal n'a plus assez de hp
                    if (((AnimauxFerme) agents.get(i)).getHP() <= 0) {
                      agents.remove(i);
                      nbMortEpuise++;
                      i--;
                    }
                  } 
                  // S'il n'y a pas de ressource sur sa case
                  else {
                      x = (int) (Math.random() * (taille));
                      y = (int) (Math.random() * (taille));
                      agents.get(i).seDeplacer(x, y);
                  }
                }

                if (agents.get(i) instanceof Fermier)
                {
                  // On affiche les informations du Fermier 
                  affichagePocheReserve(i);

                  /* On récupère les coordonnées du Fermier et on met à jour la case du fermier :
                  - S'il est sur une case contenant une ressource présente dans sa poche, alors il va en rajouter
                  - Sinon, il la pose par terre
                  */
                  int x = agents.get(i).getX();
                  int y = agents.get(i).getY();
                  updateCaseFermier(agents, i, x, y);

                  // ALORS TRAIRE LES VACHES QUI SONT A UNE DISTANCE DE 4 CASES DE LUI
                  for (int v = 0; v < agents.size(); v++)
                  {
                    if (((agents.get(v) instanceof Vache) && ((int) (agents.get(i).distance(agents.get(v).getX(), agents.get(v).getY())) < 4)))
                    {
                      nbLait += ((Vache)agents.get(v)).extraire();
                      ((Vache)agents.get(v)).setLait();
                    } 
                    else if (((agents.get(v).type.equals("Poule")) && ((int) (agents.get(i).distance(agents.get(v).getX(), agents.get(v).getY())) < 4))) 
                    {
                      if (((AnimauxFerme) agents.get(v)).getHP() > 20) 
                      {
                        if (agents.get(v) instanceof Poule) 
                        {
                          nbOeuf += ((Poule) agents.get(v)).recupOeuf();
                        }
                      }
                    }
                  }
                }

                // Pour les chasseurs :
                if (agents.get(i) instanceof Chasseur) 
                {
                  int resindice = 0;
                  ArrayList<Agent> tmp = agents;
                  while (resindice < agents.size())
                  {
                    if ((((tmp.get(resindice) instanceof AnimauxFerme)
                      && ((int) (agents.get(i).distance(tmp.get(resindice).getX(), tmp.get(resindice).getY())) < 4)))
                        && (resindice > i)) 
                    {
                      System.out.println("Le chasseur tue " + tmp.get(resindice).toString());
                      aniTue++;
                      ((Chasseur) agents.get(i)).tuer(agents, tmp.get(resindice));
                      i = 0;
                      resindice = 0;
                      break;
                    }
                    else if ((((agents.get(resindice) instanceof AnimauxFerme)&& ((int) (agents.get(i).distance(agents.get(resindice).getX(), agents.get(resindice).getY())) < 4)))
                        && (resindice < i))
                      {
                    System.out.println("Le chasseur tue " + agents.get(resindice).toString());
                    aniTue++;
                    ((Chasseur) agents.get(i)).tuer(agents, agents.get(resindice));
                    int x = (int) (Math.random() * taille);
                    int y = (int) (Math.random() * taille);
                    if (i < agents.size()) {
                        agents.get(i).seDeplacer(x, y);
                    }
                    resindice = 0;
                    i = 0;
                    break;
                    } else {
                    int x = (int) (Math.random() * taille);
                    int y = (int) (Math.random() * taille);
                    agents.get(i).seDeplacer(x, y);
                    resindice++;
                    }
                  }
                  agents = tmp;
                }

                // Pour les predateurs :
                if (agents.get(i) instanceof AnimauxPred) {
                int resindice = 0;
                ArrayList<Agent> tmp = agents;
                while (resindice < agents.size()) {
                    // TUER LES ANIMAUX DE LA FERME QUI SONT AU MAXIMUM A 4 CASES DE L'ANIMAL PREDATEUR
                    if ((((agents.get(resindice) instanceof AnimauxFerme)
                        && ((int) (agents.get(i).distance(agents.get(resindice).getX(), agents.get(resindice).getY())) < 4)))
                        && (resindice > i)) {
                    System.out.println("L'animal predateur mange " + agents.get(resindice).toString());
                    aniTue++;
                    ((AnimauxPred) agents.get(i)).tuer(tmp, agents.get(resindice));
                    int x = (int) (Math.random() * taille);
                    int y = (int) (Math.random() * taille);
                    agents.get(i).seDeplacer(x, y);
                    resindice = 0;
                    } else {
                    int x = (int) (Math.random() * taille);
                    int y = (int) (Math.random() * taille);
                    agents.get(i).seDeplacer(x, y);
                    resindice++;
                    }
                }
                agents = tmp;
                }

            }

            /* PAS NECESSAIRE SI LE TERRAIN EST MODIFIE PAR L'AGENT */
            // Mise à jour des cases du terrain et on affiche les informations sur ce qui
            // s'est produit durant l'etape
            for (int i1 = 0; i1 < taille; i1++) {
                for (int i2 = 0; i2 < taille; i2++) {
                // Pour chaque case : Mise à jour
                if (terrain.getCase(i1, i2).type.equals(" ")) { // S'il y a une ressource sur la case
                    if (Math.random() < 0.1) {
                    Ressource pousse = new Ressource("Herbe", (int) (Math.random() * 10));
                    terrain.setCase(i1, i2, pousse);
                    System.out.println("De l'herbe vient de pousser sur la case " + i1 + "," + i2 + " !");
                    }
                } else if (terrain.getCase(i1, i2).type.equals("Herbe")) {
                    if (Math.random() < 0.1) {

                    terrain.getCase(i1, i2).setQuantite(terrain.getCase(i1, i2).getQuantite() + (int) (Math.random() * 10));
                    System.out.println("De l'herbe commence à s'agrandir en " + i1 + "," + i2 + " !");
                    }
                }
                }
            }
            System.out.println("---------REAPPROVISIONNEMENT--------------");
            ((Fermier) agents.get(0)).recupReserve();
            /*
            * try {
            * Thread.sleep(2000);
            * } catch (InterruptedException e) {
            * Thread.currentThread().interrupt();
            * }
            */
            System.out.println("-------ETAT ACTUEL DE LA FERME ET DE LA FORET-------");
            terrain.affiche(2);
            /*
            try {
              Thread.sleep(2000);
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
            */
        }
        tabMoyProt[z]=tmpProt/90;
        tmpProt=0.0;

    }
    fw.close();
    System.out.println("------------------------------------------");
    System.out.println("BILAN au bout de " + 360 + " jours");
    System.out.println("------------------------------------------");
    System.out.println("Nombre d'animal tue : " + aniTue);
    System.out.println("Nombre d'animal mort d'epuisement : " + nbMortEpuise);
    System.out.println("Nombre de naissance : " + aniRepro);
    System.out.println("Nombre de litre de lait recupere : " + nbLait + "L");
    System.out.println("Nombre d'oeuf recupere : " + nbOeuf);
    System.out.println("Taux de Proteine Moyen contenu dans le lait en saison "+saison[0]+" : " + String.format("%.4f",tabMoyProt[0])+" g/L");
    System.out.println("Taux de Proteine Moyen contenu dans le lait en saison "+saison[1]+" : " + String.format("%.4f",tabMoyProt[1])+" g/L");
    System.out.println("Taux de Proteine Moyen contenu dans le lait en saison "+saison[2]+" : " + String.format("%.4f",tabMoyProt[2])+" g/L");
    System.out.println("Taux de Proteine Moyen contenu dans le lait en saison "+saison[3]+" : " + String.format("%.4f",tabMoyProt[3])+" g/L");
  }

  private void affichagePocheReserve(int indiceAgent){
    System.out.println("------------------RESERVE-----------------");
    ((Fermier) agents.get(indiceAgent)).getRessourceDisponible();
    System.out.println("------------------------------------------");
    System.out.println("-------------------POCHE------------------");
    ((Fermier) agents.get(indiceAgent)).getRessourceDisponiblePoche();
    System.out.println("------------------------------------------");
    System.out.println("------------RAPPORT JOURNALIER------------");
  }

  public ArrayList<Ressource> initPoche(int ligneFermier, int colonneFermier){
    ArrayList<Ressource> poche = new ArrayList<Ressource>(4); // Le fermier a 4 places (pour chaque ressource)
    int qttHerbe = (int) (Math.random() * (10));
    int qttFoin = (int) (Math.random() * (10));
    int qttEau = (int) (Math.random() * (10));
    int qttGraine = (int) (Math.random() * (10));
    Ressource herbe = new Ressource("Herbe", qttHerbe);
    herbe.setPosition(ligneFermier, colonneFermier);
    Ressource foin = new Ressource("Foin", qttFoin);
    foin.setPosition(ligneFermier, colonneFermier);
    Ressource eau = new Ressource("Eau", qttEau);
    eau.setPosition(ligneFermier, colonneFermier);
    Ressource graine = new Ressource("Graine", qttGraine);
    graine.setPosition(ligneFermier, colonneFermier);
    poche.add(herbe);
    poche.add(foin);
    poche.add(eau);
    poche.add(graine);
    return poche;
  }

  private void updateCaseFermier(ArrayList<Agent> agents, int i, int x, int y){
    // S'il est sur une case contenant une ressource qu'il a dans sa poche
    if (!terrain.getCase(x, y).type.equals(" ")) {
      ((Fermier) agents.get(i)).reaprovisionner(terrain.getCase(x, y), terrain.getCase(x, y).type);
      x = (int) (Math.random() * (taille));
      y = (int) (Math.random() * (taille));
      agents.get(i).seDeplacer(x, y);
    } 
    // Sinon il la depose par terre
    else {
      int indice = (int) (Math.random() * (((Fermier) agents.get(i)).taille));
      Ressource tmp = new Ressource(((Fermier) agents.get(0)).getPoche(indice).type,((Fermier) agents.get(0)).getPoche(indice).getQuantite());
      System.out.println("Le fermier depose " + ((Fermier) agents.get(0)).getPoche(indice).getQuantite() + " "
          + ((Fermier) agents.get(0)).getPoche(indice).type + " par terre");
      ((Fermier) agents.get(0)).getPoche(indice).setQuantite(0);
      terrain.setCase(x, y, tmp);
      x = (int) (Math.random() * (taille - taille / 2) + taille / 2);
      y = (int) (Math.random() * taille);
      agents.get(i).seDeplacer(x, y);
      ((Fermier) agents.get(i)).setPochePosition();
    }
  }
}
