// -------------------------------------------------------------------------

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author: John Keaney (keaneyjo@tcd.ie)
 *  Student ID: 18328855
 *  @version HT 2020
 */

/**
                            | Insert	    | Selection	    | Merge Recursive	   | Merge Iterative	    | Quick
     10 random	            | 4500ns	    | 6200ns	    |    9000ns            | 53900ns	            | 14200ns
     100 random	            | 57200ns	    | 99200ns	    |    15200ns	       | 61700ns	            | 61900ns
     1000 random	        | 532920ns	    | 144730ns	    |    170700ns	       | 542300ns	            | 218100ns
     1000 few unique	    | 669800ns	    | 213400ns	    |    124900ns	       | 141600ns	            | 154900ns
     1000 nearly ordered	| 483000ns	    | 1270300ns	    |    100900ns	       | 167500ns	            | 106600ns
     1000 reverse order	    | 1058300ns	    | 1305500ns	    |    157200ns	       | 216500ns	            | 147200ns
     1000 sorted	        | 7600ns	    | 1302600ns		|    134800ns          | 214600ns               | 312340ns
 */

/**
 a. Which of the sorting algorithms does the order of input have an impact on? Why?
 - The sorting algorithm where the order of input has the largest effect is Insertion sort. This is because it is quadratic.

 b. Which algorithm has the biggest difference between the best and worst performance, based
 on the type of input, for the input of size 1000? Why?
 - The largest difference in performance for best and worst case would be for insertion sort. This is because when it is it's best performance it will be doing
 virtually no sorting in the slightest and for the worst performance it would need to run through the nearly the entire array each time. The difference here is 0(n) Vs 0(n^2).

 c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
 based on the input size? Please consider only input files with random order for this answer.
 - The best/worst scalability in performance time based on input size is MergeSortRecursive and QuickSort respectively.

 d. Did you observe any difference between iterative and recursive implementations of merge
 sort?
 - In general, the recursive implementation seems to be faster than the iterative implementation.

 e. Which algorithm is the fastest for each of the 7 input files?
 - In general in seems that Merge Sort Recursive is the fastest.
 */
class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     */
    static double[] insertionSort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            double key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
        return a;
    }//end insertionSort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double[] selectionSort(double[] a) {
        for (int i = 0; i < (a.length - 1); i++) {
            int temp = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[temp] > a[j]) {
                    temp = j;
                }
            }
            if (temp != i) {
                double temp2 = a[i];
                a[i] = a[temp];
                a[temp] = temp2;
            }
        }
        return a;
    }//end selectionSort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double[] quickSort(double[] a, int low, int high) {
        if (low < high) {
            int q = partition(a, low, high);
            a = quickSort(a, low, q - 1);
            a = quickSort(a, q + 1, high);
        }
        return a;
    }//end quickSort

    private static int partition(double[] a, int low, int high) {
        double pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high - 1; j++) {
            if (a[j] <= pivot) {
                i++;
                a = swap(a, i, j);
            }
        }
        a = swap(a, i + 1, high);
        return i + 1;
    }

    private static double[] swap(double[] a, int i, int j) {
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

    static double[] mergeSortIterative(double a[], int high) {
        for (int curr_size = 1; curr_size <= high; curr_size = 2 * curr_size) {
            for (int low = 0; low < high; low += 2 * curr_size) {
                int mid = Math.min(low + curr_size - 1, high);
                int right_end = Math.min(low + 2 * curr_size - 1, high);

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
    static double[] mergeSortRecursive(double a[], int low, int high) {
        if (low < high) {
            int q = (low + high) / 2;
            a = mergeSortRecursive(a, low, q);
            a = mergeSortRecursive(a, q + 1, high);
            a = merge(a, low, q, high);
        }
        return a;
    }//end mergeSortRecursive

    static double[] merge(double a[], int low, int halfPoint, int high) {
        double[] firstArray = new double[halfPoint - low + 1];
        double[] secondArray = new double[high - halfPoint];
        System.arraycopy(a, low, firstArray, 0, firstArray.length);
        System.arraycopy(a, halfPoint + 1, secondArray, 0, secondArray.length);
        int i = 0;
        int j = 0;
        int k = low;
        while ((i < halfPoint - low + 1) && (j < high - halfPoint)) {
            if (firstArray[i] <= secondArray[j]) {
                a[k] = firstArray[i];
                i++;
            } else {
                a[k] = secondArray[j];
                j++;
            }
            k++;
        }
        while (i < halfPoint - low + 1) {
            a[k] = firstArray[i];
            i++;
            k++;
        }
        while (j < high - halfPoint) {
            a[k] = secondArray[j];
            j++;
            k++;
        }
        return a;
    }


    public static void main(String[] args) throws IOException {

        //tests numbers10.txt

        //        ten_random_test();
        //        one_hundred_random_test();
        //        one_thousand_random_test();
        //        one_thousand_few_unique();
        //        one_thousand_nearly();
        //        one_thousand_reverse();
        //        one_thousand_sorted();

        double[] testInput  = Files.lines(Paths.get("numbers10.txt"))
                .mapToDouble(Double::parseDouble).toArray();

        long startTime;
        long endTime;

        System.out.println("NUMBERS10.txt");

        startTime = System.nanoTime();
        SortComparison.insertionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("insertionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.selectionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("selectionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.quickSort(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("quickSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(testInput, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortIterative() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortRecursive() took " + (endTime - startTime) + "ns\n");


        testInput = Files.lines(Paths.get("numbers100.txt"))
                .mapToDouble(Double::parseDouble).toArray();
        System.out.println("NUMBERS100.txt");

        startTime = System.nanoTime();
        SortComparison.insertionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("insertionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.selectionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("selectionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.quickSort(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("quickSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(testInput, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortIterative() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortRecursive() took " + (endTime - startTime) + "ns");

        //tests numbers1000.txt

        System.out.println();

        testInput = Files.lines(Paths.get("numbers1000.txt"))
                .mapToDouble(Double::parseDouble).toArray();

        System.out.println("NUMBERS1000.txt");

        startTime = System.nanoTime();
        SortComparison.insertionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("insertionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.selectionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("selectionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.quickSort(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("quickSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(testInput, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortIterative() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortRecursive() took " + (endTime - startTime) + "ns");

        //tests numbers1000Duplicates.txt

        System.out.println();

        testInput = Files.lines(Paths.get("numbers1000Duplicates.txt"))
                .mapToDouble(Double::parseDouble).toArray();

        System.out.println("NUMBERS1000Duplicates.txt");

        startTime = System.nanoTime();
        SortComparison.insertionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("insertionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.selectionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("selectionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.quickSort(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("quickSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(testInput, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortIterative() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortRecursive() took " + (endTime - startTime) + "ns");

        //tests numbersNearlyOrdered1000.txt

        System.out.println();

        testInput = Files.lines(Paths.get("numbersNearlyOrdered1000.txt"))
                .mapToDouble(Double::parseDouble).toArray();

        System.out.println("numbersNearlyOrdered1000.txt");

        startTime = System.nanoTime();
        SortComparison.insertionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("insertionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.selectionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("selectionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.quickSort(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("quickSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(testInput,  testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortIterative() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortRecursive() took " + (endTime - startTime) + "ns");

        //tests numbersReverse1000.txt

        System.out.println();

        testInput = Files.lines(Paths.get("numbersReverse1000.txt"))
                .mapToDouble(Double::parseDouble).toArray();

        System.out.println("numbersReverse1000.txt");

        startTime = System.nanoTime();
        SortComparison.insertionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("insertionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.selectionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("selectionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.quickSort(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("quickSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(testInput, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortIterative() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortRecursive() took " + (endTime - startTime) + "ns");

        //tests numbersSorted1000.txt

        System.out.println();

        testInput = Files.lines(Paths.get("numbersSorted1000.txt"))
                .mapToDouble(Double::parseDouble).toArray();

        System.out.println("numbersSorted1000.txt");

        startTime = System.nanoTime();
        SortComparison.insertionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("insertionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.selectionSort(testInput);
        endTime = System.nanoTime();
        System.out.println("selectionSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.quickSort(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("quickSort() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortIterative(testInput, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortIterative() took " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        SortComparison.mergeSortRecursive(testInput, 0, testInput.length-1);
        endTime = System.nanoTime();
        System.out.println("mergeSortRecursive() took " + (endTime - startTime) + "ns");

    }


}


/*

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

        long start1 = System.nanoTime();
        clone_test10 = selectionSort(test10);
        long finish1 = System.nanoTime();
        System.out.println("100-Selection: " + (finish1 - start1));

        long start3 = System.nanoTime();
        clone_test10 = mergeSortIterative(test10, test10.length-1);
        long finish3 = System.nanoTime();
        System.out.println("100-Merge_Iterative: " + (finish3 - start3));

        long start4 = System.nanoTime();
        clone_test10 = mergeSortRecursive(test10, 0, test10.length-1);
        long finish4 = System.nanoTime();
        System.out.println("100-Merge_Recursive: " + (finish4 - start4));

        long start5 = System.nanoTime();
        clone_test10 = quickSort(test10, 0, test10.length-1);
        long finish5 = System.nanoTime();
        System.out.println("100-Quick: " + (finish5 - start5));

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
        double [] clone_test101 = selectionSort(test10);
        finish = System.nanoTime();
        System.out.println("Unique-Selection: " + (finish - start));

        start = System.nanoTime();
        double [] clone_test102 = mergeSortIterative(test10, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Unique-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        double [] clone_test103 = mergeSortRecursive(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Unique-Merge_Iterative: " + (finish - start));

        start = System.nanoTime();
        double [] clone_test104 = quickSort(test10, 0, test10.length-1);
        finish = System.nanoTime();
        System.out.println("Unique-Quick: " + (finish - start));

        System.out.print("\n\n");
        // Compares arrays to see if they have been truly sorted correctly
        System.out.println("one: " + Arrays.equals(clone_test10, clone_test101));
        System.out.println("two: " + Arrays.equals(clone_test102, clone_test103));
        System.out.println("three: " + Arrays.equals(clone_test102, clone_test104));

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

 */
