import java.io.IOException;


public class TestSimulation {
  public static void main(String[] args)
  throws IOException 
  {
    /*
     * Creation de l'environnement :
     * - Terrain de 10x10
     * - Initialiser et placer aleatoirement 12 ressources sur le terrain
     * - Generer 3 agents
     */
    Simulation s1 = new Simulation(10, 15, 12);

    // On simule 10 iterations et on affiche les actions effectuees durant chaque
    // iteration
    s1.simuler();
    // On afficher le terrain
    // s1.afficher();
  }
}
