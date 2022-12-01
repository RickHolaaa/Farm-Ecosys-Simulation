public class TestSimulation {
    public static void main(String[] args){
        /* 
        Création de l'environnement : 
            - Terrain de 10x10
            - Initialiser et placer aléatoirement 12 ressources sur le terrain
            - Générer 3 agents 
        */
        Simulation s1 = new Simulation(10, 12, 3);
        // On simule 10 itérations et on affiche les actions effectuées durant chaque itération
        s1.simuler(10);
        // On afficher le terrain
        System.out.println("Affichage du terrain final\n"+s1.toString());
    }
}
