import org.junit.Before;
import org.junit.Test;

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
}
