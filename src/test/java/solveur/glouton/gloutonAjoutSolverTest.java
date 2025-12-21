package solveur.glouton;

import sacados.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class gloutonAjoutSolverTest {

    private SacADos sacSimple;

    @BeforeEach
    public void setUp() {
        Objet obj0 = new Objet("obj0", 5, new int[]{10});
        Objet obj1 = new Objet("obj1", 8, new int[]{15});
        Objet obj2 = new Objet("obj2", 12, new int[]{20});
        sacSimple = new SacADos(new int[]{30}, List.of(obj0, obj1, obj2));
    }


    @Test
    public void testAjoutOrdreDecroissant() {
        GloutonAjoutSolver solver = new GloutonAjoutSolver(new Comparateur.ParUtiliteDecroissante());
        List<Objet> solution = solver.resoudre(sacSimple);

        assertTrue(solution.get(0).getUtilite() >= solution.get(solution.size()-1).getUtilite());
    }

    @Test
    public void testAjoutAvecBudgetExact() {
        SacADos sacLimite = new SacADos(new int[]{30}, List.of(
                new Objet("obj0", 5, new int[]{10}),
                new Objet("obj2", 12, new int[]{20})
        ));
        GloutonAjoutSolver solver = new GloutonAjoutSolver(new Comparateur.ParUtiliteDecroissante());
        List<Objet> solution = solver.resoudre(sacLimite);
        int totalCout = solution.stream().mapToInt(o -> o.getCouts()[0]).sum();
        assertEquals(30, totalCout);
    }

    @Test
    public void testAjoutDepasseBudget() {
        SacADos sacDepasse = new SacADos(new int[]{30}, List.of(
                new Objet("obj1", 8, new int[]{15}),
                new Objet("obj2", 12, new int[]{20})
        ));
        GloutonAjoutSolver solver = new GloutonAjoutSolver(new Comparateur.ParUtiliteDecroissante());
        List<Objet> solution = solver.resoudre(sacDepasse);

        int totalCout = solution.stream().mapToInt(o -> o.getCouts()[0]).sum();
        assertTrue(totalCout <= 30);
    }
}

