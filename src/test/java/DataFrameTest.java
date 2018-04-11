import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import java.util.ArrayList;
import myExceptions.*;
import dataframe.*;

public class DataFrameTest {

    ArrayList<ArrayList<? extends Comparable>> col;
    ArrayList<String> labels;
    DataFrame dfToTest;
    DataFrame dfToTestCsv;

    @Before
    public void init(){
        dfToTestCsv = new DataFrame("src/main/java/csv/csv1.csv");
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

        try {
            dfToTest = new DataFrame(labels,col);
        } catch (ExceptionNotSameSize exceptionNotSameSize) {
            exceptionNotSameSize.printStackTrace();
        }
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

    @Test(expected = ExceptionNotSameSize.class)
    public void testConstructor() throws ExceptionNotSameSize {
        ArrayList<Integer> firstCol = new ArrayList<Integer>();
        firstCol.add(-1);
        firstCol.add(0);


        ArrayList<Double>  secondCol = new ArrayList<Double>();
        secondCol.add(1.0);
        secondCol.add(2.5);
        secondCol.add(0.0);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Entier");
        labels.add("Double");

        ArrayList<ArrayList<? extends Comparable>> col = new ArrayList<ArrayList<? extends Comparable>>();
        col.add(firstCol);
        col.add(secondCol);

        DataFrame dt = new DataFrame(labels, col);

    }

    @Test
    public void testSelectLabels() throws ExceptionNoSuchColumn {
        System.out.println("\n\n ----TEST SELECT LABELS, AFFICHAGE DU DATAFRAME DE BASE---- \n");
        dfToTest.printAll();

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Mot");
        DataFrame df = dfToTest.selectFromLabel(labels);
        System.out.println("\n\n SELECTION DE MOT POUR REFAIRE UNE DATA FRAME PUIS AFFICHAGE: \n");
        df.printAll();


        labels.add("Index");
        DataFrame df2 = dfToTest.selectFromLabel(labels);
        System.out.println("\n\n SELECTION DE MOT ET INDEX POUR REFAIRE UNE DATA FRAME PUIS AFFICHAGE: \n");
        df2.printAll();
    }

    @Test
    public void testSelectLines() throws ExceptionNoSuchColumn, ExceptionColBadIndex {
        System.out.println("\n\n ---- TEST SELECT LINES, AFFICHAGE DU DATAFRAME DE BASE. -----\n");
        dfToTest.printAll();

        DataFrame df = dfToTest.selectFromLine(0,0);
        System.out.println("\n\n SELECTION DE LA PREMIERE LIGNE POUR REFAIRE UNE DATA FRAME PUIS AFFICHAGE: \n");
        df.printAll();

        DataFrame df2 = dfToTest.selectFromLine(1,5);
        System.out.println("\n\n SELECTION DE LA DEUXIEME LIGNE A LA FIN POUR REFAIRE UNE DATA FRAME PUIS AFFICHAGE: \n");
        df2.printAll();
    }




    //////CSV PART//////

    @Test
    public void testPrintCsv(){
        System.out.println("\n\nTEST AFFICHAGE CSV scv1.csv\n \n");
        dfToTestCsv.printAll();
    }

    @Test
    public void testSizeCsv() throws ExceptionNoSuchColumn {

        assertEquals("Doit être égal à 6 pour la colonne Index",6, dfToTestCsv.getCount("Index"));
        assertEquals("Doit être égal à 6 pour la colonne Nom",6, dfToTestCsv.getCount("Nom"));
        assertEquals("Doit être égal à 6 pour la colonne Prénom",6, dfToTestCsv.getCount("Prénom"));
        assertEquals("Doit être égal à 6 pour la colonne Age",6, dfToTestCsv.getCount("Age"));
        assertEquals("Doit être égal à 6 pour la colonne Note",6, dfToTestCsv.getCount("Note"));
        assertEquals("Doit être égal à 6 pour la colonne HASH",6, dfToTestCsv.getCount("HASH"));

    }

    @Test(expected= ExceptionNoSuchColumn.class)
    public void testColonneExistCsv() throws ExceptionNoSuchColumn, ExceptionString {
        dfToTestCsv.getAvg("DontExists");
    }

    //INTEGER
    @Test
    public void testSumCsv() throws ExceptionString, ExceptionNoSuchColumn {
        assertEquals("Somme de la colonne Age",34.5, dfToTestCsv.getSum("Age"));
        assertEquals("Somme de la colonne Note",20.9, dfToTestCsv.getSum("Note"));
        assertEquals("Somme de la colonne Index",15, dfToTestCsv.getSum("Index"));
    }

    @Test(expected = ExceptionString.class)
    public void testSumStringCsv() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTestCsv.getSum("HASH");
    }

    @Test(expected= ExceptionString.class)
    public void testAvgStringCsv() throws ExceptionString, ExceptionNoSuchColumn {
        dfToTestCsv.getAvg("Prénom");
    }

    @Test
    public void testAvgCsv() throws ExceptionString, ExceptionNoSuchColumn {
        assertEquals("Moyenne de la colonne Age",34.5/6, dfToTestCsv.getAvg("Age"));
        assertEquals("Moyenne de la colonne Note",20.9/6, dfToTestCsv.getAvg("Note"));
        assertEquals("Moyenne de la colonne Index",15/6, dfToTestCsv.getAvg("Index"));
    }

    @Test
    public void testMinCsv() throws ExceptionNoSuchColumn {
        assertEquals("Minimum de la colonne Index",0, dfToTestCsv.getMin("Index"));
        assertEquals("Minimum de la colonne Age",2.0, dfToTestCsv.getMin("Age"));
        assertEquals("Minimum de la colonne Prénom","1", dfToTestCsv.getMin("Prénom"));
        assertEquals("Minimum de la colonne Nom","3", dfToTestCsv.getMin("Nom"));
        assertEquals("Minimum de la colonne HASH","1", dfToTestCsv.getMin("HASH"));
        assertEquals("Minimum de la colonne Note",1.0, dfToTestCsv.getMin("Note"));
    }

    @Test
    public void testMaxCsv() throws ExceptionNoSuchColumn {
        assertEquals("Maximum de la colonne Index",5, dfToTestCsv.getMax("Index"));
        assertEquals("Maximum de la colonne Age",9.0, dfToTestCsv.getMax("Age"));
        assertEquals("Maximum de la colonne Prénom","Papin", dfToTestCsv.getMax("Prénom"));
        assertEquals("Maximum de la colonne Nom","G", dfToTestCsv.getMax("Nom"));
        assertEquals("Maximum de la colonne HASH","ZSH", dfToTestCsv.getMax("HASH"));
        assertEquals("Maximum de la colonne Note",5.0, dfToTestCsv.getMax("Note"));
    }
}
