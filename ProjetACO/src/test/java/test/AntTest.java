package test;

import fr.m2dl.aco.domain.Ant;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


/**
 * Created by hichem on 26/01/2018.
 */
public class AntTest {

    private Ant ant;
    @Before
    public void setUp() {
        ant = new Ant();
    }

    @Test
    public void testCreateAnt() {

        assertNotNull(ant);
    }

}