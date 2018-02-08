package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import fr.m2dl.aco.domain.Ant;


/**
 * Created by hichem on 26/01/2018.
 */
public class AntTest {

    private Ant ant;
    @Before
    public void setUp() {
        ant = new Ant(null);
    }

    @Test
    public void testCreateAnt() {

        assertNotNull(ant);
    }

}