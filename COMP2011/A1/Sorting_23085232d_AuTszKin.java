import java.util.Arrays;

import Sorting_23085232d_AuTszKin.Element;

/**
 * 
 * @author Yixin Cao (September 11, 2021)
 *
 * A better implementation of insertion sort.
 * 
 * The ith major iteration of insertion sort puts element a[i] to its correct position.
 * Your algorithm should use <i>binary search</i> to find the position of the first element 
 * that is greater than (> not >=) a[i], or i if there does exist such an element.
 * 
 * The comparison is conducted via the {@link Comparable#compareTo(java.lang.Object)} method.  
 * Please be reminded that the comparisons are <i>not</i> on the values of the elements.
 * 
 * To facilitate your testing, we have included an extra field {@code originalPos} 
 * in the {@code Element} class. It stores the index of this element in the input array.
 * When an element is output, originalPos is printed in parentheses.
 * 
 * If your implementation is correct, elements of the same value should respect their original order,
 * e.g., for input {3, 10, 20, 3}, the output should be [10 (1), 3 (0), 20 (2), 3 (3)].
 */
public class Sorting_23085232d_AuTszKin { // Please change!
    /*
     * Each element has a secret and the original position in the input array.
     * Modifications of this class are strictly forbidden. 
     */
    static class Element implements Comparable<Element> {
        private int originalPos;
        private int secret;
        
        public Element(int i, int s) {
            if (s <= 0) {
                throw new IllegalArgumentException("The secret value must be a positive integer."); 
            }
            originalPos = i; 
            secret = s;
        }
        
        /*
         * For this assignment, you do <i>not</i> need to understand the {@code compareTo} method.
         */
        @Override public int compareTo(Element other) {
            int a = secret, b = other.secret;
            if (a == b) return 0; // can be removed.
            while (a != 1 && b != 1) {
                if (a / 2 * 2 == a) a /= 2;
                else a = a * 3 + 1;
                if (b / 2 * 2 == b) b /= 2;
                else b = b * 3 + 1;
            }
            return a - b;
        }
        
        public String toString() {
            return (String.valueOf(secret)) + " (" + String.valueOf(originalPos) + ")";
        }
    }
    
    /**
     * VERY IMPORTANT.
     * 
     * I've discussed this question with the following students:
     *     1. 23087882d I didn't discuss the question with him much but he tell me that the "compare_to" fuction is value of 3n+1 
     *     when I ask why {3, 10, 20, 3}, the output should be [10, 3, 20, 3].
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. no
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O( nlog n ).   (shift elements linearly takes (n) and binary search takes (log n))
     */ 
    public static void insertionSort(Element[] a) {
        int i, j, n = a.length;
        int position;
        Element key;
        for(i = 1; i < n; i++) {
        	//boolean check = true;
        	key = a[i];
        	int low = 0, high = i-1;
        	while(low <= high) {
        		int mid = low+(high-low)/2;
        		//System.out.println("key = " + key + "low = " + low + " mid = " + mid + " high = " + high);
        		if (a[mid].compareTo(key) > 0) {
                    high = mid-1;
                   // System.out.println("key = " + key + "low = " + low + " mid = " + mid + " high = " + high);
                } 
        		else {
        			low = mid+1;
                }
        	}
        	position = low;
        	//System.out.println("pos = "+ position + " check = " + check);
	    	for (j = i-1; j >= position; j--) {
	            a[j+1] = a[j];
	        }
	    	a[position] = key;
        	//System.out.println(Arrays.toString(a));
        }
    }
    
    // The original insertion sort is copied for your reference.
    public static void insertionSortOrig(Element[] a) {
        int i, j, n = a.length;
        Element key;
        for (i = 1; i < n; i++) {
            key = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (a[j].compareTo(key) <= 0) break;  
            	a[j + 1] = a[j];
            }
            a[j + 1] = key;
        }
    }
    
    // The binary search algorithm is copied for your reference.
  /*  public static int binarySearch(int[] a, int key) {
        int n = a.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == key) return mid;
            else if (a[mid] > key) high = mid - 1;
            else if (a[mid] < key) low = mid + 1; 
        }
        return -1;
    }*/
    
    public static void main(String[] args) {
        int input[] = {3, 3, 20, 10}; // try different inputs.
        int n = input.length;
        Element[] a = new Element[n];
        for (int i = 0; i < input.length; i++) 
            a[i] = new Element(i, input[i]);
        
        System.out.println("Original: " + Arrays.toString(a));
        insertionSort(a);
        System.out.println("After sorted: " + Arrays.toString(a));
    }
}

