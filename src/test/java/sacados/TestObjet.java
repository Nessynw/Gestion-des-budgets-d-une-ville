package sacados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


public class TestObjet {
    private Objet objetUnderTest=new Objet();

    @BeforeEach
    public void initObjet(){
        objetUnderTest.setNom("Objet test");
        objetUnderTest.setUtilite(20);
        objetUnderTest.setCouts(new int[]{4,21,12,7});
    }

    @AfterEach
    public void clearObjet(){
        objetUnderTest=null;
    }

    @ParameterizedTest
    @ValueSource(ints={2,40,0,7})
    public void addCout_int_rajouteDansCoutsObjet(int arg){
        Objet o=new Objet();
        o.setNom(objetUnderTest.getNom());
        o.setUtilite(objetUnderTest.getUtilite());
        o.setCouts(new int[]{4,21,12,7,arg});
        objetUnderTest.addCout(arg);
        assertEquals(objetUnderTest,o);
    }
}
