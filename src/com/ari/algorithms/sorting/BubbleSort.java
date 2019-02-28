package com.ari.algorithms.sorting;

import java.util.Arrays;

public class BubbleSort {
    /**
     *     - O(n2)
     *
     * BubbleSort
     * FOR EACH ITERATION, EVERY
     * ELEMENT IS COMPARED WITH
     * ITS NEIGHBOR AND SWAPPED
     * IF THEY ARE NOT IN ORDER .
     * THIS RESULTS IN SMALLER
     * ELEMENTS BUBBLING TO THE
     * BEGINNING OF THE LIST
     * AT THE END OF THE FIRST ITERATION, THE
     * SMALLEST ELEMENT IS IN THE RIGHT
     * POSITION, AT THE END OF THE SECOND
     * ITERATION THE SECOND SMALLEST IS IN
     * THE RIGHT POSITION AND SO ON
     */
    public static void swap(int[] listToSort, int i, int j) {
        int temp = listToSort[i];
        listToSort[i] = listToSort[j];
        listToSort[j] = temp;
    }

    public static void bubbleSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length; i++)
        {
            boolean swapped = false;
            for (int j = listToSort.length - 1; j > i; j--)
            {
                if (listToSort[j] < listToSort[j - 1]) {
                    System.out.println("Before - "+Arrays.toString(listToSort));
                    System.out.println("Found [i,j] == [" + i + "," + j + "] - swapping [" + listToSort[i] + "," + listToSort[j] + "]");
                    swap(listToSort, j, j - 1);
                    System.out.println("After - "+Arrays.toString(listToSort));
                    swapped = true;
                }
            }
            System.out.println("Iteration i="+i+" completed");
            if (!swapped) break;
        }
    }


    public static void main(String[] args) {
        int[] input = {3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12};
        /**
         * Before - [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Found [i,j] == [0,11] - swapping [3,12]
         * After - [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 12, 19]
         * Before - [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 12, 19]
         * Found [i,j] == [0,7] - swapping [3,4]
         * After - [3, 2, 7, 6, 8, 1, 4, 9, 5, 10, 12, 19]
         * Before - [3, 2, 7, 6, 8, 1, 4, 9, 5, 10, 12, 19]
         * Found [i,j] == [0,5] - swapping [3,1]
         * After - [3, 2, 7, 6, 1, 8, 4, 9, 5, 10, 12, 19]
         * Before - [3, 2, 7, 6, 1, 8, 4, 9, 5, 10, 12, 19]
         * Found [i,j] == [0,4] - swapping [3,1]
         * After - [3, 2, 7, 1, 6, 8, 4, 9, 5, 10, 12, 19]
         * Before - [3, 2, 7, 1, 6, 8, 4, 9, 5, 10, 12, 19]
         * Found [i,j] == [0,3] - swapping [3,1]
         * After - [3, 2, 1, 7, 6, 8, 4, 9, 5, 10, 12, 19]
         * Before - [3, 2, 1, 7, 6, 8, 4, 9, 5, 10, 12, 19]
         * Found [i,j] == [0,2] - swapping [3,1]
         * After - [3, 1, 2, 7, 6, 8, 4, 9, 5, 10, 12, 19]
         * Before - [3, 1, 2, 7, 6, 8, 4, 9, 5, 10, 12, 19]
         * Found [i,j] == [0,1] - swapping [3,1]
         * After - [1, 3, 2, 7, 6, 8, 4, 9, 5, 10, 12, 19]
         * Iteration i=0 completed
         * Before - [1, 3, 2, 7, 6, 8, 4, 9, 5, 10, 12, 19]
         * Found [i,j] == [1,8] - swapping [3,5]
         * After - [1, 3, 2, 7, 6, 8, 4, 5, 9, 10, 12, 19]
         * Before - [1, 3, 2, 7, 6, 8, 4, 5, 9, 10, 12, 19]
         * Found [i,j] == [1,6] - swapping [3,4]
         * After - [1, 3, 2, 7, 6, 4, 8, 5, 9, 10, 12, 19]
         * Before - [1, 3, 2, 7, 6, 4, 8, 5, 9, 10, 12, 19]
         * Found [i,j] == [1,5] - swapping [3,4]
         * After - [1, 3, 2, 7, 4, 6, 8, 5, 9, 10, 12, 19]
         * Before - [1, 3, 2, 7, 4, 6, 8, 5, 9, 10, 12, 19]
         * Found [i,j] == [1,4] - swapping [3,4]
         * After - [1, 3, 2, 4, 7, 6, 8, 5, 9, 10, 12, 19]
         * Before - [1, 3, 2, 4, 7, 6, 8, 5, 9, 10, 12, 19]
         * Found [i,j] == [1,2] - swapping [3,2]
         * After - [1, 2, 3, 4, 7, 6, 8, 5, 9, 10, 12, 19]
         * Iteration i=1 completed
         * Before - [1, 2, 3, 4, 7, 6, 8, 5, 9, 10, 12, 19]
         * Found [i,j] == [2,7] - swapping [3,5]
         * After - [1, 2, 3, 4, 7, 6, 5, 8, 9, 10, 12, 19]
         * Before - [1, 2, 3, 4, 7, 6, 5, 8, 9, 10, 12, 19]
         * Found [i,j] == [2,6] - swapping [3,5]
         * After - [1, 2, 3, 4, 7, 5, 6, 8, 9, 10, 12, 19]
         * Before - [1, 2, 3, 4, 7, 5, 6, 8, 9, 10, 12, 19]
         * Found [i,j] == [2,5] - swapping [3,5]
         * After - [1, 2, 3, 4, 5, 7, 6, 8, 9, 10, 12, 19]
         * Iteration i=2 completed
         * Before - [1, 2, 3, 4, 5, 7, 6, 8, 9, 10, 12, 19]
         * Found [i,j] == [3,6] - swapping [4,6]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         * Iteration i=3 completed
         * Iteration i=4 completed
         * Final - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         */
        bubbleSort(input);
        System.out.println("Final - "+Arrays.toString(input));
    }
}
