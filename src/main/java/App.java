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
            DataFrame df = new DataFrame("file.csv");
            Scanner sc = new Scanner(System.in);

            while(true){

                String cmd = sc.nextLine();

                String cmdParsed[] = cmd.split(" ");

                if(cmdParsed.length >= 1){
                    try {
                        switch(cmdParsed[0]){
                            case "exit":
                                System.exit(0);
                                break;
                            case "avg":
                                if(cmdParsed.length >= 2){
                                    System.out.println("Average = " +df.getAvg(cmdParsed[1]));
                                } else {
                                    System.out.println("Give a label");
                                }

                                break;
                            case "min":
                                if(cmdParsed.length >= 2){
                                    System.out.println("Min = "+df.getMin(cmdParsed[1]));
                                } else {
                                    System.out.println("Give a label");
                                }
                                break;
                            case "max":
                                if(cmdParsed.length >= 2){
                                    System.out.println("Max = "+df.getMax(cmdParsed[1]));
                                } else {
                                    System.out.println("Give a label");
                                }

                                break;
                            case "count":
                                if(cmdParsed.length >= 2){
                                    System.out.println("Count = "+df.getCount(cmdParsed[1]));
                                } else {
                                    System.out.println("Give a label");
                                }
                                break;
                            default:
                        }
                    } catch (ExceptionString exceptionString) {
                        exceptionString.printStackTrace();
                    } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                        System.out.println("Ce label n'existe pas");
                    }
                }


            }

        } catch (IOException e) {
            System.out.println("Unable to open the file");
            e.printStackTrace();
        } catch (ExceptionNotSameSize exceptionNotSameSize) {
            System.err.println("Table not same size");
            exceptionNotSameSize.printStackTrace();
        }


    }
}