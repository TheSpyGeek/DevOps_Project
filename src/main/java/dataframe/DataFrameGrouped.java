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


    public void printColGrouped(){
        System.out.print("\t");
        for(int i = 0; i<colGroupedName.size(); i++){
            System.out.print(colGroupedName.get(i)+"\t");
        }
    }

    public void printValuesGrouped(ArrayList<? extends Comparable> values){
        System.out.println();
        for(int i = 0; i<nbLine; i++){
            System.out.print(i+"\t");
            for(int j = 0; j<colGroupedName.size(); j++){
                System.out.print(setOfCol.get(colGroupedName.get(j)).printGrouped(i)+"\t");
            }
            System.out.print(values.get(i));
            System.out.println();
        }
    }


    public void countPrint(){
        ArrayList<Integer> values = count();
        printColGrouped();
        System.out.print("COUNT");
        printValuesGrouped(values);
    }



    public void sumPrint(String label) throws ExceptionColIsGrouped, ExceptionString {
        ArrayList<? extends Comparable> values = sum(label);
        printColGrouped();
        System.out.print("SUM "+label);
        printValuesGrouped(values);
    }

    public void avgPrint(String label) throws ExceptionColIsGrouped, ExceptionString {
        ArrayList<? extends Comparable> values = avg(label);
        printColGrouped();
        System.out.print("AVG "+label);
        printValuesGrouped(values);
    }

    public void maxPrint(String label) throws ExceptionColIsGrouped {
        ArrayList<? extends Comparable> values = max(label);
        printColGrouped();
        System.out.print("MAX "+label);
        printValuesGrouped(values);
    }

    public void minPrint(String label) throws ExceptionColIsGrouped {
        ArrayList<? extends Comparable> values = min(label);
        printColGrouped();
        System.out.print("MIN "+label);
        printValuesGrouped(values);
    }

}
