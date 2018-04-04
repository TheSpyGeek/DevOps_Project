package main.java.pandas;

import java.util.ArrayList;
import java.util.HashMap;

public class DataFrame {

    public enum type {STRING, FLOAT, INTEGER, BOOLEAN};

    /* chaque colonne contient un tableau de comparable */
    /* chaque colonne est identifié par un label */
    HashMap<String, ArrayList<Comparable>> dataArray;

    public DataFrame(ArrayList<type> typeOfColumn){
        dataArray = new HashMap<>();
    }
}
