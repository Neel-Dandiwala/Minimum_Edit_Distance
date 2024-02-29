public class computeEditDistance {
    
    private static void printMatrix(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println();
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + "  ");
            }
        }
        System.out.println();
        System.out.println();
    }
    
    public static void main(String[] args) {
 
        String str1 = "intention";
        String str2 = "execution";

        int minEditDistance = calculateEditDistance(str1, str2);
        System.out.println("Minimum Edit Distance between \"" + str1 + "\" and \"" + str2 + "\": " + minEditDistance);
    }

    private static int calculateEditDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        int[][] backtrace = new int[m][n];
        // int[] backtrace = new int[m * n + 1];
        // -1 is Insertion [Left]
        // 0 is Deletion [Down]
        // +1 is Substitution [DIAG]
        // +2 is Match
        
        
        for(int i = 0; i <= m; i++) dp[i][0] = i;
        for(int j = 0; j <= n; j++) dp[0][j] = j;
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = dp[i - 1][j] + 1;
                backtrace[(i - 1)][(j - 1)] =  0;
                
                if(dp[i][j] > dp[i][j - 1] + 1){
                    dp[i][j] = dp[i][j - 1] + 1;
                    backtrace[(i - 1)][(j - 1)] =  -1;
                } 
                
                int match = str1.charAt(i - 1) != str2.charAt(j - 1) ? 2 : 0;
                
                if(dp[i][j] > dp[i - 1][j - 1] + match){
                    dp[i][j] = dp[i - 1][j - 1] + match;
                    if(match == 2) backtrace[(i - 1)][(j - 1)] =  1;
                    else backtrace[(i - 1)][(j - 1)] =  2;
                    
                }
                 
            }
        }
        
        System.out.println("Distance Matrix");
        printMatrix(dp);
        System.out.println("Backtrace Matrix");
        printMatrix(backtrace);
        return dp[m][n];

    }
}
