package solveur.hillclimbing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import sacados.*;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class SolutionTest {

    private SacADos problemeSimple;


    @BeforeEach
    public void setUp() {
        Objet obj0 = new Objet("obj0", 5, new int[]{10});
        Objet obj1 = new Objet("obj1", 8, new int[]{15});
        Objet obj2 = new Objet("obj2", 12, new int[]{20});


        List<Objet> objets = List.of(obj0, obj1, obj2);
        int[] budget = {30};
        problemeSimple = new SacADos(budget, objets);

    }

    @Test
    public void testCreationSolutionVide() {
        Solution solution = new Solution(new HashSet<>());

        assertNotNull(solution); // la solution en doit pas être null
        assertEquals(0, solution.getObjets().size());
    }

    @Test
    public void testCreationSolutionAvecObjets() {
        Set<Integer> objets = Set.of(0, 1);
        Solution solution = new Solution(objets);

        assertEquals(2, solution.getObjets().size()); // la taille doit être = 2
        assertTrue(solution.getObjets().contains(0)); //vérifie que la solution contient bien 2 objets qui sont 0 et 1
        assertTrue(solution.getObjets().contains(1));
    }

    @Test
    public void testCreationSolutionNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Solution(null);
        });
    }
//tests sur le budget
    @Test
    public void testSolutionVideRespecteBudget() {
        //sol vide=0 respecte toujours le budget
        Solution solution = new Solution(new HashSet<>());
        assertTrue(solution.respecteBudget(problemeSimple));
    }

    @Test
    public void testSolutionValideRespecteBudget() {
        // obj0 (coût 10) + obj1 (coût 15) = 25 ≤ 30 donc sol respecte le budget
        Solution solution = new Solution(Set.of(0, 1));
        assertTrue(solution.respecteBudget(problemeSimple));
    }

    @Test
    public void testSolutionLimiteRespecteBudget() {
        // obj0 (coût 10) + obj2 (coût 20) = 30 = budget exact (le cas limite sol= budget)
        Solution solution = new Solution(Set.of(0, 2));
        assertTrue(solution.respecteBudget(problemeSimple));
    }

    @Test
    public void testSolutionInvalideNeRespectePasBudget() {
        // obj0 + obj1 + obj2 = 10 + 15 + 20 = 45 > 30 (on rejette sol)
        Solution solution = new Solution(Set.of(0, 1, 2));
        assertFalse(solution.respecteBudget(problemeSimple));
    }

    @Test
    public void testSolutionDepasseBudget() {
        // obj1 (15) + obj2 (20) = 35 > 30
        Solution solution = new Solution(Set.of(1, 2));
        assertFalse(solution.respecteBudget(problemeSimple));
    }

    @Test
    public void testRespecteBudgetAvecProblemeNull() {
        Solution solution = new Solution(Set.of(0));
        assertThrows(IllegalArgumentException.class, () -> {
            solution.respecteBudget(null);
        });
    }

}