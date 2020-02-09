// -------------------------------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;




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

    static double[] mergeSortIterative (double a[], int high) {
        for (int curr_size = 1; curr_size <= high; curr_size = 2*curr_size)
        {
            for (int low = 0; low < high; low += 2*curr_size)
            {
                int mid = Math.min(low + curr_size - 1, high);
                int right_end = Math.min(low + 2*curr_size - 1, high);

                a = merge(a, low, mid, right_end);
            }
        }
        return a;
    }//end mergesortIterative



    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[], int low, int high) {
        if(low < high)
        {
            int q = (low + high)/2;
            a = mergeSortRecursive(a, low, q);
            a = mergeSortRecursive(a, q+1, high);
            a = merge(a, low, q, high);
        }
        return a;
    }//end mergeSortRecursive

    static double [] merge (double a[], int low, int halfPoint, int high)
    {
        double [] firstArray = new double[halfPoint - low + 1];
        double [] secondArray = new double[high - halfPoint];
        System.arraycopy(a, low, firstArray, 0, firstArray.length);
        System.arraycopy(a, halfPoint+1, secondArray, 0, secondArray.length);
        int i = 0;
        int j = 0;
        int k = low;
        while ((i < halfPoint - low + 1) && (j < high - halfPoint))
        {
            if(firstArray[i] <= secondArray[j])
            {
                a[k] = firstArray[i];
                i++;
            }
            else
            {
                a[k] = secondArray[j];
                j++;
            }
            k++;
        }
        while (i < halfPoint - low + 1)
        {
            a[k] = firstArray[i];
            i++;
            k++;
        }
        while (j < high - halfPoint)
        {
            a[k] = secondArray[j];
            j++;
            k++;
        }
        return a;
    }







    public static void main(String[] args) throws FileNotFoundException {

        ten_random_test();
        one_hundred_random_test();
        one_thousand_random_test();
        one_thousand_few_unique();
        one_thousand_nearly();
        one_thousand_reverse();
        one_thousand_sorted();

    }

    public static void ten_random_test() throws FileNotFoundException {

        long start = System.nanoTime();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        Scanner scanner = new Scanner(new File("C:/Users/John/IdeaProjects/Algorithms-SortingComparison/assignment input data files/numbers10.txt"));
        double [] test10 = new double [10];
        int i = 0;
        while(scanner.hasNextInt()){
            test10[i++] = scanner.nextDouble();
        }        //todo: do experiments as per assignment instructions
        double [] clone_test10 = new double[10];

        start = System.nanoTime();
        clone_test10 = insertionSort(test10);
        finish = System.nanoTime();
        System.out.println("10-Insertion: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = selectionSort(test10);
        finish = System.nanoTime();
        System.out.println("10-Selection: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortIterative(test10, test10.length-1);
        finish = System.nanoTime();
        System.out.println("10-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortRecursive(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("10-Merge_Recursive: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = quickSort(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("10-Quick: " + (finish - start));

        System.out.print("\n\n");
    }

    public static void one_hundred_random_test() throws FileNotFoundException {

        long start = System.nanoTime();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        Scanner scanner = new Scanner(new File("C:/Users/John/IdeaProjects/Algorithms-SortingComparison/assignment input data files/numbers100.txt"));
        double [] test10 = new double [100];
        int i = 0;
        while(scanner.hasNextInt()){
            test10[i++] = scanner.nextDouble();
        }        //todo: do experiments as per assignment instructions
        double [] clone_test10 = new double[100];

        start = System.nanoTime();
        clone_test10 = insertionSort(test10);
        finish = System.nanoTime();
        System.out.println("100-Insertion: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = selectionSort(test10);
        finish = System.nanoTime();
        System.out.println("100-Selection: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortIterative(test10, test10.length-1);
        finish = System.nanoTime();
        System.out.println("100-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortRecursive(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("100-Merge_Recursive: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = quickSort(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("100-Quick: " + (finish - start));

        System.out.print("\n\n");
    }

    public static void one_thousand_random_test() throws FileNotFoundException {

        long start = System.nanoTime();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        Scanner scanner = new Scanner(new File("C:/Users/John/IdeaProjects/Algorithms-SortingComparison/assignment input data files/numbers1000.txt"));
        double [] test10 = new double [1000];
        int i = 0;
        while(scanner.hasNextInt()){
            test10[i++] = scanner.nextDouble();
        }        //todo: do experiments as per assignment instructions
        double [] clone_test10 = new double[1000];

        start = System.nanoTime();
        clone_test10 = insertionSort(test10);
        finish = System.nanoTime();
        System.out.println("1000-Insertion: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = selectionSort(test10);
        finish = System.nanoTime();
        System.out.println("1000-Selection: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortIterative(test10, test10.length-1);
        finish = System.nanoTime();
        System.out.println("1000-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortRecursive(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("1000-Merge_Recursive: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = quickSort(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("1000-Quick: " + (finish - start));

        System.out.print("\n\n");
    }

    public static void one_thousand_few_unique() throws FileNotFoundException {

        long start = System.nanoTime();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        Scanner scanner = new Scanner(new File("C:/Users/John/IdeaProjects/Algorithms-SortingComparison/assignment input data files/numbers1000Duplicates.txt"));
        double [] test10 = new double [1000];
        int i = 0;
        while(scanner.hasNextInt()){
            test10[i++] = scanner.nextDouble();
        }        //todo: do experiments as per assignment instructions
        double [] clone_test10 = new double[1000];

        start = System.nanoTime();
        clone_test10 = insertionSort(test10);
        finish = System.nanoTime();
        System.out.println("Unique-Insertion: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = selectionSort(test10);
        finish = System.nanoTime();
        System.out.println("Unique-Selection: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortIterative(test10, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Unique-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortRecursive(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Unique-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = quickSort(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Unique-Quick: " + (finish - start));

        System.out.print("\n\n");
    }

    public static void one_thousand_nearly() throws FileNotFoundException {

        long start = System.nanoTime();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        Scanner scanner = new Scanner(new File("C:/Users/John/IdeaProjects/Algorithms-SortingComparison/assignment input data files/numbersNearlyOrdered1000.txt"));
        double [] test10 = new double [1000];
        int i = 0;
        while(scanner.hasNextInt()){
            test10[i++] = scanner.nextDouble();
        }        //todo: do experiments as per assignment instructions
        double [] clone_test10 = new double[1000];

        start = System.nanoTime();
        clone_test10 = insertionSort(test10);
        finish = System.nanoTime();
        System.out.println("Nearly-Insertion: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = selectionSort(test10);
        finish = System.nanoTime();
        System.out.println("Nearly-Selection: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortIterative(test10, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Nearly-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortRecursive(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Nearly-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = quickSort(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Nearly-Quick: " + (finish - start));

        System.out.print("\n\n");
    }

    public static void one_thousand_reverse() throws FileNotFoundException {

        long start = System.nanoTime();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        Scanner scanner = new Scanner(new File("C:/Users/John/IdeaProjects/Algorithms-SortingComparison/assignment input data files/numbersReverse1000.txt"));
        double [] test10 = new double [1000];
        int i = 0;
        while(scanner.hasNextInt()){
            test10[i++] = scanner.nextDouble();
        }        //todo: do experiments as per assignment instructions
        double [] clone_test10 = new double[1000];

        start = System.nanoTime();
        clone_test10 = insertionSort(test10);
        finish = System.nanoTime();
        System.out.println("Reverse-Insertion: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = selectionSort(test10);
        finish = System.nanoTime();
        System.out.println("Reverse-Selection: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortIterative(test10, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Reverse-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortRecursive(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Reverse-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = quickSort(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Reverse-Quick: " + (finish - start));

        System.out.print("\n\n");
    }

    public static void one_thousand_sorted() throws FileNotFoundException {

        long start = System.nanoTime();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        Scanner scanner = new Scanner(new File("C:/Users/John/IdeaProjects/Algorithms-SortingComparison/assignment input data files/numbersSorted1000.txt"));
        double [] test10 = new double [1000];
        int i = 0;
        while(scanner.hasNextInt()){
            test10[i++] = scanner.nextDouble();
        }        //todo: do experiments as per assignment instructions
        double [] clone_test10 = new double[1000];

        //Test
        start = System.nanoTime();
        finish = System.nanoTime();
        timeElapsed = finish - start;

        start = System.nanoTime();
        clone_test10 = insertionSort(test10);
        finish = System.nanoTime();
        System.out.println("Sorted-Insertion: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = selectionSort(test10);
        finish = System.nanoTime();
        System.out.println("Sorted-Selection: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortIterative(test10, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Sorted-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = mergeSortRecursive(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Sorted-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        clone_test10 = quickSort(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Sorted-Quick: " + (finish - start));

        System.out.print("\n\n");
    }
}//end class
