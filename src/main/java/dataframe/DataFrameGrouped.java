package dataframe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DataFrameGrouped {
    private LinkedHashMap<String,DataColGrouped> setOfCol;
    private ArrayList<String> colGroupedName;

    public DataFrameGrouped(ArrayList<String> labels, ArrayList<String> colGroupedName, ArrayList<DataColGrouped> data){
        this.colGroupedName = colGroupedName;
        setOfCol = new LinkedHashMap<String, DataColGrouped>();
        for(int i=0; i<data.size(); i++){
            setOfCol.put(labels.get(i),data.get(i));
        }
        this.colGroupedName = colGroupedName;
    }

    public ArrayList<Integer> count(){
        Iterator i = setOfCol.keySet().iterator();
        return setOfCol.get(i.next()).getCount();
    }


}
