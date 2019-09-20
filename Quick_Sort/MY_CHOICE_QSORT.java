/*
 * Classname:MY_CHOICE_QSORT.java
 *
 * Version information:1
 *
 * Date:15/05/2019
 *@author : Anuradha Nitin Bhave and Himanshi Chetwani
 */
import java.io.File; // imports File functionaility
import java.io.PrintWriter; // Imports writer functionality to file
import java.util.Arrays; // To print array

/**
 * Used to help find out running time of the modified quick sort for two
 * different types of data sets
 */
public class MY_CHOICE_QSORT extends Generate_Data {
    static  int [] myArray={};

    /**
     * Function that implements functionality of the modified quick sort
     * @param m
     * @param n
     * @param pivot_loc
     */
    public static void  qSorte(int m,int n, int pivot_loc)
    {
        int pivot,i,j,size,t;
        boolean lsorted,rsorted,flag;
        if(m<n){
            pivot=myArray[pivot_loc];
            i=m-1;
            j=n+1;
            flag=true;
            lsorted=true;
            rsorted=true;
            while (flag){
                i=i+1;
                while ((i<=n) && myArray[i]<pivot  ) {
                    //Determining if the left subfile is still in sorted order
                    if(lsorted){
                        if(i>m){
                            if(myArray[i]<myArray[i-1]){
                                lsorted=false;
                            }
                        }
                    }
                    i=i+1;
                }
                j=j-1;
                while ((j>=m) && (myArray[j]>=pivot)){
                    //Determine if the right subfile is still sorted
                    if(rsorted){
                        if(j<n){
                            if(myArray[j]>myArray[j+1]){
                                rsorted=false;
                            }
                        }
                    }
                    j=j-1;
                }
                if(i<j){
                    t=myArray[j];
                    myArray[j]=myArray[i];
                    myArray[i]=t;
                    //Check if the pivot value was moved
                    if(i==pivot_loc){
                        pivot_loc=j;
                    }
                    //Determine if the left subfile is still in sorted order
                    if(lsorted){
                        if(i>m){
                            if(myArray[i]<myArray[i-1]){
                                lsorted=false;
                            }
                        }
                    }
                    //Determine if the right subfile is till sorted
                    if(rsorted){
                        if(j<n){
                            if(myArray[j]>myArray[j+1]){
                                rsorted=false;
                            }
                        }

                    }

                }
                else {
                    flag = false;
                }
            }
            //If the right sub file is not sorted the swap the pivot value
            //  with myArray[i]. This also takes care of the special case for
            // an empty subfile
            if(!rsorted){
                //Swap myArray[i] and myArray[pivot_loc]
                t=myArray[i];
                myArray[i]=myArray[pivot_loc];
                myArray[pivot_loc]=t;
                i=i+1;
            }
            if(!lsorted){
                size=j-m+1;
                if(size>2){
                    qSorte(m,j,(m+j)/2);
                }
                else if(size==2){
                    if(myArray[m]>myArray[m+1]){
                        t=myArray[m];
                        myArray[m]=myArray[m+1];
                        myArray[m+1]=t;
                    }
                }
            }
            if(!rsorted){
                size=n-i+1;
                if(size>2){
                    qSorte(i,n,(i+n)/2);
                }
                else if(size==2){
                    if(myArray[n]<myArray[n-1]){
                        t=myArray[n];
                        myArray[n]=myArray[n-1];
                        myArray[n-1]=t;
                    }
                }
            }
        }

    }

    /**
     * Used to call the two data sets that have been generated to help
     * understand the efficiency of both algorithms.
     * @param args
     */
    public static void main(String[] args) {
        int pivot,low,high;
        long startTime,endTime,cpuTime1;
        double cpuTime;
        double cpuT;
        double cpuA;
        int prOfK[];
        double k;
        int n[]={1000,10000,50000,100000,500000};
        try {
            File file  = new File("Output_Qsorte.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter writer = new PrintWriter(file);
                for (int index = 0; index < n.length; index++) {
                    int temp = n[index];
                    myArray = new int[temp];
                    myArray = generate_numbers(n[index]);
                    writer.println("n:" + n[index]);
                    writer.println("UnSorted Data Set 1");
                    writer.println(Arrays.toString(myArray));
                    low = 0;
                    high = myArray.length - 1;
                    pivot = high / 2;
                    startTime = System.currentTimeMillis();
                    qSorte(low, high, pivot);
                    endTime = System.currentTimeMillis();
                    cpuTime1 = endTime - startTime;
                    cpuTime = (double) cpuTime1 / (double) (1000);
                    writer.println("Sorted Data Set 1");
                    writer.println(Arrays.toString(myArray));
                    writer.println("CPU Time for set 1 is  " + n[index] +
                            " is " + cpuTime + "seconds");
                    prOfK = new int[n[index]];
                    for (int m = 0; m < n[index] - 1; m++) {
                        prOfK[m] = possonDistribution(n[index]);
                    }
                    writer.println("Set 2 : Poisson distribution of data values");
                    writer.println(Arrays.toString(prOfK));
                    myArray = prOfK;
                    startTime = System.currentTimeMillis();
                    qSorte(low, high, pivot);
                    endTime = System.currentTimeMillis();
                    cpuTime1 = endTime - startTime;
                    cpuTime = (double) cpuTime1 / (double) (1000);
                    writer.println("Sorted Data");
                    writer.println(Arrays.toString(myArray));
                    writer.println("CPU Time for for Set 2 " + n[index] +
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
