import java.util.ArrayList;

public class Chasseur extends Homme implements Ennemi
{
    private boolean mortel; //True pour armes mortelle et False pour armes qui blesse
    private int munitions;
    private int nbMort;
    private int nbBlesse;

    public Chasseur(boolean mortel,int x, int y)
    {
        super(x,y,"Chasseur");
        this.mortel=mortel;
        munitions=(int)(Math.random()*3)+3;
    }

    public static void ajouterChasseur(ArrayList<Agent> agents, int nbLigne, int nbColonne){
        // Definir sa position
        int ligneChasseur = (int) (Math.random() * (nbLigne) + (nbLigne / 2)); // Entre [taille/2,taille[
        int colonneChasseur = (int) (Math.random() * (nbColonne)); // Entre [0,taille[
        // Initialiser et placer le Chasseur dans la liste d'agents
        Chasseur chass = new Chasseur(true, ligneChasseur, colonneChasseur);
        agents.add(chass);
    }

    public void tuer(ArrayList<AnimauxFerme> tab)
    {
        //Si on suppose que le terrain est de taille 20*20 et que la ferme est dans la premiere partie
        //du terrain (de la ligne/colonne 1 a ligne/colonne 10)
        //Et que le terrain ou les animaux se balade (il peux y avoir chasseur + predateur) se situe dans la deuxieme partie
        //(de la ligne/colonne 11 a ligne/colonne 20)
        
        if(munitions>0)
        {
            //Coordonees de la cible

            for(int i=0;i<tab.size();i++)
            {
                if(tab.get(i).getX()==this.x && tab.get(i).getY()==this.y)
                {
                    if(mortel) //Si armes mortelle
                    {
                        tab.remove(i);
                        munitions--;
                        nbMort++;
                        return;
                    }
                    (tab.get(i)).reduceHp(1);
                }
            }
            //Si aucun animal ne se situe à ces coordonnees, alors la balle est perdue et aucun degat n'a ete commis
            munitions--;
        }
        else
        {
            if(mortel)
                System.out.println("Chasseur à tuer "+nbMort+" animaux");
            else
                System.out.println("Chasseur à blesser "+nbBlesse+" animaux");
        }
    }
    public void tuer(ArrayList<Agent> tab, Agent a)
    {
        //Si on suppose que le terrain est de taille 20*20 et que la ferme est dans la premiere partie
        //du terrain (de la ligne/colonne 1 a ligne/colonne 10)
        //Et que le terrain ou les animaux se balade (il peux y avoir chasseur + predateur) se situe dans la deuxieme partie
        //(de la ligne/colonne 11 a ligne/colonne 20)
        
        if(munitions>0)
        {
            //Coordonees de la cible

            for(int i=0;i<tab.size();i++)
            {
                if(tab.get(i).getX()==this.x && tab.get(i).getY()==this.y)
                {
                    if(mortel) //Si armes mortelle
                    {
                        tab.remove(i);
                        munitions--;
                        nbMort++;
                        return;
                    }
                    ((AnimauxFerme)(tab.get(i))).reduceHp(1);
                }
            }
            //Si aucun animal ne se situe à ces coordonnees, alors la balle est perdue et aucun degat n'a ete commis
            munitions--;
        }
        else
        {
            if(mortel)
                System.out.println("Chasseur à tuer "+nbMort+" animaux");
            else
                System.out.println("Chasseur à blesser "+nbBlesse+" animaux");
        }
    }   
}