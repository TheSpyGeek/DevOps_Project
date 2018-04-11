import dataframe.DataFrame;
import dataframe.DataFrameGrouped;
import myExceptions.ExceptionNoSuchColumn;
import myExceptions.ExceptionNotSameSize;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DataFrameGroupedTest {
    ArrayList<ArrayList<? extends Comparable>> col;
    ArrayList<String> labels;
    DataFrame dfToTest;
    DataFrameGrouped dfGroupedToTest;

    @Before
    public void init() throws ExceptionNoSuchColumn {
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
        thirdCol.add("Victor");
        thirdCol.add("Victor");
        thirdCol.add("Maxime");
        thirdCol.add("Maxime");
        thirdCol.add("Maxime");

        col.add(firstCol);
        col.add(secondCol);
        col.add(thirdCol);

        labels.add("Index");
        labels.add("Note");
        labels.add("Mot");

        try {
            dfToTest = new DataFrame(labels,col);
            ArrayList<String> groupByLabels = new ArrayList<String>();
            groupByLabels.add("Mot");
            dfGroupedToTest = dfToTest.groupBy(groupByLabels);
        } catch (ExceptionNotSameSize exceptionNotSameSize) {
            exceptionNotSameSize.printStackTrace();
        }
    }

    @Test
    public void testCountLineOnGroup(){
        System.out.println("\nTEST COUNT LINES:");
        System.out.println(dfGroupedToTest.count());
    }
}
