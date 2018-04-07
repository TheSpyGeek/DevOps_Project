import java.util.ArrayList;

/**
 * DataCol représente les colonnes de notre DataFrame.
 */
public class DataCol
{
    //Contient les données de toute une colonne
    private ArrayList<Comparable> data;

    //Contient le label de notre colonne
    private String label;

    public DataCol(String label, ArrayList<Comparable> data){
        this.label = label;
        this.data  = data;
    }


    /**
     * Méthode permettant d'insérer une nouvelle donnée dans notre dataframe.
     * Cette méthode va aussi vérifier que la donnée insérer correspond bien au même type que le type de la colonne.
     * Ici, on part du principe que dataToInsert doit avoir le même type que le premier élément de data.
     * @param dataToInsert donnée à insérer.
     */
    public void insert(String dataToInsert){
        Comparable realType;
        try{
            realType = Integer.parseInt(dataToInsert);
        }catch (Exception eTestInt){
            try{
                realType = Double.parseDouble(dataToInsert);
            }catch (Exception eTestDouble){ //On part du principe que c'est donc une String
                realType = dataToInsert;
            }
        }
        //La donnée à insérer est bien du même type que le reste de la colonne, on insert.
        if(data.size()>0){
            if(realType.getClass()==data.get(0).getClass()){
                data.add(realType);
            }else{
                System.out.println("Erreur, la colonne n'a pas de type homogène.");
            }
        }else{//On insert dans la colonne si ne c'est le premier élément de la colonne.
            data.add(realType);
        }
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
}
