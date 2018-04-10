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
        System.out.print(data.get(i).toString()+"\t");
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
    public DataCol selectByLine(int begin, int end) throws ExceptionColBadIndex{

        if(begin < 0 || end < 0 || end < begin || end >= getSize()){
            throw new ExceptionColBadIndex();
        }

        ArrayList<Comparable> newCol = new ArrayList<Comparable>();
        for(int i = begin; i <= end; i++){
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
        return Collections.min(data);
    }

    /**
     * La colonne doit-être de type Numérique!
     * @return La somme des éléments de la colonne.
     */
    public Comparable getSum() throws ExceptionString {
        if(data.get(0) instanceof Double){
            Double sum = 0.0;
            for(Comparable c: data){
                sum+=(Double)c;
            }
            return sum;
        }else if( data.get(0) instanceof Integer){
            Integer sum = 0;
            for(Comparable c: data){
                sum+=(Integer)c;
            }
            return sum;
        } else {
            throw new ExceptionString();
        }
    }

    /**
     * La colonne doit-être de type Numérique!
     * @return La moyenne des éléments de la colonne.
     */
    public Comparable getAvg() throws ExceptionString {
        if(data.get(0) instanceof Double)
            return (Double)((Double)getSum()/data.size());
        else if(data.get(0) instanceof Integer)
            return (Integer)getSum()/data.size();
        else
            throw new ExceptionString();
    }
}
