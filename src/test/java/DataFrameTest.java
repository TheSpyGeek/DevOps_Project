import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import myExceptions.*;

public class DataFrameTest {

    ArrayList<ArrayList<? extends Comparable>> col;
    ArrayList<String> labels;
    DataFrame dfToTest;

    @Before
    public void init(){
        col = new ArrayList<ArrayList<? extends Comparable>>();
        labels = new ArrayList<String>();
        ArrayList<Integer> firstCol = new ArrayList<Integer>();
        firstCol.add(-1);
        firstCol.add(0);
        firstCol.add(1);
        firstCol.add(2);
        firstCol.add(3);
        firstCol.add(8);

        ArrayList<Double>  secondCol = new ArrayList<Double>();
        secondCol.add(1.0);
        secondCol.add(2.5);
        secondCol.add(0.0);
        secondCol.add(0.4);
        secondCol.add(0.0);
        secondCol.add(0.1);

        ArrayList<String>  thirdCol = new ArrayList<String>();
        thirdCol.add("Victor");
        thirdCol.add("Maxime");
        thirdCol.add("Malotru");
        thirdCol.add("Vaccination");
        thirdCol.add("Abeille");
        thirdCol.add("Zébulon!");

        col.add(firstCol);
        col.add(secondCol);
        col.add(thirdCol);

        labels.add("Index");
        labels.add("Note");
        labels.add("Mot");

        dfToTest = new DataFrame(labels,col);

    }

    @Test
    public void printAll(){
        dfToTest.printAll();
    }

    @Test
    public void printFirstLine(){
        dfToTest.print(0,0);
    }

    @Test
    public void printFirstLines(){
        dfToTest.print(2,3);
    }


    @Test
    public void testSize() throws ExceptionNoSuchColumn {

        assertEquals("Doit être égal à 6 pour la colonne Index",6, dfToTest.getCount("Index"));
        assertEquals("Doit être égal à 6 pour la colonne Note",6, dfToTest.getCount("Note"));
        assertEquals("Doit être égal à 6 pour la colonne Mot",6, dfToTest.getCount("Mot"));

    }

    @Test(expected= ExceptionNoSuchColumn.class)
    public void testColonneExist() throws ExceptionNoSuchColumn, ExceptionString {

        dfToTest.getAvg("DontExists");

    }

    //INTEGER
    @Test
    public void testSum() throws ExceptionString, ExceptionNoSuchColumn {
        assertEquals("Somme de la colonne Index",13, dfToTest.getSum("Index"));
        assertEquals("Somme de la colonne Note",4.0, dfToTest.getSum("Note"));
    }

    @Test(expected = ExceptionString.class)
    public void testSumString() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTest.getSum("Mot");
    }

    @Test
    public void testAvg() throws ExceptionString, ExceptionNoSuchColumn {
        assertEquals("Moyenne de la colonne Index",(13/6), dfToTest.getAvg("Index"));
        assertEquals("Moyenne de la colonne Note",(double)4/6, dfToTest.getAvg("Note"));
    }

    @Test(expected= ExceptionString.class)
    public void testAvgString() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTest.getAvg("Mot");
    }

    @Test
    public void testMin() throws ExceptionNoSuchColumn {
        assertEquals("Minimum de la colonne Index",-1, dfToTest.getMin("Index"));
        assertEquals("Minimum de la colonne Mot","Abeille", dfToTest.getMin("Mot"));
        assertEquals("Minimum de la colonne Note",0.0, dfToTest.getMin("Note"));
    }

    @Test
    public void testMax() throws ExceptionNoSuchColumn {
        assertEquals("Maximum de la colonne Index",8, dfToTest.getMax("Index"));
        assertEquals("Maximum de la colonne Mot","Zébulon!", dfToTest.getMax("Mot"));
        assertEquals("Maximum de la colonne Note",2.5, dfToTest.getMax("Note"));
    }
}
