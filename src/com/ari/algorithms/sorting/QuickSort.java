package com.ari.algorithms.sorting;

import java.util.Arrays;

public class QuickSort {
    /**
     *     - O(log n)
     *
     * QuickSort is A DIVIDE AND
     * CONQUER ALGORITHM WHICH
     * PARTITIONS THE LIST AT EVERY
     * STEP
     * THE PARTITION IS NOT BASED ON
     * THE LENGTH OR AN ARTIFICIAL
     * INDEX, IT’S BASED ON A PIVOT
     * THE PIVOT IS AN ELEMENT
     * FROM THE LIST
     * THE LIST IS PARTITIONED WITH
     * ALL ELEMENTS SMALLER THAN
     * THE PIVOT ON ONE SIDE AND
     * LARGER THAN THE PIVOT ON THE
     * OTHER
     * THIS PIVOT PARTITION IS
     * APPLIED TO ALL SUB-LISTS
     * TILL THE LIST IS SORTED
     *
     * ALL THAT IS NEEDED THAT THE
     * ELEMENTS SMALLER THAN THE PIVOT MOVE TO
     * THE LEFT, AND THOSE LARGER MOVE TO THE RIGHT
     * THEY NEED NOT BE IN ORDER ON EITHER SIDE OF THE PIVOT
     */
    public static void swap(int[] listToSort, int i, int j) {
        int temp = listToSort[i];
        listToSort[i] = listToSort[j];
        listToSort[j] = temp;
    }

    public static void quickSort(int[] listToSort, int low, int high) {
        if (low >= high) return;
        int pivotIndex = partition(listToSort, low, high);
        System.out.println("Calling QuickSort - Pivot ="+pivotIndex+" low="+low+" high="+high);
        quickSort(listToSort, low, pivotIndex - 1);
        quickSort(listToSort, pivotIndex + 1, high);
    }

    public static int partition(int[] listToSort, int low, int high) {
        int pivot = listToSort[low];
        int l = low;
        int h = high;
        while (l < h) {
            while (listToSort[l] <= pivot && l < h) l++;
            while (listToSort[h] > pivot) h--;
            if (l < h) {
                System.out.println("Before - "+Arrays.toString(listToSort));
                System.out.println("Found [l,h] == [" + l + "," + h + "] - swapping [" + listToSort[l] + "," + listToSort[h] + "]");
                swap(listToSort, l, h);
                System.out.println("After - "+Arrays.toString(listToSort));
            }
        }
        if (low != h) {
            System.out.println("Before - "+Arrays.toString(listToSort));
            System.out.println("Found [l,h] == [" + low + "," + h + "] - swapping [" + listToSort[low] + "," + listToSort[h] + "]");
            swap(listToSort, low, h);
            System.out.println("After - "+Arrays.toString(listToSort));

        }
        return h;
    }


    public static void main(String[] args) {
        int[] input = {3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12};
        /**
         * Before - [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [2,5] - swapping [7,1]
         * After - [3, 2, 1, 6, 8, 7, 9, 4, 5, 10, 19, 12]
         * Before - [3, 2, 1, 6, 8, 7, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [0,2] - swapping [3,1]
         * After - [1, 2, 3, 6, 8, 7, 9, 4, 5, 10, 19, 12]
         * Calling QuickSort - Pivot =2 low=0 high=11
         * Calling QuickSort - Pivot =0 low=0 high=1
         * Before - [1, 2, 3, 6, 8, 7, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [4,8] - swapping [8,5]
         * After - [1, 2, 3, 6, 5, 7, 9, 4, 8, 10, 19, 12]
         * Before - [1, 2, 3, 6, 5, 7, 9, 4, 8, 10, 19, 12]
         * Found [l,h] == [5,7] - swapping [7,4]
         * After - [1, 2, 3, 6, 5, 4, 9, 7, 8, 10, 19, 12]
         * Before - [1, 2, 3, 6, 5, 4, 9, 7, 8, 10, 19, 12]
         * Found [l,h] == [3,5] - swapping [6,4]
         * After - [1, 2, 3, 4, 5, 6, 9, 7, 8, 10, 19, 12]
         * Calling QuickSort - Pivot =5 low=3 high=11
         * Calling QuickSort - Pivot =3 low=3 high=4
         * Before - [1, 2, 3, 4, 5, 6, 9, 7, 8, 10, 19, 12]
         * Found [l,h] == [6,8] - swapping [9,8]
         * After - [1, 2, 3, 4, 5, 6, 8, 7, 9, 10, 19, 12]
         * Calling QuickSort - Pivot =8 low=6 high=11
         * Before - [1, 2, 3, 4, 5, 6, 8, 7, 9, 10, 19, 12]
         * Found [l,h] == [6,7] - swapping [8,7]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 12]
         * Calling QuickSort - Pivot =7 low=6 high=7
         * Calling QuickSort - Pivot =9 low=9 high=11
         * Before - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 12]
         * Found [l,h] == [10,11] - swapping [19,12]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         * Calling QuickSort - Pivot =11 low=10 high=11
         * Final - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         */
        quickSort(input, 0, input.length - 1);
        System.out.println("Final - "+Arrays.toString(input));
    }
}
