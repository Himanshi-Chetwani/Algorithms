/*
 * Classname:Generate_Data.java
 *
 * Version information:1
 *
 * Date:15/02/2019
 *@author : Himanshi Chetwani
 */
import java.util.Random;//To help generate random number

/**
 * Class used to generate two types of data sets
 */
public class Generate_Data {
    /**
     * Function that generates data randomly for every value of n
     * @param n
     * @return returns the random data generated of size n
     */
    public static int[] generate_numbers(int n){
        int newArray[] = new int[n];
        for(int index=0;index<n;index++)
        {
            Random random = new Random();
            newArray[index]=random.nextInt(500000);
        }
        return newArray;
    }

    /**
     * Uses poissons distribution to generate the data for set 2
     * @param n
     * @return Returns the data generated using poissons distribution
     */
    public static int possonDistribution(int n){
        double lamda=n/2;
        double l=Math.exp(-lamda);
        int k =1;
        double p=1;
        int counter=0;
        do {
            k=k+1;
            p=p*Math.random();
        }while (p>l);
        return k-1;
    }
}
