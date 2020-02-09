import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Assert.*;
//import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.*;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author: John Keaney (keaneyjo@tcd.ie)
 *  Student ID: 18328855
 *  @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        double[] a = new double[10];
        a = SortComparison.insertionSort(a);
        double[] result = new double[10];
        assertEquals(true, Arrays.equals(a, result));
    }

    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    @Test
    public void testInsertionSort()
    {
        double[] a = new double[] {1, 3, 2, 4, 0};
        a = SortComparison.insertionSort(a);
        double[] result = new double[] {0, 1, 2, 3, 4};
        Assert.assertArrayEquals("Insertion Sort: True", result, a, 0);
    }


    @Test
    public void testSelectionSort()
    {
        double[] a = {1, 3, 2, 4, 0};
        a = SortComparison.selectionSort(a);
        double[] result = {0, 1, 2, 3, 4};
        Assert.assertArrayEquals("Selection Sort: True", result, a, 0);
    }

    @Test
    public void testQuickSort()
    {
        double[] a = {1, 3, 2};
        a = SortComparison.quickSort(a, 0, a.length-1);
        double[] result = {1, 2, 3};
        Assert.assertArrayEquals("Selection Sort: True", result, a, 0);
        a = new double []{1, 3, 2, 4, 0};
        a = SortComparison.quickSort(a, 0, a.length-1);
        result = new double []{0, 1, 2, 3, 4};
        Assert.assertArrayEquals("Selection Sort: Second True", result, a, 0);
    }

    @Test
    public void testMergeSortIterative()
    {
        double[] a = new double[] {1, 3, 2, 4, 0};
        a = SortComparison.mergeSortIterative(a, a.length-1);
        double[] result = new double[] {0, 1, 2, 3, 4};
        Assert.assertArrayEquals("Merge Sort Iterative: True", result, a, 0);

    }

    @Test
    public void testMergeSortRecursive()
    {
        double[] a = new double[] {1, 3, 2, 4, 0, 5};
        a = SortComparison.mergeSortRecursive(a, 0, a.length-1);
        double[] result = new double[] {0, 1, 2, 3, 4, 5};
        Assert.assertArrayEquals("Merge Sort Recursive: True", result, a, 0);
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
        //TODO: implement this method
    }

}
