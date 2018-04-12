import org.junit.Test;
import myExceptions.*;

import java.util.ArrayList;
import dataframe.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataColTest {


    @Test
    public void testMaxData(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(3);
        data.add(7);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals("Max Data", 7, datacol.getMax());
    }

    @Test
    public void testMinDataInteger(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(3);
        data.add(7);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals(3, datacol.getMin());
    }

    @Test
    public void testSumData() throws ExceptionString {
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(3);
        data.add(7);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals(10, datacol.getSum());
    }

    @Test(expected= ExceptionString.class)
    public void testSumDataWithString() throws ExceptionString {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");

        DataCol datacol = new DataCol("Prenom", data);

        datacol.getSum();
    }

    @Test
    public void testGetLabel(){
        DataCol datacol = new DataCol("Label", null);
        assertEquals("Label", datacol.getLabel());
    }

    @Test
    public void testGetSize(){
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        assertEquals(4, datacol.getSize());
    }

    @Test
    public void testSelectLine() throws ExceptionColBadIndex {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        DataCol newDatacol = datacol.selectByLine(2,3);

        assertEquals(2, newDatacol.getSize());
        assertEquals("Prenom", newDatacol.getLabel());
    }

    @Test(expected = ExceptionColBadIndex.class)
    public void testSelectLineNegativeIndex() throws ExceptionColBadIndex {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        DataCol newDatacol = datacol.selectByLine(-2,3);

    }

    @Test(expected = ExceptionColBadIndex.class)
    public void testSelectLineInversedIndex() throws ExceptionColBadIndex {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");

        DataCol datacol = new DataCol("Prenom", data);

        DataCol newDatacol = datacol.selectByLine(2,1);

    }

    @Test(expected = ExceptionColBadIndex.class)
    public void testSelectLineOutOfBoundIndex() throws ExceptionColBadIndex {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        DataCol newDatacol = datacol.selectByLine(2,5);

    }

    @Test(expected = ExceptionColBadIndex.class)
    public void testSelectLineOutOfBoundIndex2() throws ExceptionColBadIndex {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);

        DataCol newDatacol = datacol.selectByLine(2,7);

    }

    @Test
    public void testAvgInteger() throws ExceptionString {
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(5);
        data.add(5);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals(5, datacol.getAvg());
    }

    @Test
    public void testAvgDouble() throws ExceptionString {
        ArrayList<Double> data = new ArrayList<Double>();
        data.add(0.0);
        data.add(2.0);

        DataCol datacol = new DataCol("Entier", data);

        assertEquals(1.0, datacol.getAvg());
    }

    @Test(expected = ExceptionString.class)
    public void testAvgString() throws ExceptionString {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Victor");
        data.add("Maxime");
        data.add("Thomas");
        data.add("Antoine");

        DataCol datacol = new DataCol("Prenom", data);
        datacol.getAvg();
    }

}
