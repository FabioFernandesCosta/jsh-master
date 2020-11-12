package test.java.jsh.so;


import main.java.jsh.so.ComandosInternos;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    
    final static ComandosInternos ci = new ComandosInternos();
    public static String userDir;
    
    
    @Test
    public void testRel() {
        assertEquals(ci.exibirRelogio(), 1);
    }

    @Test
    public void testCd(){
        assertEquals(ci.criarNovoDiretorio("testDir"), 1);
    }
    @Test
    public void testAd(){
        assertEquals(ci.apagarDiretorio("testDir"), 1);
    }
    @Test
    public void testLa(){
        userDir = System.getProperty("user.dir");
        assertEquals(ci.escreverListaArquivos(java.util.Optional.ofNullable(userDir)), 1);
    }
    @Test
    public void testMdt(){
        assertEquals(ci.mudarDiretorioTrabalho("C:\\\\"), 1);
    }
}
