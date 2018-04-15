package dataframe;

import myExceptions.ExceptionColIsGrouped;
import myExceptions.ExceptionString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DataFrameGrouped {
    private LinkedHashMap<String,DataColGrouped> setOfCol;
    private ArrayList<String> colGroupedName;
    private int nbLine;

    public DataFrameGrouped(ArrayList<String> labels, ArrayList<String> colGroupedName, ArrayList<DataColGrouped> data){
        this.colGroupedName = colGroupedName;
        setOfCol = new LinkedHashMap<String, DataColGrouped>();
        for(int i=0; i<data.size(); i++){
            setOfCol.put(labels.get(i),data.get(i));
        }
        this.colGroupedName = colGroupedName;

        Iterator i = setOfCol.keySet().iterator();
        nbLine = setOfCol.get(i.next()).getNbLine();
    }

    public ArrayList<Integer> count(){
        Iterator i = setOfCol.keySet().iterator();
        return setOfCol.get(i.next()).getCount();
    }

    public ArrayList<? extends Comparable> sum(String label) throws ExceptionColIsGrouped, ExceptionString {
        if(colGroupedName.contains(label)){
            throw  new ExceptionColIsGrouped();
        }else{
            return setOfCol.get(label).getSum();
        }
    }

    public ArrayList<? extends Comparable> avg(String label) throws ExceptionColIsGrouped, ExceptionString {
        if(colGroupedName.contains(label)){
            throw  new ExceptionColIsGrouped();
        }else{
            return setOfCol.get(label).getAvg();
        }
    }

    public ArrayList<? extends Comparable> max(String label) throws ExceptionColIsGrouped {
        if(colGroupedName.contains(label)){
            throw  new ExceptionColIsGrouped();
        }else{
            return setOfCol.get(label).getMax();
        }
    }

    public ArrayList<? extends Comparable> min(String label) throws ExceptionColIsGrouped {
        if(colGroupedName.contains(label)){
            throw  new ExceptionColIsGrouped();
        }else{
            return setOfCol.get(label).getMin();
        }
    }


}
