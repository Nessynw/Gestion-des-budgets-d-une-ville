package solveur.glouton;

import sacados.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class ComparateurTest {

    @Test
    public void testDecroissant() {
        Objet o1 = new Objet("A", 5, new int[]{1});
        Objet o2 = new Objet("B", 10, new int[]{1});

        Comparateur.ParUtiliteDecroissante comp = new Comparateur.ParUtiliteDecroissante();
        assertTrue(comp.compare(o1, o2) > 0);
        assertTrue(comp.compare(o2, o1) < 0);
    }

    @Test
    public void testCroissant() {
        Objet o1 = new Objet("A", 5, new int[]{1});
        Objet o2 = new Objet("B", 10, new int[]{1});

        Comparateur.ParUtiliteCroissante comp = new Comparateur.ParUtiliteCroissante();
        assertTrue(comp.compare(o1, o2) < 0);
        assertTrue(comp.compare(o2, o1) > 0);
    }
}

