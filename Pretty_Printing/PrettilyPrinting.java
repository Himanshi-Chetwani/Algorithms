import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrettilyPrinting {
    private int count = 0;
    private static String[] words;

    private int printOutput(int p[], int n) {
        int recursiveVariable;
        if (p[n] == 1)
            recursiveVariable = 1;
        else
            recursiveVariable = printOutput(p, p[n] - 1) + 1;
        StringBuilder tempString= new StringBuilder();

            for(int wordNo=p[n]-1;wordNo<n;wordNo++){
                tempString.append(words[wordNo]).append(" ");
            }
            tempString.append("\n");
            System.out.println(tempString);

        return recursiveVariable;
    }
    private void JustifyingText(int l[], int n, int M) {
        int [][] extras = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            extras[i][i] = M - l[i - 1];
            for (int j = i + 1; j <= n; j++)
                extras[i][j] = extras[i][j - 1] - l[j - 1] - 1;
        }
        int maxValue = Integer.MAX_VALUE;
        int[][] LineCount = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (extras[i][j] < 0)
                    LineCount[i][j] = maxValue;
                else if (j == n && extras[i][j] >= 0)
                    LineCount[i][j] = 0;
                else
                    LineCount[i][j] = extras[i][j] * extras[i][j];
            }
        }
        int cost[] = new int[n + 1];
        int p[] = new int[n + 1];
        cost[0] = 0;
        for (int j = 1; j <= n; j++) {
            cost[j] = maxValue;
            for (int i = 1; i <= j; i++) {
                if (cost[i - 1] != maxValue && LineCount[i][j] != maxValue &&
                        (cost[i - 1] + LineCount[i][j] < cost[j])) {
                    cost[j] = cost[i - 1] + LineCount[i][j];
                    p[j] = i;
                }
            }
        }

        printOutput(p, n);
    }

    public static void main(String args[]) {
        PrettilyPrinting prettilyPrinting = new PrettilyPrinting();
        System.out.println("Enter the name of the file");
        Scanner scanner = new Scanner(System.in);
        String inputFile = scanner.nextLine();
        System.out.println("Enter the location where the file is saved");
        Scanner scanner1 = new Scanner(System.in);
        String pathOfFile = scanner.nextLine();
        String fullPath=pathOfFile+"\\"+inputFile;
        File file =
                new File(fullPath);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc != null;
        while (sc.hasNext()) {
            prettilyPrinting.count++;
            sc.next();
        }
        words = new String[prettilyPrinting.count];
        int i = 0;
        File file1 =
                new File(fullPath);
        Scanner sc1 = null;
        try {
            sc1 = new Scanner(file1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc1 != null;
        while (sc1.hasNext()) {
            words[i] = sc1.next();
            i++;
        }
        int max = 0;
        int l[] = new int[words.length];
        for (int index = 0; index < words.length; index++) {
            l[index] = words[index].length();
            if(l[index]>max)
                max=l[index];
        }

        int n = l.length;
        System.out.println("Enter the value of the width");
        Scanner scanner3 = new Scanner(System.in);
        int M = scanner3.nextInt();
        if(M<max){
            System.out.println("Width can not be less than largest word in " +
                    "file. Exiting");
            System.exit(0);
        }
        prettilyPrinting.JustifyingText(l, n, M);
    }
}

