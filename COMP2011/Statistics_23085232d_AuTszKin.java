
public class Statistics_23085232d_AuTszKin {

    static double median(int[] a) {
        int n = a.length;
        if (n == 0) throw new IllegalArgumentException("The input must be nonempty.");
        int mid = n / 2;
        if (mid * 2 != n) return a[mid];
        return (a[mid] + a[mid - 1]) / 2.0; 
    }
    
    /**
     * Calculates the median of the union of two <i>sorted</i> arrays.
     * The input arrays are both <i>sorted</i> and <i>cannot</i> be both empty.
     *
     * VERY IMPORTANT.
     * 
     * I've discussed this question with the following students:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. learn the algorithm from https://www.geeksforgeeks.org/median-two-sorted-arrays-different-sizes-ologminn-m/
     *     the Illustration help me a lot.
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(log n); space O(1).   
     */
    static double median(int[] a1, int[] a2) {
    	int n = a1.length, m = a2.length;
    	int total = n+m;
        if(n > m) return median(a2, a1);
        int i, j;
        int low = 0, high = n;
        while(low <= high) {
		    i = (low+high)/2; // 2 
		    j = (n+m+1)/2 - i; // 3
		    /*2nd low=3, high=4, i=3, j=2
		      max(1, 2)
		    */
		    //System.out.println(i + j);
		    if(a1[i-1] < a2[j] && a2[j-1] < a1[i]) {
		    	if(total % 2 == 0) {
		    		return (Math.max(a1[i-1], a2[j-1]) + Math.min(a1[i], a2[j]))/2.0;
		    	}
		    	else {
		    		return Math.max(a1[i-1], a2[j-1]);
		    	}
		    }
		    else if(a1[i-1] > a2[j]) {
		    	high = i-1;
		    }
		    else low = i+1;
        }
        return -1;
    }
	     
	public static void main(String[] args) {
		int[][] a = {{-2, 0, 1, 3}, {1, 2, 4, 8, 16}};
        System.out.println("The medians of the two arrays are " + median(a[0]) + ", " + median(a[1])
        + ", and the median of their union is " + median(a[0], a[1]));
	}
		
}
