public class smithWaterman {
    
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
 
        String str1 = "ATCAT";
        String str2 = "ATTATC";
        System.out.println("Common local alignment between \"" + str1 + "\" and \"" + str2 + "\": ");
        computeEditDistance(str1, str2);
        
    }
    
    private static String[] traceback(int i, int j, String str1, String str2, int[][] score, StringBuilder align1, StringBuilder align2){
         while(i > 0 && j > 0 && score[i][j] != 0) {
        
           int scoreCurrent = score[i][j];
           int scoreDiagonal = score[i - 1][j - 1];
           int scoreUp = score[i - 1][j];
           int scoreLeft = score[i][j - 1];
           
           if(scoreCurrent == scoreDiagonal + matchScore(str1.charAt(i - 1), str2.charAt(j - 1))){
               align1.append(str1.charAt(i - 1));
               align2.append(str2.charAt(j - 1));
               i--;
               j--;
           } else if(scoreCurrent == scoreUp + -1 * gapPenalty){
               align1.append(str1.charAt(i - 1));
               align2.append('-');
               i--;
           } else if(scoreCurrent == scoreLeft + -1 * gapPenalty){
               align1.append('-');
               align2.append(str2.charAt(j - 1));
               j--;
           }
         }
         return new String[]{align1.reverse().toString(), align2.reverse().toString(), i + "", j + ""};
    }

    private static void computeEditDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
       int[][] score = new int[m + 1][n + 1];
       
       int maxScore = 0;
       int maxScoreI = 0;
       int maxScoreJ = 0;
       for(int i = 1; i <= m ; i++){
           for(int j = 1; j <= n; j++){
               int match = score[i - 1][j - 1] + matchScore(str1.charAt(i - 1), str2.charAt(j - 1));
               int delete = score[i - 1][j] + -1 * gapPenalty;
               int insert = score[i][j - 1] + -1 * gapPenalty;
               
               score[i][j] = Math.max(0, Math.max(match, Math.max(delete, insert)));
               if(maxScore <= score[i][j]){
                   maxScore = score[i][j];
                   maxScoreI = i;
                   maxScoreJ = j;
               }
               
           }
       }
       System.out.println("The Score Matrix");
       printMatrix(score);
       System.out.println("Max: "+ maxScore);
       
       StringBuilder align1 = new StringBuilder();
       StringBuilder align2 = new StringBuilder();
       
       String[] res = traceback(maxScoreI, maxScoreJ, str1, str2, score, align1, align2);
       
        
      
       System.out.println(res[0] + " " + res[1]);

    }
}
