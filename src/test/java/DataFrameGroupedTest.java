import dataframe.DataFrame;
import dataframe.DataFrameGrouped;
import myExceptions.ExceptionColIsGrouped;
import myExceptions.ExceptionNoSuchColumn;
import myExceptions.ExceptionNotSameSize;
import myExceptions.ExceptionString;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class DataFrameGroupedTest {
    ArrayList<ArrayList<? extends Comparable>> col;
    ArrayList<String> labels;
    DataFrame dfToTest;
    DataFrameGrouped dfGroupedToTestGroupedByNom;
    DataFrameGrouped dfGroupedToTestGroupedByNomPrenom;

    @Before
    public void init() throws ExceptionNoSuchColumn, ExceptionNotSameSize {
        col = new ArrayList<ArrayList<? extends Comparable>>();
        labels = new ArrayList<String>();

        ArrayList<String>  firstCol = new ArrayList<String>();
        firstCol.add("Baverel");
        firstCol.add("Isnel");
        firstCol.add("Gomes");
        firstCol.add("Isnel");
        firstCol.add("Baverel");
        firstCol.add("Valdes");




        ArrayList<String>  thirdCol = new ArrayList<String>();
        thirdCol.add("Victor");
        thirdCol.add("Maxime");
        thirdCol.add("Selena");
        thirdCol.add("Maxime");
        thirdCol.add("Victor");
        thirdCol.add("Victor");




        ArrayList<Integer>  secondCol = new ArrayList<Integer>();
        secondCol.add(3000);
        secondCol.add(4000);
        secondCol.add(13000);
        secondCol.add(10);
        secondCol.add(2000);
        secondCol.add(1000);




        ArrayList<Double>  fifthCol = new ArrayList<Double>();
        fifthCol.add(3000.0);
        fifthCol.add(4000.0);
        fifthCol.add(13000.0);
        fifthCol.add(10.0);
        fifthCol.add(2000.0);
        fifthCol.add(1000.0);

        ArrayList<String>  fourthCol = new ArrayList<String>();
        fourthCol.add("Bel-ami");
        fourthCol.add("L'assommoir");
        fourthCol.add("Le rouge et le noir");
        fourthCol.add("20 000 lieues sous les mers");
        fourthCol.add("Kafka sur le rivage");
        fourthCol.add("Le bourreau de Gaudi");

        col.add(thirdCol);
        col.add(firstCol);
        col.add(secondCol);
        col.add(fifthCol);
        col.add(fourthCol);


        labels.add("Prénom");
        labels.add("Nom");
        labels.add("RevenusInt");
        labels.add("RevenusDouble");
        labels.add("Livres préférés");


        dfToTest = new DataFrame(labels,col);
        ArrayList<String> colsToGroup = new ArrayList<String>();
        colsToGroup.add("Prénom");
        dfGroupedToTestGroupedByNom = dfToTest.groupBy(colsToGroup);

        colsToGroup.add("Nom");
        dfGroupedToTestGroupedByNomPrenom = dfToTest.groupBy(colsToGroup);
    }


    /*CES PREMIERS TESTS S'EFFECTUEUNT SUR UN GROUP BY UNIQUEMENT SUR LA COLONNE PRENOM*/

    @Test
    public void testCountLineOnGroup(){
        ArrayList<Integer> waitedResult = new ArrayList<Integer>();
        waitedResult.add(3);
        waitedResult.add(2);
        waitedResult.add(1);
        assertTrue("Le résultat doit-être [3,2,1]",
                waitedResult.containsAll(dfGroupedToTestGroupedByNom.count()));




        waitedResult = new ArrayList<Integer>();
        waitedResult.add(2);
        waitedResult.add(2);
        waitedResult.add(1);
        waitedResult.add(1);
        assertTrue("Le résultat doit-être [2,2,1,1]",
                waitedResult.containsAll(dfGroupedToTestGroupedByNomPrenom.count()));
    }

    @Test
    public void testSumLineOnGroup() throws ExceptionString, ExceptionColIsGrouped {


        ArrayList<Integer> resultWaited = new ArrayList<Integer>();
        resultWaited.add(6000);
        resultWaited.add(4010);
        resultWaited.add(13000);

        assertTrue("Victor doit avoir 6000, Maxime 4010, Selena 13000",
                resultWaited.containsAll(dfGroupedToTestGroupedByNom.sum("RevenusInt")));


        ArrayList<Double> resultWaited2 = new ArrayList<Double>();
        resultWaited2.add(5000.0);
        resultWaited2.add(4010.0);
        resultWaited2.add(13000.0);
        resultWaited2.add(1000.0);
        assertTrue("Victor Baverel doit avoir 5000, Maxime Isnel 4010, Selena Gomes 13000, Victor Valdes 1000",
                resultWaited2.containsAll(dfGroupedToTestGroupedByNomPrenom.sum("RevenusDouble")));
    }


    @Test
    public void testAvgLineOnGroup() throws ExceptionString, ExceptionColIsGrouped {
        ArrayList<Integer> resultWaited = new ArrayList<Integer>();
        resultWaited.add(2000);
        resultWaited.add(2005);
        resultWaited.add(13000);
        assertTrue("Victor doit avoir 2000, Maxime 2005, Selena 13000",
                resultWaited.containsAll(dfGroupedToTestGroupedByNom.avg("RevenusInt")));



        ArrayList<Double> resultWaited2 = new ArrayList<Double>();
        resultWaited2.add(2500.0);
        resultWaited2.add(2005.0);
        resultWaited2.add(13000.0);
        resultWaited2.add(1000.0);
        assertTrue("Victor Baverel doit avoir 2500, Maxime Isnel 2005, Selena Gomes 13000, Victor Valdes 1000",
                resultWaited2.containsAll(dfGroupedToTestGroupedByNomPrenom.avg("RevenusDouble")));
    }

    @Test
    public void testMaxLineOnGroup() throws ExceptionColIsGrouped {
        ArrayList<Integer> resultWaited = new ArrayList<Integer>();
        resultWaited.add(3000);
        resultWaited.add(4000);
        resultWaited.add(13000);


        assertTrue("Victor doit avoir 2000, Maxime 2005, Selena 13000",
                resultWaited.containsAll(dfGroupedToTestGroupedByNom.max("RevenusInt")));



        ArrayList<Double> resultWaited2 = new ArrayList<Double>();
        resultWaited2.add(3000.0);
        resultWaited2.add(4000.0);
        resultWaited2.add(13000.0);
        resultWaited2.add(1000.0);

        assertTrue("Victor Baverel doit avoir 3000, Maxime Isnel 2000, Selena Gomes 13000, Victor Valdes 1000",
                resultWaited2.containsAll(dfGroupedToTestGroupedByNomPrenom.max("RevenusDouble")));




        ArrayList<String> resultWaited3 = new ArrayList<String>();
        resultWaited3.add("Kafka sur le rivage");
        resultWaited3.add("L'assommoir");
        resultWaited3.add("Le rouge et le noir");
        resultWaited3.add("Le bourreau de Gaudi");

        assertTrue("Victor Baverel doit avoir Kafka sur le rivage, Maxime Isnel L'assommoir," +
                        " Selena Gomes Le Rouge et le Noir, Victor Valdes Le Bourreau de Gaudi",
                resultWaited3.containsAll(dfGroupedToTestGroupedByNomPrenom.max("Livres préférés")));
    }

    @Test
    public void testMinLineOnGroup() throws ExceptionColIsGrouped {
        ArrayList<Integer> resultWaited = new ArrayList<Integer>();
        resultWaited.add(1000);
        resultWaited.add(10);
        resultWaited.add(13000);

        assertTrue("Victor doit avoir 1000, Maxime 10, Selena 13000",
                resultWaited.containsAll(dfGroupedToTestGroupedByNom.min("RevenusInt")));


        ArrayList<Double> resultWaited2 = new ArrayList<Double>();
        resultWaited2.add(2000.0);
        resultWaited2.add(10.0);
        resultWaited2.add(13000.0);
        resultWaited2.add(1000.0);

        assertTrue("Victor Baverel doit avoir 2000, Maxime Isnel 10, Selena Gomes 13000, Victor Valdes 1000",
                resultWaited2.containsAll(dfGroupedToTestGroupedByNomPrenom.min("RevenusDouble")));


        ArrayList<String> resultWaited3 = new ArrayList<String>();
        resultWaited3.add("Bel-ami");
        resultWaited3.add("20 000 lieues sous les mers");
        resultWaited3.add("Le rouge et le noir");
        resultWaited3.add("Le bourreau de Gaudi");

        assertTrue("Victor Baverel doit avoir Bel-ami, Maxime Isnel 20 000 lieues sous les mers," +
                        " Selena Gomes Le Rouge et le Noir, Victor Valdes Le Bourreau de Gaudi",
                resultWaited3.containsAll(dfGroupedToTestGroupedByNomPrenom.max("Livres préférés")));
    }


}
