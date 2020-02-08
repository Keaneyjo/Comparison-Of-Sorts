// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author: John Keaney (keaneyjo@tcd.ie)
 *  Student ID: 18328855
 *  @version HT 2020
 */

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){
        for(int i = 1; i < a.length; i++)
        {
            double key = a[i];
            int j = i -1;
            while(j >= 0 && a[j] > key)
            {
                a[j + 1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
        return a;
    }//end insertionsort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
        for(int i = 0; i < (a.length-1 ); i++)
        {
            int temp = i;
            for(int j= i+1; j < a.length; j++)
            {
                if(a[temp] > a[j])
                {
                    temp = j;
                }
            }
            if(temp != i) { a = swap(a, i, temp); }
        }
        return a;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort(double[] a, int low, int high)
    {
        if(low < high) {
            int q = partition(a, low, high);
            a = quickSort(a, low, q - 1);
            a = quickSort(a, q + 1, high);
        }
        return a;
    }//end quicksort

    private static int partition (double a[], int low, int high)
    {
        /*
        Step 1 − Choose the highest index value has pivot
        Step 2 − Take two variables to point left and right of the list excluding pivot
        Step 3 − left points to the low index
        Step 4 − right points to the high
        Step 5 − while value at left is less than pivot move right
        Step 6 − while value at right is greater than pivot move left
        Step 7 − if both step 5 and step 6 does not match swap left and right
        Step 8 − if left ≥ right, the point where they met is new pivot
         */

        double pivot = a[high];
        int i = low-1;
        for(int j = low; j < high -1; j++)
        {
            if(a[j] <= pivot)
            {
                i++;
                a = swap(a, i, j);
            }
        }
        a = swap(a, i+1, high);
        return i+1;
    }

    private static double [] swap(double [] a, int i, int j)
    {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return a;
    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {

        //todo: implement the sort
        return null;
    }//end mergesortIterative



    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {


        //todo: implement the sort
        return null;
    }//end mergeSortRecursive







    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

}//end class
