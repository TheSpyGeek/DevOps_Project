import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DataFrame {

    //Une dataframe est un ensemble de DataCol.
    LinkedHashMap<String, DataCol> setOfCol;

    /**
     * Construction d'un objet Dataframe à partir d'un ensemble de colonnes.
     * @param cols liste de colonnes.
     * @param labels liste des labels respectifs des cols.
     */
    public DataFrame(ArrayList<String> labels, ArrayList<ArrayList<? extends Comparable>> cols){
        setOfCol = new LinkedHashMap<String, DataCol>();
        if(labels.size()!=cols.size())
            System.out.println("Warning, plus de labels que de colonnes.");

        int s = cols.get(0).size();
        for(int i = 1; i < cols.size(); i++){
            if(s!=cols.get(i).size()){
                System.out.println("ERROR: Une colonne a plus d'élément qu'une autre!");
                System.exit(1);
            }
        }

        for(int i=0; i<cols.size(); i++){
            DataCol dc = new DataCol(labels.get(i), cols.get(i));
            setOfCol.put(labels.get(i),dc);
        }
    }

    /**
     * Construction d'un objet Dataframe à partir d'un fichier csv.
     * @param csvPath Chemin du fichier csv à parser.
     */
    public DataFrame(String csvPath){
        setOfCol = new LinkedHashMap<String, DataCol>();

        String line = "";
        String separator = ",";
        boolean firstPass = true;
        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<Integer> types = new ArrayList<Integer>();
        ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
        try  {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {

                String[] lineContent = line.split(separator);

                for (int i=0; i<lineContent.length; i++){

                    //On part du principe que le premier élément de la colonne contient le label
                    if(firstPass){
                        System.out.println(lineContent[i]);
                        labels.add(lineContent[i]);
                        datas.add(new ArrayList<String>());
                        types.add(0);
                    }else{//Puis on insert dans les bonnes colonnes.
                        if(lineContent[i].equals("")){//Si la case est vide on met 0 à la place
                            datas.get(i).set(i,"0");
                        }else{//On détermine les types et on remplit les données sous forme de string pour l'instant pour chaque colonne
                            datas.get(i).add(lineContent[i]);

                            if(parseInteger(lineContent[i])!=null){
                                types.set(i,changeType(types.get(i),1));
                            }
                            else if(parseDouble(lineContent[i])!=null){
                                types.set(i,changeType(types.get(i),2));
                            }
                            else{
                                types.set(i,3);
                            }
                        }
                    }
                }
                firstPass = false;
            }

            ArrayList<Comparable>  tempD;

            //On parcours nos colonnes de String
            //sameType correspond au type définit
            for (int i = 0; i<datas.size(); i++) {
                tempD = new ArrayList<Comparable>();
                //Aucun type n'est définit pour l'instant
                for(String s: datas.get(i)){//Pour chaque donnée de notre colonne
                    if(types.get(i)==3){
                        break;
                    }else if(types.get(i)==2){
                        tempD.add(parseDouble(s));
                    }else if(types.get(i)==1){
                        tempD.add(parseInteger(s));
                    }
                }
                if(types.get(i)==3){//Si on est sorti, on envoie les données String
                    setOfCol.put(labels.get(i),new DataCol(labels.get(i),datas.get(i)));
                }else{//Sinon, on envoie les données parsées
                    setOfCol.put(labels.get(i),new DataCol(labels.get(i),tempD));
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de l'ouverture du fichier.");
        }
    }

    public int changeType(int t1, int t2){
        if(t1>t2){
            return t1;
        }
        return t2;
    }

    /**
     * Troisième constructeur à partir d'une hashMap
     * @param setOfCol HashMap correspondant au DataFrame.
     */
    public DataFrame(LinkedHashMap<String, DataCol> setOfCol){
        this.setOfCol = setOfCol;
    }

    private Double parseDouble(String s){
        try {
            return  Double.parseDouble(s);
        }catch (Exception e){
            return null;
        }
    }

    private Integer parseInteger(String s){
        try {
            return  Integer.parseInt(s);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Fonction permettant d'afficher l'entiereté du DataFrame
     * @param begin Indique à partir de quelle ligne afficher.
     * @param end   Indique quand arrêter l'affichage. Si end = -1, alors tout afficher.
     */
    public void print(int begin, int end){
        System.out.print("\t");
        Iterator i = setOfCol.keySet().iterator();
        while (i.hasNext()){
            System.out.print(setOfCol.get(i.next()).getLabel()+"\t");
        }
        System.out.println();
        if(end==-1){
            begin = 0;
            end = setOfCol.get(setOfCol.keySet().iterator().next()).getSize()-1;
        }


        if(end>=begin){
            while(begin<=end){
                System.out.print(begin+"\t");
                i = setOfCol.keySet().iterator();
                while (i.hasNext()){
                    setOfCol.get(i.next()).print(begin);
                }
                System.out.println();
                begin++;
            }
        }else{
            System.out.println("Out of range for printing!");
        }
    }

    public void printAll(){
        print(0,  (setOfCol.get(setOfCol.keySet().iterator().next()).getSize()-1));
    }

    /**
     * Renvoie un sous-ensemble de notre DataFrame en sélectionnant les données par ligne.
     * @param begin Borne inférieure
     * @param end Borne supérieure
     * @return Le sous-ensemble du DataFrame sélectionné via les lignes
     */
    public DataFrame selectFromLine(int begin, int end){
        LinkedHashMap<String, DataCol> newSetOfCol = new LinkedHashMap<String, DataCol>();
        if(begin>end){
            System.out.println("Erreur, borne inférieure est supérieure à la borne supérieure.");
        }else if (setOfCol.get(0).getSize()<end){
            System.out.println("Erreur, borne inférieure est supérieure à la borne supérieure.");
        }else{
            Iterator i = setOfCol.keySet().iterator();
            while(i.hasNext()){
                String s = (String) i.next();
                newSetOfCol.put(s,setOfCol.get(s).selectByLine(begin,end));
            }
        }
        return new DataFrame(newSetOfCol);
    }

    /**
     * Renvoie un sous-ensemble de notre DataFrame en sélectionnant les données par colonne.
     * @param labels Nom des colonnes que l'on doit sélectionner
     * @return Le sous-ensemble du DataFrame sélectionné via les labels
     */
    public DataFrame selectFromLabel(ArrayList<String> labels) throws ExceptionNoSuchColumn{
        LinkedHashMap<String, DataCol> newSetOfCol = new LinkedHashMap<String, DataCol>();

        for(String s: labels){
            if(setOfCol.containsKey(s)){
                newSetOfCol.put(s,setOfCol.get(s));
            }else{
                throw new ExceptionNoSuchColumn();
            }
        }

        return new DataFrame(newSetOfCol);
    }

    public Comparable getMax(String label) throws ExceptionNoSuchColumn{
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getMax();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

    public Comparable getMin(String label) throws ExceptionNoSuchColumn{
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getMin();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

    public Comparable getSum(String label) throws ExceptionString, ExceptionNoSuchColumn {
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getSum();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

    public Comparable getAvg(String label) throws ExceptionString, ExceptionNoSuchColumn {
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getAvg();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

    public int getCount(String label) throws ExceptionNoSuchColumn{
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getSize();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

}
