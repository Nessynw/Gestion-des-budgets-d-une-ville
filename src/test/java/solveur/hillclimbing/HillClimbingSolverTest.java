package solveur.hillclimbing;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import sacados.*;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * tests unitaires pour la classe HillClimbingSolver
 * teste les différentes méthodes de l'algorithme de Hill Climbing pour
 * résoudre le problème du sac à dos multidimensionnel.
 */
public class HillClimbingSolverTest {
    private SacADos sacADos;
    private List<Objet> objets;
    /**
     * à faire avant chaque test*/
    @BeforeEach
    void setUp() {
        //création d'objets de tests
        objets = new ArrayList<>();
        objets.add(new Objet("Objet1", 50, new int[]{10, 5, 3}));
        objets.add(new Objet("Objet2", 80, new int[]{15, 10, 8}));
        objets.add(new Objet("Objet3", 40, new int[]{8, 6, 4}));
        objets.add(new Objet("Objet4", 30, new int[]{5, 3, 2}));
        objets.add(new Objet("Objet5", 70, new int[]{12, 8, 6}));

        //budget
        sacADos = new SacADos(new int[]{30, 20, 15}, objets);
    }

    //on teste les méthodes
    @Test
    //on teste le cas d'une solution null
    void testResoudreSolutionNull() {
        HillClimbingSolver solver = new HillClimbingSolver(sacADos, 2); //taille voisinage = 2
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            solver.resoudre(null);
        });
        assertTrue(exception.getMessage().contains("solution") ||
                        exception.getMessage().contains("null"),
                "Le message d'erreur avec la solution");
    }

    @Test
    void testResoudreSolutionNonRealisable() {
        HillClimbingSolver solver = new HillClimbingSolver(sacADos, 2);

        // Solution qui dépasse le budget sur au moins une dimension
        Set<Integer> objetsSelectionnes = new HashSet<Integer>()   ;

        objetsSelectionnes.add(0); // [10, 5, 3]
        objetsSelectionnes.add(1); // [15, 10, 8]
        objetsSelectionnes.add(2); // [8, 6, 4]
        objetsSelectionnes.add(4); // [12, 8, 6]
        // Total: [45, 29, 21] > budget [30, 20, 15]

        Solution solution = new Solution(objetsSelectionnes);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            solver.resoudre(solution);
        });
        assertTrue(exception.getMessage().contains("réalisable") ||
                        exception.getMessage().contains("budget"),
                "Le message d'erreur devrait mentionner la non-réalisabilité");
    }

    @Test
    void testResoudreSolutionVide() {
        HillClimbingSolver solver = new HillClimbingSolver(sacADos, 2);
        Solution solutionVide = new Solution(new HashSet<>());

        Solution resultat = solver.resoudre(solutionVide);

        assertNotNull(resultat, "Le résultat ne devrait pas être null");
        assertTrue(resultat.getValeur() >= 0, "La valeur devrait être positive ou nulle");
        assertTrue(resultat.respecteBudget(sacADos), "La solution devrait respecter le budget");
        assertTrue(solver.getIterations() >= 1, "Au moins une itération devrait être effectuée");
    }

    @Test
    void testResoudreSolutionSimple() {
        HillClimbingSolver solver = new HillClimbingSolver(sacADos, 2);

        Set<Integer> objetsSelectionnes = new HashSet<>();
        objetsSelectionnes.add(3); // Objet4: coûts=[5, 3, 2], utilité=30

        Solution solution = new Solution(objetsSelectionnes);
        Solution resultat = solver.resoudre(solution);

        assertNotNull(resultat, "Le résultat ne devrait pas être null");
        assertTrue(resultat.getValeur() >= 30, "La solution devrait au moins conserver l'utilité initiale");
        assertTrue(resultat.respecteBudget(sacADos), "La solution devrait respecter le budget");
    }
    @Test
    void testResoudreAvecAmeliorationPossible() {
        HillClimbingSolver solver = new HillClimbingSolver(sacADos, 2);

        // Solution sous-optimale avec le plus petit objet
        Set<Integer> objetsSelectionnes = new HashSet<>();
        objetsSelectionnes.add(3); // Objet4: utilité=30

        Solution solutionInitiale = new Solution(objetsSelectionnes);
        double valeurInitiale = 30.0;

        Solution resultat = solver.resoudre(solutionInitiale);

        assertNotNull(resultat, "Le résultat ne devrait pas être null");
        assertTrue(resultat.getValeur() >= valeurInitiale,
                "Le Hill Climbing devrait améliorer ou maintenir la solution");
        assertTrue(resultat.respecteBudget(sacADos), "La solution devrait respecter le budget");
    }
    @Test
    void testResoudreAvecPlateauActive() {
        HillClimbingSolver solver = new HillClimbingSolver(sacADos, 2, true, 3);

        Set<Integer> objetsSelectionnes = new HashSet<>();
        objetsSelectionnes.add(0);

        Solution solution = new Solution(objetsSelectionnes);
        Solution resultat = solver.resoudre(solution);

        assertNotNull(resultat, "Le résultat ne devrait pas être null");
        assertTrue(resultat.respecteBudget(sacADos), "La solution devrait respecter le budget");
    }
    @Test
    void testSolutionRespecteToujoursBudget() {
        HillClimbingSolver solver = new HillClimbingSolver(sacADos, 3);

        Set<Integer> objetsSelectionnes = new HashSet<>();
        objetsSelectionnes.add(0);

        Solution solution = new Solution(objetsSelectionnes);
        Solution resultat = solver.resoudre(solution);

        assertTrue(resultat.respecteBudget(sacADos),
                "La solution finale devrait toujours respecter le budget");
    }



}



