public class Simulation {
    private Terrain terrain;
    private Agent [] agents;
    private Ressource [] ressources;

    public Simulation(int taille,int m, int n){
        /* Initialiser le terrain, m ressources et n agents */
        terrain = new Terrain();
        // On remplit le terrain
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(i<=10){ // Cas de la ferme : placer aléatoirement des ressources sur le terrain
                    if(Math.random()<0.1){
                        boolean result = terrain.setCase(i, j,new Ressource("foin", 12));
                    }
                    else if(Math.random()<0.2){
                        boolean result = terrain.setCase(i, j,new Ressource("herbe", 12));
                    }
                    else if(Math.random()<0.3){
                        boolean result = terrain.setCase(i, j,new Ressource("eau", 12));
                    }
                }
                else{ // Cas de la forêt
                    boolean result = terrain.setCase(i, j,new Ressource("herbe", 20));
                }
            }
        }
        // Afficher le terrain
        terrain.affiche(1);
        // Générer la liste des agents & ressources
        agents = new Agent[n];
        ressources = new Ressource[m];

        // Placer les ressources de manière aléatoire
        int i=0;
        int x1,y1;
        if(m<taille){ // On doit avoir au maximum (TAILLE) ressources sur une ligne/colonne (sinon on sort du tableau : Segmentation fault)
            while(i<m){ // Tant qu'on a pas (m) ressources sur le terrain
                // On génère aléatoirement les coordonées d'une case
                x1=(int)(Math.random()*(taille));
                y1=(int)(Math.random()*(taille));
                if(terrain.caseEstVide(x1,y1)){ // Si la case est vide
                    terrain.setCase(x1,y1,ressources[i]); // Alors on ajoute la ressource sur la case
                    i++;
                }
            }
        }
    }

    void simuler(int iteration){
        for(int it=0;it<iteration;it++){ // On fait (ITERATION) itérations de la simulation
            System.out.println("Simulation - itération "+it);
            for(int i=0;i<agents.length;i++){
                /*Action de chaque agent :*/

                // Pour les vaches :
                if(agents[i] instanceof AnimauxFerme){
                    int x = agents[i].getX();
                    int y = agents[i].getY();
                    if(terrain.videCase(x,y)!=null){ // S'il y a une ressource (herbe, foin, eau)
                        ((AnimauxFerme)agents[i]).manger(terrain.getCase(x,y).type); // L'animal mange la ressource
                    }
                }

                /*
                // Pour les fermiers : A MODIFIER - PARCOURT GENERAL DU TERRAIN PAR LE FERMIER EN UNE SEULE METHODE
                if(agents[i] instanceof Fermier){
                    int x = agents[i].getX();
                    int y = agents[i].getY();
                    if(terrain.videCase(x,y)==null){ // S'il y a une ressource (herbe, foin, eau)
                        int qtt = terrain.getCase(x,y).getQuantite();
                        terrain.getCase(x,y).setQuantite(qtt+((Fermier)agents[i]).getNourriture());
                        ((Fermier)agents[i]).setNourriture();
                        Ressource res = new Ressource("herbe", 13);
                        System.out.println("Le fermier "+i+" réapprovisionne la ressource : "+terrain.getCase(x,y).type);
                    }
                    for(int j=0;j<agents.length;j++){ // Parmis tous les agents
                        // S'il y a un autre agent qui est un AnimalFerme
                        if(((agents[j].getX()==agents[i].getX())&&(agents[j].getX()==agents[i].getY()))&&(agents[j] instanceof AnimauxFerme)){
                            if(((AnimauxFerme)agents[j]).getNom()=="vache"){ // Si c'est une vache
                                if(((AnimauxFerme)agents[j]).getHP()>10){ // et qu'elle a assez d'énergie

                                }
                            }
                        }
                    }

                    // Il se réapprovisionne dans la ferme (automatiquement)
                    ((Fermier)agents[i]).setNourriture();
                }
                */


                // Pour les chasseurs :
                if(agents[i] instanceof Chasseur){
                    
                    System.out.println("Le chasseur "+i+" agit");
                }

                // Pour les prédateurs :
                if(agents[i] instanceof AnimauxPred){
                    
                    System.out.println("Le prédateur "+i+" agit");
                }


            }

            /* PAS NECESSAIRE SI LE TERRAIN EST MODIFIE PAR L'AGENT */
            // Mise à jour des cases du terrain et on affiche les informations sur ce qui s'est produit durant l'étape
            for(int i1=0;i1<Terrain.NBLIGNESMAX;i1++){
                for(int i2=0;i2<Terrain.NBCOLONNESMAX;i2++){
                    // Pour chaque case : Mise à jour
                    if(terrain.videCase(i1,i2)!=null){ // S'il y a une ressource sur la case
                        if(Math.random()<0.1){
                            // Une probabilité de faire pousser de l'herbe sur la case ?
                            terrain.getCase(i1,i2).setQuantite(-1);
                            System.out.println("La case ["+i1+","+"i2"+"] a été modifié");
                        }
                    }
                }
            }
        }
    }

}
