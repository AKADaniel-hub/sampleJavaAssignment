package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {
    
    
    //    @Test
    //    public void getMoviesActorYear() {
    //        assertTrue(Main.parseFiles(new File("test-files/getMoviesActorYear")));
    //        Result result = Main.execute("GET_MOVIES_ACTOR_YEAR 2010 Leonardo DiCaprio");
    //        assertNotNull(result);
    //        assertTrue(result.success);
    //        String[] resultParts = result.result.split("\n");
    //        assertArrayEquals(new String[]{
    //                "Shutter Island",
    //                "Inception"
    //        }, resultParts);
    //        result = Main.execute("GET_MOVIES_ACTOR_YEAR 1994 Tom Hanks");
    //        assertNotNull(result);
    //        assertTrue(result.success);
    //        resultParts = result.result.split("\n");
    //        assertArrayEquals(new String[]{
    //                "Forrest Gump"
    //        }, resultParts);
    //
    //
    //    }
    
    //    @Test
    //    public void INSERT_ACTOR_duplicateShouldReturnError() {
    //        assertTrue(Main.parseFiles(new File("test-files")));
    //
    //        ArrayList movieArray = Main.getObjects(TipoEntidade.FILME);
    //        assertEquals("27205 | Inception | 2010-07-14 | 3 | 1 | 3 | 0", movieArray.get(0).toString());
    //
    //
    //        // Inserção inicial
    //        Result result = Main.execute("INSERT_ACTOR 12331;daniel;M;27205");
    //        assertNotNull(result);
    //        assertTrue(result.success);
    //        assertEquals("OK", result.result);
    //
    //        movieArray = Main.getObjects(TipoEntidade.FILME);
    //
    //        assertEquals("27205 | Inception | 2010-07-14 | 3 | 1 | 4 | 0", movieArray.get(0).toString());
    //        //27205 | Inception | 2010-07-14 | 3 | 1 | 4 | 0
    //        // Inserção duplicada
    //        result = Main.execute("INSERT_ACTOR 12331;daniel;M;27205");
    //        assertNotNull(result);
    //        assertTrue(result.success);
    //        assertEquals("Erro", result.result);
    //
    //    }
    //
    
    @Test
    public void INSERT_ACTOR_duplicateDirectorIdShouldReturnError() {
        /*
        3,Uma Thurman,F,680
        */
        
        assertTrue(Main.parseFiles(new File("test-files")));
        ArrayList movieArrayAntes = Main.getObjects(TipoEntidade.FILME);
        
        
        //tenta inserri com mesmo id de director já existente
        Result result = Main.execute("INSERT_ACTOR 3;daniel;M;27205");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("Erro", result.result);
        
        // testa se MovieObject nao muda
        ArrayList movieArrayDepois = Main.getObjects(TipoEntidade.FILME);
        assertEquals(movieArrayAntes, movieArrayDepois);
        
        
    }
    
    @Test
    public void INSERT_ACTOR_movieIdNotFoundShouldReturnError() {
        
        
        assertTrue(Main.parseFiles(new File("test-files")));
        
        
        //tenta inserri com mesmo id de director já existente
        Result result = Main.execute("INSERT_ACTOR 12331;daniel;M;2723405");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("Erro", result.result);
        
        // testa se MovieObject nao muda
        
        
    }
    
    @Test
    public void COUNT_MOVIES_MONTH_YEAR() {
        
        assertTrue(Main.parseFiles(new File("test-files/test2")));
        
        
        Result result = Main.execute("COUNT_MOVIES_MONTH_YEAR 07 2010");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("1", result.result);
        
        result = Main.execute("COUNT_MOVIES_MONTH_YEAR 09 2010");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("0", result.result);
        
    }
    
    @Test
    public void COUNT_MOVIES_BETWEEN_YEARS_WITH_N_ACTORS() {
        
        assertTrue(Main.parseFiles(new File("test-files/test2")));
        
        
        Result result = Main.execute("COUNT_MOVIES_BETWEEN_YEARS_WITH_N_ACTORS 1993 2025 0 10");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("9", result.result);
        
        result = Main.execute("COUNT_MOVIES_BETWEEN_YEARS_WITH_N_ACTORS 2010 2025 0 10");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("1", result.result);
        
    }
    
    @Test
    public void TOP_VOTED_ACTORS_with_noResult() {
        
        assertTrue(Main.parseFiles(new File("test-files/test2")));
        Result result = Main.execute("TOP_VOTED_ACTORS 20 1994");
        assertNotNull(result);
        assertTrue(result.success);
        
        String[] restultPartes = result.result.split("\n");
        assertArrayEquals(new String[]{
                "Tim Robbins:9.3",
                "Morgan Freanan:9.3",
                "Uma Thurman:8.9",
                "Samuel L. Jackson:8.9",
                "John Travolta:8.9",
                "Tom Hanks:8.8",
                "Robin Wright:8.8"
        }, restultPartes);
        
        result = Main.execute("TOP_VOTED_ACTORS 10 0000");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("No results", result.result);
        
    }
    
    @Test
    public void TOP_VOTED_ACTORS_test_num_max() {
        
        assertTrue(Main.parseFiles(new File("test-files/test2")));
        Result result = Main.execute("TOP_VOTED_ACTORS 2 1994");
        assertNotNull(result);
        assertTrue(result.success);
        
        String[] restultPartes = result.result.split("\n");
        assertArrayEquals(new String[]{
                "Tim Robbins:9.3",
                "Morgan Freanan:9.3"
        }, restultPartes);
        
    }
    
    
    @Test
    public void GET_MOVIES_ACTOR_YEAR_with_noResult() {
        
        assertTrue(Main.parseFiles(new File("test-files/test2")));
        Result result = Main.execute("GET_MOVIES_ACTOR_YEAR 2000 Tom Skerritt");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("Cast Away", result.result);
        
        result = Main.execute("GET_MOVIES_ACTOR_YEAR 0000 Tom Skerritt");
        assertNotNull(result);
        assertTrue(result.success);
        assertEquals("No results", result.result);
        
    }
    
}
