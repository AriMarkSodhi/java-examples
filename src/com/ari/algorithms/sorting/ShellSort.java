package com.ari.algorithms.sorting;

import java.util.Arrays;

public class ShellSort {
    /**
     *     - between O(n) and O(n2)
     *
     * SHELL SORT PARTITIONS
     * THE ORIGINAL LIST INTO
     * SUB-LISTS WHERE A SUB-
     * LIST IS MADE OF
     * ELEMENTS SEPARATED BY
     * AN “INCREMENT”
     * EACH SUB-LIST IS
     * THEN SORTED USING
     * INSERTION SORT
     * THE INCREMENT IS SLOWLY
     * REDUCED TILL IT’S 1
     * AT THIS POINT IT’S
     * BASICALLY INSERTION
     * SORT OF A NEARLY
     * SORTED LIST
     */
    public static void swap(int[] listToSort, int i, int j) {
        int temp = listToSort[i];
        listToSort[i] = listToSort[j];
        listToSort[j] = temp;
    }

    public static void insertionSort(int[] listToSort, int startIndex, int increment) {
        for (int i = startIndex; i < listToSort.length; i = i+increment) {
            for (int j = Math.min(i+increment, listToSort.length-1); (j - increment) >= 0; j = j - increment)
            {
                if (listToSort[j] < listToSort[j - increment])
                {
                    System.out.println("Before - " + Arrays.toString(listToSort));
                    System.out.println("Comparing [" + j + "," + (j - increment) + "] - swapping [" + listToSort[j] + "," + listToSort[j - increment] + "]");
                    swap(listToSort, j, j - increment);
                    System.out.println("After - " + Arrays.toString(listToSort));
                } else {
                    System.out.println("Comparing [" + j + "," + (j - increment) + "] - not swapping");
                    break;
                }
            }
            System.out.println("Completed pass " + i);
        }
    }

    public static void shellSort(int[] listToSort) {
        int increment = listToSort.length / 2;
        while (increment >= 1) {
            System.out.println("Setting increment ="+increment);
            for (int startIndex = 0; startIndex < increment; startIndex++) {
                insertionSort(listToSort, startIndex, increment);
            }
            increment = increment / 2;
        }
    }


    public static void main(String[] args) {
        int[] input = {3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12};
        /**
         * Setting increment =6
         * Comparing [6,0] - not swapping
         * Completed pass 0
         * Comparing [11,5] - not swapping
         * Completed pass 6
         * Comparing [7,1] - not swapping
         * Completed pass 1
         * Comparing [11,5] - not swapping
         * Completed pass 7
         * Before - [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Comparing [8,2] - swapping [5,7]
         * After - [3, 2, 5, 6, 8, 1, 9, 4, 7, 10, 19, 12]
         * Completed pass 2
         * Comparing [11,5] - not swapping
         * Completed pass 8
         * Comparing [9,3] - not swapping
         * Completed pass 3
         * Comparing [11,5] - not swapping
         * Completed pass 9
         * Comparing [10,4] - not swapping
         * Completed pass 4
         * Comparing [11,5] - not swapping
         * Completed pass 10
         * Comparing [11,5] - not swapping
         * Completed pass 5
         * Comparing [11,5] - not swapping
         * Completed pass 11
         * Setting increment =3
         * Comparing [3,0] - not swapping
         * Completed pass 0
         * Comparing [6,3] - not swapping
         * Completed pass 3
         * Comparing [9,6] - not swapping
         * Completed pass 6
         * Comparing [11,8] - not swapping
         * Completed pass 9
         * Comparing [4,1] - not swapping
         * Completed pass 1
         * Before - [3, 2, 5, 6, 8, 1, 9, 4, 7, 10, 19, 12]
         * Comparing [7,4] - swapping [4,8]
         * After - [3, 2, 5, 6, 4, 1, 9, 8, 7, 10, 19, 12]
         * Comparing [4,1] - not swapping
         * Completed pass 4
         * Comparing [10,7] - not swapping
         * Completed pass 7
         * Comparing [11,8] - not swapping
         * Completed pass 10
         * Before - [3, 2, 5, 6, 4, 1, 9, 8, 7, 10, 19, 12]
         * Comparing [5,2] - swapping [1,5]
         * After - [3, 2, 1, 6, 4, 5, 9, 8, 7, 10, 19, 12]
         * Completed pass 2
         * Comparing [8,5] - not swapping
         * Completed pass 5
         * Comparing [11,8] - not swapping
         * Completed pass 8
         * Comparing [11,8] - not swapping
         * Completed pass 11
         * Setting increment =1
         * Before - [3, 2, 1, 6, 4, 5, 9, 8, 7, 10, 19, 12]
         * Comparing [1,0] - swapping [2,3]
         * After - [2, 3, 1, 6, 4, 5, 9, 8, 7, 10, 19, 12]
         * Completed pass 0
         * Before - [2, 3, 1, 6, 4, 5, 9, 8, 7, 10, 19, 12]
         * Comparing [2,1] - swapping [1,3]
         * After - [2, 1, 3, 6, 4, 5, 9, 8, 7, 10, 19, 12]
         * Before - [2, 1, 3, 6, 4, 5, 9, 8, 7, 10, 19, 12]
         * Comparing [1,0] - swapping [1,2]
         * After - [1, 2, 3, 6, 4, 5, 9, 8, 7, 10, 19, 12]
         * Completed pass 1
         * Comparing [3,2] - not swapping
         * Completed pass 2
         * Before - [1, 2, 3, 6, 4, 5, 9, 8, 7, 10, 19, 12]
         * Comparing [4,3] - swapping [4,6]
         * After - [1, 2, 3, 4, 6, 5, 9, 8, 7, 10, 19, 12]
         * Comparing [3,2] - not swapping
         * Completed pass 3
         * Before - [1, 2, 3, 4, 6, 5, 9, 8, 7, 10, 19, 12]
         * Comparing [5,4] - swapping [5,6]
         * After - [1, 2, 3, 4, 5, 6, 9, 8, 7, 10, 19, 12]
         * Comparing [4,3] - not swapping
         * Completed pass 4
         * Comparing [6,5] - not swapping
         * Completed pass 5
         * Before - [1, 2, 3, 4, 5, 6, 9, 8, 7, 10, 19, 12]
         * Comparing [7,6] - swapping [8,9]
         * After - [1, 2, 3, 4, 5, 6, 8, 9, 7, 10, 19, 12]
         * Comparing [6,5] - not swapping
         * Completed pass 6
         * Before - [1, 2, 3, 4, 5, 6, 8, 9, 7, 10, 19, 12]
         * Comparing [8,7] - swapping [7,9]
         * After - [1, 2, 3, 4, 5, 6, 8, 7, 9, 10, 19, 12]
         * Before - [1, 2, 3, 4, 5, 6, 8, 7, 9, 10, 19, 12]
         * Comparing [7,6] - swapping [7,8]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 12]
         * Comparing [6,5] - not swapping
         * Completed pass 7
         * Comparing [9,8] - not swapping
         * Completed pass 8
         * Comparing [10,9] - not swapping
         * Completed pass 9
         * Before - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 12]
         * Comparing [11,10] - swapping [12,19]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         * Comparing [10,9] - not swapping
         * Completed pass 10
         * Comparing [11,10] - not swapping
         * Completed pass 11
         * Final - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         */
        shellSort(input);
        System.out.println("Final - " + Arrays.toString(input));
    }
}
