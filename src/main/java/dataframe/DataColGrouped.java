package dataframe;

import myExceptions.ExceptionString;

import java.util.ArrayList;

class DataColGrouped {
    //Chaque case contient à présent une liste de comparable
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


    public ArrayList<? extends Comparable> getSum() throws ExceptionString {
        if(data.get(0).get(0) instanceof Integer){
            ArrayList<Integer> sumVal = new ArrayList<Integer>();
            for(int i = 0; i<data.size(); i++){
                Integer sum = 0;
                for(int j = 0; j<data.get(i).size(); j++){
                    sum+= (Integer)data.get(i).get(j);
                }
                sumVal.add(sum);
            }
            return sumVal;
        }else if(data.get(0).get(0) instanceof Double){
            ArrayList<Double> sumVal = new ArrayList<Double>();
            for(int i = 0; i<data.size(); i++){
                Double sum = 0.0;
                for(int j = 0; j<data.get(i).size(); j++){
                    sum+= (Integer)data.get(i).get(j);
                }
                sumVal.add(sum);
            }
            return sumVal;
        }else {
            throw new ExceptionString();
        }
    }

    public ArrayList<? extends Comparable> getAvg() throws ExceptionString {
        if(data.get(0).get(0) instanceof Double){
            ArrayList<Double> sumToAvg = (ArrayList<Double>) getSum();
            for(int i =0; i<sumToAvg.size(); i++){
                sumToAvg.set(i, sumToAvg.get(i)/sumToAvg.size());
            }
            return sumToAvg;
        }else if(data.get(0).get(0) instanceof Integer){
            ArrayList<Integer> sumToAvg = (ArrayList<Integer>) getSum();
            for(int i =0; i<sumToAvg.size(); i++){
                sumToAvg.set(i, sumToAvg.get(i)/sumToAvg.size());
            }
            return sumToAvg;
        }else{
            throw new ExceptionString();
        }
    }
}
