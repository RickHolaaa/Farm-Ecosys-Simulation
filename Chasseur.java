import java.util.ArrayList;

public class Chasseur extends Homme implements Ennemi
{
    private boolean typeArme; //True pour armes mortelle et False pour armes qui blesse
    private int munitions;
    private int nbVictime;

    public Chasseur(boolean typeArme,int x, int y,String nom)
    {
        super(x,y,nom);
        this.typeArme=typeArme;
        munitions=(int)(Math.random()*3)+3;
    }

    public void tuer(AnimauxFerme a){

    }

    public void tuerTous(ArrayList<AnimauxFerme> t)
    {
        //Si on suppose que le terrain est de taille 20*20 et que la ferme est dans la premiere partie
        //du terrain (de la ligne/colonne 1 a ligne/colonne 10)
        //Et que le terrain ou les animaux se balade (il peux y avoir chasseur + predateur) se situe dans la deuxiéme partie
        //(de la ligne/colonne 11 a ligne/colonne 20)
        
        if(munitions>0)
        {
            //Coordonées de la cible
            int x_tmp=(int)(Math.random()*11)+10;
            int y_tmp=(int)(Math.random()*11)+10;

            for(int i=0;i<t.size();i++)
            {
                if(t.get(i).x==x_tmp && t.get(i).y==y_tmp)
                {
                    if(typeArme)
                    {
                        t.remove(i);
                        munitions--;
                        nbVictime++;
                        return;
                    }
                    t.get(i).reduceHP(t.get(i).hp-1);
                }
            }
            //Si aucun animal ne se situe à ces coordonnées, alors la balle est perdue et aucun degat n'a été commis
            munitions--;
        }
        else
        {
            if(typeArme)
                System.out.println("Chasseur a tué "+nbVictime);
            else
                System.out.println("Chasseur a tué "+nbVictime);
        }
    }   
}