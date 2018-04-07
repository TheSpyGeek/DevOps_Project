import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dataframe {

    //Une dataframe est un ensemble de DataCol.
    ArrayList<DataCol> setOfCol;

    /**
     * Construction d'un objet Dataframe à partir d'un ensemble de colonnes.
     * @param cols liste de colonnes.
     * @param labels liste des labels respectifs des cols.
     */
    public Dataframe(ArrayList<String> labels, ArrayList<ArrayList<? extends Comparable>> cols){

    }

    /**
     * Construction d'un objet Dataframe à partir d'un fichier csv.
     * @param csvPath Chemin du fichier csv à parser.
     */
    public Dataframe(String csvPath){
        String line = "";
        String separator = ",";
        try  {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {

                //columnContent contient alors les données de notre première colonne.
                String[] columnContent = line.split(separator);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
