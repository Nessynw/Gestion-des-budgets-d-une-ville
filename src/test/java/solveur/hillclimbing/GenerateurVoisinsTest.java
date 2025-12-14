package solveur.hillclimbing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import sacados.*;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * Tests unitaires pour GenerateurVoisinsBas.
 * méthode genererVoisins() qui génère le voisinage d'une solution.
 */
public class GenerateurVoisinsTest {
    private SacADos sacADos;
    private GenerateurVoisinsBas generateur;
    /**
     * à faire avant chaque test*/
    @BeforeEach
    void setUp() {
        //création d'objets de tests
        List<Objet> objets = new ArrayList<>();
        objets.add(new Objet("Objet1", 50, new int[]{10, 5, 3}));
        // Objet2: utilité=80, coûts=[15, 10, 8]
        objets.add(new Objet("Objet2", 80, new int[]{15, 10, 8}));
        // Objet3: utilité=40, coûts=[8, 6, 4]
        objets.add(new Objet("Objet3", 40, new int[]{8, 6, 4}));
        // Objet4: utilité=30, coûts=[5, 3, 2]
        objets.add(new Objet("Objet4", 30, new int[]{5, 3, 2}));
        // Objet5: utilité=70, coûts=[12, 8, 6]
        objets.add(new Objet("Objet5", 70, new int[]{12, 8, 6}));

        //budget
        sacADos = new SacADos(new int[]{30, 20, 15}, objets);
        generateur = new GenerateurVoisinsBas(2);

    }
    @Test
    @DisplayName("genererVoisins() avec solution vide génère uniquement des ajouts")
    void testGenererVoisinsSolutionVide() {
        Solution solutionVide = new Solution(new HashSet<>());

        List<Solution> voisins = generateur.genererVoisins(solutionVide, sacADos);

        assertNotNull(voisins);
        assertTrue(voisins.size() > 0, "Devrait générer des voisins par ajout");

        // Tous les voisins devraient respecter le budget
        for (Solution voisin : voisins) {
            assertTrue(voisin.respecteBudget(sacADos));
        }
    }
    @Test
    @DisplayName("genererVoisins() génère des voisins valides (respectant budget)")
    void testGenererVoisinsRespectebudget() {
        Set<Integer> objets = new HashSet<>(Set.of(0, 3)); // Objets 1 et 4
        Solution solution = new Solution(objets);

        List<Solution> voisins = generateur.genererVoisins(solution, sacADos);

        assertNotNull(voisins);
        // Tous les voisins doivent respecter le budget
        for (Solution voisin : voisins) {
            assertTrue(voisin.respecteBudget(sacADos),
                    "Chaque voisin doit respecter le budget multidimensionnel");
        }
    }

    @Test
    @DisplayName("genererVoisins() ne génère pas de doublons")
    void testGenererVoisinsSansDoublons() {
        Set<Integer> objets = new HashSet<>(Set.of(0));
        Solution solution = new Solution(objets);

        List<Solution> voisins = generateur.genererVoisins(solution, sacADos);

        // Convertir en Set pour détecter doublons
        Set<Solution> voisinsUniques = new HashSet<>(voisins);

        assertEquals(voisins.size(), voisinsUniques.size(),
                "Ne devrait pas y avoir de doublons dans les voisins");
    }
    @Test
    @DisplayName("genererVoisins() génère voisins par retrait uniquement")
    void testGenererVoisinsRetrait() {
        Set<Integer> objets = new HashSet<>(Set.of(0, 3)); // 2 objets
        Solution solution = new Solution(objets);

        List<Solution> voisins = generateur.genererVoisins(solution, sacADos);

        // Chercher un voisin obtenu par retrait uniquement
        boolean retraitTrouve = false;
        for (Solution voisin : voisins) {
            if (voisin.getObjets().size() < objets.size()) {
                retraitTrouve = true;
                break;
            }
        }

        assertTrue(retraitTrouve, "Devrait générer des voisins par retrait");
    }

    @Test
    @DisplayName("genererVoisins() génère voisins par ajout uniquement")
    void testGenererVoisinsAjout() {
        Set<Integer> objets = new HashSet<>(Set.of(3)); // 1 objet petit
        Solution solution = new Solution(objets);

        List<Solution> voisins = generateur.genererVoisins(solution, sacADos);

        // Chercher un voisin obtenu par ajout uniquement
        boolean ajoutTrouve = false;
        for (Solution voisin : voisins) {
            if (voisin.getObjets().size() > objets.size()) {
                ajoutTrouve = true;
                break;
            }
        }

        assertTrue(ajoutTrouve, "Devrait générer des voisins par ajout");
    }

    @Test
    @DisplayName("genererVoisins() génère voisins par échange")
    void testGenererVoisinsEchange() {
        Set<Integer> objets = new HashSet<>(Set.of(0)); // Objet1
        Solution solution = new Solution(objets);

        List<Solution> voisins = generateur.genererVoisins(solution, sacADos);

        // Chercher un voisin obtenu par échange (même taille mais objets différents)
        boolean echangeTrouve = false;
        for (Solution voisin : voisins) {
            Set<Integer> objetsVoisin = voisin.getObjets();
            if (objetsVoisin.size() == objets.size() && !objetsVoisin.equals(objets)) {
                echangeTrouve = true;
                break;
            }
        }

        assertTrue(echangeTrouve, "Devrait générer des voisins par échange");
    }


}
