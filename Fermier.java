import java.util.ArrayList;

public class Fermier extends Homme
{

    private ArrayList<Ressource> tab;

    public Fermier(int x, int y, String nom, ArrayList<Ressource> tab)
    {
        super(x,y,nom);
    }


    public void reaprovisionner(Terrain t,int n)
    {
        for(int i=0;i<t.nbLignes;i++)
        {
            for(int j=0;j<t.nbColonnes;j++)
            {
                tmp.setQuantite(tmp.getQuantite()+n);
            }
        }
    }

    public void updateRessource()
    {
        for(int i=0;i<tab.size();i++)
        {
            Ressource r=tab.get(i);
            r.setQuantite(r.getQuantite()+(int)(Math.random()*(10-5+1))+5);
        }
    }

    public void getRessourceDisponible()
    {
        for(int i=0;i<size();i++)
        {
            Ressource r=tab.get(i);
            System.out.println(r.toString());
        }
    }
    //Chaque mois fermier recois stock de ressource 
    
}