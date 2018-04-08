import java.util.ArrayList;
import java.util.Collections;

/**
 * DataCol représente les colonnes de notre DataFrame.
 */
public class DataCol
{
    //Contient les données de toute une colonne
    private ArrayList<? extends Comparable> data;

    //Contient le label de notre colonne
    private String label;

    public DataCol(String label, ArrayList<? extends Comparable> data){
        this.label = label;
        this.data  = data;
    }

    /**
     * Affiche l'élément demandé à l'index indiqué.
     * @param i index de l'élément à afficher.
     */
    public void print(int i){
        if(i>data.size()){
            System.out.print("NULL\t");
        }else{
            System.out.println(data.get(i).toString()+"\t");
        }
    }

    /**
     * @return le nombre de donnée dans la colonne.
     */
    public int getSize(){
        return this.data.size();
    }

    /**
     * @return le nom de la colonne.
     */
    public String getLabel(){
        return this.label;
    }

    /**
     *
     * @return une nouvelle colonne avec les lignes demandées.
     */
    public DataCol selectByLine(int begin, int end){
        ArrayList<Comparable> newCol = new ArrayList<Comparable>();
        for(int i = begin; i < end; i++){
            newCol.add(data.get(i));
        }
        return new DataCol(this.label,newCol);
    }

    /**
     *
     * @return L'élément maximum du tableau.
     */
    public Comparable getMax(){
        return Collections.max(data);
    }

    /**
     *
     * @return L'élément minimum de la colonne.
     */
    public Comparable getMin(){
        return Collections.max(data);
    }

    /**
     * La colonne doit-être de type Numérique!
     * @return La somme des éléments de la colonne.
     */
    public Double getSum(){
        Double d = 0.0;
        if(data.get(0) instanceof Double){
            for(Comparable c: data){
                d+=(Double)c;
            }
        }else{
            System.out.println("Type invalide pour cette opération");
            return null;
        }
        return d;
    }

    /**
     * La colonne doit-être de type Numérique!
     * @return La moyenne des éléments de la colonne.
     */
    public Double getAvg(){
        Double d = 0.0;
        if(data.get(0) instanceof Double){
            for(int i = 0; i<data.size(); i++){
                d+=(Double)data.get(i);
            }
        }else{
            System.out.println("Type invalide pour cette opération");
            return null;
        }
        return d/data.size();
    }
}
