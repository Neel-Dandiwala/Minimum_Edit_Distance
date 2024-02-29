public class needlemanWunsch {
    
    public static int gapPenalty = 1;
    public static int matchAward = 1;
    public static int mismatchPenalty = -1;
    
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
    
    private static int matchScore(char a, char b){
        if(a == b) return matchAward;
        else if(a == '-' || b =='-') return -1 * gapPenalty;
        else return mismatchPenalty;
    }
    
    public static void main(String[] args) {
 
        String str1 = "ATTACA";
        String str2 = "ATGCT";
        System.out.println("Needleman-Wunsch Output between \"" + str1 + "\" and \"" + str2 + "\": ");
        computeEditDistance(str1, str2);
        
    }

    private static void computeEditDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
       int[][] score = new int[m + 1][n + 1];
       
       for(int i = 0; i <= m; i++) score[i][0] = -gapPenalty * i;
       for(int j = 0; j <= n; j++) score[0][j] = -gapPenalty * j;
       
       for(int i = 1; i <= m ; i++){
           for(int j = 1; j <= n; j++){
               int match = score[i - 1][j - 1] + matchScore(str1.charAt(i - 1), str2.charAt(j - 1));
               int delete = score[i - 1][j] + -1 * gapPenalty;
               int insert = score[i][j - 1] + -1 * gapPenalty;
               
               score[i][j] = Math.max(match, Math.max(delete, insert));
           }
       }
       System.out.println("The Score Matrix");
       printMatrix(score);
       
       String align1 = "";
       String align2 = "";
       
       int i = m;
       int j = n;
       
       while(i > 0 && j > 0){
           int scoreCurrent = score[i][j];
           int scoreDiagonal = score[i - 1][j - 1];
           int scoreUp = score[i - 1][j];
           int scoreLeft = score[i][j - 1];
           
           if(scoreCurrent == scoreDiagonal + matchScore(str1.charAt(i - 1), str2.charAt(j - 1))){
               align1 += str1.charAt(i - 1);
               align2 += str2.charAt(j - 1);
               i--;
               j--;
           } else if(scoreCurrent == scoreUp + -1 * gapPenalty){
               align1 += str1.charAt(i - 1);
               align2 += '-';
               i--;
           } else if(scoreCurrent == scoreLeft + -1 * gapPenalty){
               align1 += '-';
               align2 += str2.charAt(j - 1);
               j--;
           }
       }
       while(i > 0){
           align1 += str1.charAt(i - 1);
           align2 += '-';
           i--;
       }
       while(j > 0){
           align1 += '-';
           align2 += str2.charAt(j - 1);
           j--;
       }
       
       align1 = new StringBuffer(align1).reverse().toString();
       align2 = new StringBuffer(align2).reverse().toString();
       
       System.out.println(align1);
       System.out.println(align2);

    }
}
