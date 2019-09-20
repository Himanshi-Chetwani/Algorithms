/*
 * Classname:QuickSort.java
 *
 * Version information:1
 *
 * Date:15/02/2019
 *@author :  Himanshi Chetwani
 */

import java.io.File;// imports File functionaility
import java.io.PrintWriter;// Imports writer functionality to file
import java.util.Arrays;// To print array

/**
 * Used to help find out running time of the original quick sort for two
 * different types of data sets
 */
public class QuickSort extends Generate_Data  {
    static int [] myArray;
    public static int partition(int [] qArray, int l, int r){
        int pivot=qArray[l];
        int i=l;
        int j=r;
        int q;
        int temp;
        while (i<j){
            while ((i<=r) && (qArray[i]<=pivot) ){
                i=i+1;
            }
            while ((j>=l)&&(qArray[j]>pivot)  ){
                j=j-1;
            }
            if(i<j){
                temp=qArray[i];
                qArray[i]=qArray[j];
                qArray[j]=temp;

            }
        }
        q=j;
        temp=qArray[l];
        qArray[l]=qArray[q];
        qArray[q]=temp;

        return q;
    }
    public static void quickSort(int qArray[], int l, int r){
        int q;
        if(l<r){
            q=partition(qArray,l,r);
            quickSort(qArray,l,q-1);
            quickSort(qArray,q+1,r);
        }
    }
    /**
     * Used to call the two data sets that have been generated to help
     * understand the efficiency of both algorithms.
     * @param args
     */
    public static void main(String[] args) {
        int low,high;
        long startTime,endTime,cpuTime1;
        double cpuTime;
        double cpuT=0;
        double cpuA=1;
        int prOfK[];
        int n[]={1000,10000,50000,100000,500000};
        // int n[]={500000};
        try {
            File file  = new File("Output_Qsort.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter writer = new PrintWriter(file);
            for (int index = 0; index < n.length; index++) {
                int temp = n[index];
                myArray = new int[temp];
                myArray = generate_numbers(n[index]);
                writer.println("n:" + n[index]);
                writer.println("UnSorted Data");
                writer.println(Arrays.toString(myArray));
                low = 0;
                high = myArray.length - 1;
                startTime = System.currentTimeMillis();
                quickSort(myArray, low, high);
                endTime = System.currentTimeMillis();
                cpuTime1 = endTime - startTime;
                cpuTime = (double) (cpuTime1) / (double) (1000);
                writer.println("Sorted Data");
                writer.println(Arrays.toString(myArray));
                writer.println("CPU Time for set 1  for n=  " + n[index] +
                        " is " + cpuTime + "seconds");
                prOfK = new int[n[index]];
                for (int m = 0; m < n[index] - 1; m++) {
                    prOfK[m] = possonDistribution(n[index]);
                }
                writer.println("Set 2 : Poisson distribution of data values");
                writer.println(Arrays.toString(prOfK));
                myArray = prOfK;
                startTime = System.currentTimeMillis();
                quickSort(myArray, low, high);
                endTime = System.currentTimeMillis();
                cpuTime1 = endTime - startTime;
                cpuTime = (double) (cpuTime1) / (double) (1000);
                writer.println("Sorted Data");
                writer.println(Arrays.toString(myArray));
                writer.println("CPU Time for set 2 for n = " + n[index] +
                        " is " + cpuTime + "seconds");
                writer.println(
                        "--------------------------------------------");
            }
            writer.close();
        }catch (Exception e){
            System.out.println("Error in file");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
