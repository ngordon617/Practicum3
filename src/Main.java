import java.util.*;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String X = "GAADAT";
        String Y = "ADATAG";

        while (X.length() != 6 || Y.length() != 6) {
            System.out.print("Enter DNA sequence 1: ");
            X = scanner.nextLine();
            System.out.print("Enter DNA sequence 2: ");
            Y = scanner.nextLine();

            if (X.length() != 6 || Y.length() != 6) {
                System.out.println("Both DNA sequences need to have a length of 6, try again\n");
            }
        }

        int p = X.length();
        int d = Y.length();

        int[][] dynamicProg = new int[p + 1][d + 1];

        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= d; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dynamicProg[i][j] = 1 + dynamicProg[i - 1][j - 1];
                }
                else {
                    dynamicProg[i][j] = Math.max(dynamicProg[i - 1][j], dynamicProg[i][j - 1]);
                }
                printTable(dynamicProg);
            }
        }

        StringBuilder longestCommonSubsequence = new StringBuilder();
        int i = p, j = d;
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                longestCommonSubsequence.insert(0, X.charAt(i - 1));
                i--;
                j--;
            }
            else if (dynamicProg[i - 1][j] > dynamicProg[i][j - 1]) {
                i--;
            }
            else {
                j--;
            }
        }

        System.out.println("DNA Sequence 1: " + X);
        System.out.println("DNA Sequence 2: " + Y);
        System.out.println("Longest Common Subsequence: " + longestCommonSubsequence.toString());

        System.out.print("\nDo you want to test other examples? (yes/no): ");
        String modify = scanner.nextLine();
        if (modify.equalsIgnoreCase("yes")) {
            main(args);
        }
    }

    public static void printTable(int[][] dynamicProg) {

        System.out.println("Table:");
        for (int[] row : dynamicProg) {
            for (int value : row) {
                System.out.printf("%2d ", value);
            }
            System.out.println();
        }
        System.out.println(" ");
    }

}