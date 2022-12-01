import java.util.ArrayList;

public class Fermier extends Homme
{
    private int qt_eau;//En Litre
    private int qt_foin;//En kg
    private int ressources;
    private ArrayList<AnimauxFerme> tab;

    public Fermier(int x, int y, String nom)
    {
        super(x,y,nom);
        qt_eau=(int)(Math.random()*10)+5;
        qt_foin=(int)(Math.random()*10)+5;
        ressources=(int)(Math.random()*10)+5;
    }

    public void nourir(Terrain t)
    {
        for(int i=0;i<t.nbLignes;i++)
        {
            for(int j=0;j<t.nbColonnes;j++)
            {
                Ressource tmp=t.getCase(i,j);
                if(tmp.getQuantite()<5)
                {
                    if(qt_eau>=5)
                    {
                        tmp.setQuantite(tmp.getQuantite()+5);
                        qt_eau-=5;
                    }
                    if(qt_foin>=5)
                    {
                        tmp.setQuantite(tmp.getQuantite()+5);
                        qt_eau-=5;
                    }
                }
            }
        }
    }
    public int getNourriture(){ return ressources; }
    public void setNourriture(){
        ressources=(int)(Math.random()*10)+5;
    }
}