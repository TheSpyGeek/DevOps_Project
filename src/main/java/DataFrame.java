import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataFrame {

    //Une dataframe est un ensemble de DataCol.
    ArrayList<DataCol> setOfCol;

    /**
     * Construction d'un objet Dataframe à partir d'un ensemble de colonnes.
     * @param cols liste de colonnes.
     * @param labels liste des labels respectifs des cols.
     */
    public DataFrame(ArrayList<String> labels, ArrayList<ArrayList<Comparable>> cols){
        if(labels.size()!=cols.size()) {
            //Erreur, ils doivent faire la même taille
        }else{
            for(int i=0; i<cols.size(); i++){
                DataCol dc = new DataCol(labels.get(i), cols.get(i));
                setOfCol.add(dc);
            }
        }
    }

    /**
     * Construction d'un objet Dataframe à partir d'un fichier csv.
     * @param csvPath Chemin du fichier csv à parser.
     */
    public DataFrame(String csvPath){
        String line = "";
        String separator = ",";
        boolean firstPass = true;
        ArrayList<DataCol> cols = new ArrayList();
        try  {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {

                String[] lineContent = line.split(separator);

                for (int i=0; i<lineContent.length; i++){
                    //On part du principe que le premier élément de la colonne contient le label
                    //On construit alors un objet DataCol à partir de ce dernier.
                    if(firstPass){
                        cols.add(new DataCol(lineContent[i],new ArrayList<Comparable>()));
                        firstPass = false;
                    }else{//Puis on insert dans les bonnes colonnes.
                        cols.get(i).insert(lineContent[i]);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de l'ouverture du fichier.");
        }
    }

    /**
     * Fonction permettant d'afficher l'entiereté du DataFrame
     * @param begin Indique à partir de quelle ligne afficher.
     * @param end   Indique quand arrêter l'affichage. Si end = 0, alors tout afficher.
     */
    public void print(int begin, int end){
        int max = 0;
        for (DataCol c:setOfCol) {
            System.out.print(c.getLabel()+"\t");
            if(c.getSize()>max)
                max = c.getSize();
        }

        if(end==0){
            end = max;
        }
        else if(end>=begin){
            while(begin<end){
                System.out.print(begin+"\t");
                for (DataCol c:setOfCol) {
                    c.print(begin);
                }
                begin++;
            }
        }else{
            System.out.println("Out of range for printing!");
        }
    }

    /**
     * Renvoie un sous-ensemble de notre DataFrame en sélectionnant les données par ligne.
     * @param begin Borne inférieure
     * @param end Borne supérieure
     * @return Le sous-ensemble du DataFrame sélectionné via les lignes
     */
    public DataFrame selectLine(int begin, int end){
        return null;
    }

    /**
     * Renvoie un sous-ensemble de notre DataFrame en sélectionnant les données par colonne.
     * @param labels Nom des colonnes que l'on doit sélectionner
     * @return Le sous-ensemble du DataFrame sélectionné via les labels
     */
    public DataFrame selectLabel(ArrayList<String> labels){
        return null;
    }

}
