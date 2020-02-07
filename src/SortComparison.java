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
        for(int i = 0; i < (a.length-1); i++)
        {
            int temp = i;
            for(int j= i+1; j < a.length; j++)
            {
                if(a[temp] > a[j])
                {
                    temp = j;
                }
            }
            if(temp != i)
            {
                double largerValue = a[i];
                a[i] = a[temp];
                a[temp] = largerValue;
            }
        }
        return null;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){

        int q = partition(a, 0, (a.length-1));
        quickSort(a, 0, q);
        quickSort(a, q+1, a.length-1);
        return a;
    }//end quicksort

    private static void quickSort(double[] a, int low, int high)
    {
        int q = partition(a, low, high);
        quickSort(a, low, q);
        quickSort(a, q+1, high);
        return;
    }

    private static int partition (double a[], int low, int high)
    {
        double pivot = a[low];
        int i = low - 1;
        int j = high + 1;
        while(true)
        {
            while(a[j] <= pivot) {j--;}
            while(a[i] >= pivot) {i++;}
            if(a[i] < a[j]) {swap(a, i, j);}
            else {
                swap(a, i, j);
                return j;
            }
        }
    }

    private static void swap(double [] a, int i, int j)
    {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return;
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
