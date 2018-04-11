import myExceptions.ExceptionNoSuchColumn;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DataColTestFromCsv {
    DataFrame dfToTest;

    @Before
    public void init(){
        dfToTest = new DataFrame("src/main/java/csv/csv1.csv");

    }

    @Test
    public void print(){
        dfToTest.printAll();
    }

    @Test
    public void testMaxCSV() throws ExceptionNoSuchColumn {
        DataFrame dt = new DataFrame("src/main/java/csv/csv1.csv");
        assertEquals(5.0, dt.getMax("Note"));
    }

}
