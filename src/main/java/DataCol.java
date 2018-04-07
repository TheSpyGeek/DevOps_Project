import java.util.ArrayList;

public class DataCol
{
    //Contient les données de toute une colonne
    private ArrayList<? extends Comparable> data;

    //Contient le label de notre colonne
    private String label;

    //Contient le type de notre colonne
    private String type;

    public DataCol(String label, ArrayList<? extends Comparable> data){
        this.label = label;
        this.data  = data;
    }
}
