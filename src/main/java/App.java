import dataframe.DataFrame;
import myExceptions.ExceptionNoSuchColumn;
import myExceptions.ExceptionNotSameSize;
import myExceptions.ExceptionString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class App {
	public static void main(String args[]){
        try {
            DataFrame df = new DataFrame("/FileTest/file.csv");

            ArrayList<String> labels = df.getLabel();

            System.out.println("Average:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getAvg(label)+"\t");
                } catch (ExceptionString exceptionString) {
                    System.out.print("NULL\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println("\n");

            System.out.println("Maximum:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getMax(label)+"\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println("\n");

            System.out.println("Minimum:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getMax(label)+"\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println("\n");

            System.out.println("Count:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getCount(label)+"\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println("\n");

            System.out.println("Sum:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getSum(label)+"\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                } catch (ExceptionString exceptionString) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println();

        } catch (IOException e) {
            System.err.println("Nous n'avons pas pu ouvrir le fichier \"file.csv\"");
        } catch (ExceptionNotSameSize exceptionNotSameSize) {
            System.err.println("Erreur les colonnes ne sont pas de la même taille");
        }


    }

    static public void printLabel(ArrayList<String> array){
	    for(String s: array){
            System.out.print(s + "\t");
        }
        System.out.println();
    }
}