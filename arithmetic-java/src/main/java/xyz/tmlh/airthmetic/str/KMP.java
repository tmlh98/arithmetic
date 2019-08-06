package xyz.tmlh.airthmetic.str;


/**
 *   KMP
 */
public class KMP {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "BCDABD";
        
        int index = kmpSearch(str1, str2);
        System.out.println(index);
    }
    
    public static int kmpSearch(String str1 , String str2) {
        int[] next = kmpNext(str2);
        
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while( j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1]; 
            }
            
            if(str2.charAt(j) == str1.charAt(i)) {
                j ++;
            }
            if(j == str2.length() ) {
                return i - j + 1;
            }
            
        }
        return -1;
    }
    
    /**
     * 获取部分匹配表
     * 
     * @param str
     * @return 
     * @return int[]
     */
    private static int[] kmpNext(String str) {
        int[] next = new int [str.length()];
        //next[0] 只能是0
        
        for (int i = 1, j = 0; i < next.length; i++) {
            while(j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j-1];
            }
            
            if(str.charAt(i) == str.charAt(j)) {
                j ++ ; 
            }
            next[i] = j;
        }
        
        return next;
    }
}
