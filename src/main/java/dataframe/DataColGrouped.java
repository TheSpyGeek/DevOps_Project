package dataframe;

import java.util.ArrayList;

class DataColGrouped {
    private ArrayList<ArrayList<Comparable>> data;
    private int nbLine = 0;

    public DataColGrouped(ArrayList<ArrayList<Comparable>> data){
        this.data  = data;
        nbLine = this.data.size();
    }

    public int getNbLine(){
        return nbLine;
    }

    public ArrayList<Integer> getCount(){
        ArrayList<Integer> countByLine = new ArrayList<Integer>();
        for (int i = 0; i < nbLine; i++){
            countByLine.add(this.data.get(i).size());
        }
        return countByLine;
    }



}
