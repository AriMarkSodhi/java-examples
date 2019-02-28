package com.ari.algorithms.sorting;

import java.util.Arrays;

public class SelectionSort {
    /**
     *     - O(n2)
     *
     * AT EACH ITERATION 1 ELEMENT
     * IS SELECTED AND COMPARED
     * WITH EVERY OTHER ELEMENT
     * IN THE LIST TO FIND THE
     * SMALLEST ONE
     * FIRST WE FIND THE SMALLEST
     * ELEMENT, GET IT INTO THE
     * FIRST POSITION, NEXT WE FIND
     * THE SECOND SMALLEST TILL
     * THE ENTIRE LIST IS SORTED
     */
    public static void swap(int[] listToSort, int i, int j) {
        int temp = listToSort[i];
        listToSort[i] = listToSort[j];
        listToSort[j] = temp;
    }

    public static void selectionSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length; i++) {
            for (int j = i + 1; j < listToSort.length; j++) {
                if (listToSort[i] > listToSort[j]) {
                    System.out.println("Before - " + Arrays.toString(listToSort));
                    System.out.println("Found [i,j] == [" + i + "," + j + "] - swapping [" + listToSort[i] + "," + listToSort[j] + "]");
                    swap(listToSort, i, j);
                    System.out.println("After - " + Arrays.toString(listToSort));
                }
            }
            System.out.println("Iteration i=" + i + " completed");

        }

    }


    public static void main(String[] args) {
        int[] input = {3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12};
        /**
         * Before - [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Found [i,j] == [0,1] - swapping [3,2]
         * After - [2, 3, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Before - [2, 3, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Found [i,j] == [0,5] - swapping [2,1]
         * After - [1, 3, 7, 6, 8, 2, 9, 4, 5, 10, 19, 12]
         * Iteration i=0 completed
         * Before - [1, 3, 7, 6, 8, 2, 9, 4, 5, 10, 19, 12]
         * Found [i,j] == [1,5] - swapping [3,2]
         * After - [1, 2, 7, 6, 8, 3, 9, 4, 5, 10, 19, 12]
         * Iteration i=1 completed
         * Before - [1, 2, 7, 6, 8, 3, 9, 4, 5, 10, 19, 12]
         * Found [i,j] == [2,3] - swapping [7,6]
         * After - [1, 2, 6, 7, 8, 3, 9, 4, 5, 10, 19, 12]
         * Before - [1, 2, 6, 7, 8, 3, 9, 4, 5, 10, 19, 12]
         * Found [i,j] == [2,5] - swapping [6,3]
         * After - [1, 2, 3, 7, 8, 6, 9, 4, 5, 10, 19, 12]
         * Iteration i=2 completed
         * Before - [1, 2, 3, 7, 8, 6, 9, 4, 5, 10, 19, 12]
         * Found [i,j] == [3,5] - swapping [7,6]
         * After - [1, 2, 3, 6, 8, 7, 9, 4, 5, 10, 19, 12]
         * Before - [1, 2, 3, 6, 8, 7, 9, 4, 5, 10, 19, 12]
         * Found [i,j] == [3,7] - swapping [6,4]
         * After - [1, 2, 3, 4, 8, 7, 9, 6, 5, 10, 19, 12]
         * Iteration i=3 completed
         * Before - [1, 2, 3, 4, 8, 7, 9, 6, 5, 10, 19, 12]
         * Found [i,j] == [4,5] - swapping [8,7]
         * After - [1, 2, 3, 4, 7, 8, 9, 6, 5, 10, 19, 12]
         * Before - [1, 2, 3, 4, 7, 8, 9, 6, 5, 10, 19, 12]
         * Found [i,j] == [4,7] - swapping [7,6]
         * After - [1, 2, 3, 4, 6, 8, 9, 7, 5, 10, 19, 12]
         * Before - [1, 2, 3, 4, 6, 8, 9, 7, 5, 10, 19, 12]
         * Found [i,j] == [4,8] - swapping [6,5]
         * After - [1, 2, 3, 4, 5, 8, 9, 7, 6, 10, 19, 12]
         * Iteration i=4 completed
         * Before - [1, 2, 3, 4, 5, 8, 9, 7, 6, 10, 19, 12]
         * Found [i,j] == [5,7] - swapping [8,7]
         * After - [1, 2, 3, 4, 5, 7, 9, 8, 6, 10, 19, 12]
         * Before - [1, 2, 3, 4, 5, 7, 9, 8, 6, 10, 19, 12]
         * Found [i,j] == [5,8] - swapping [7,6]
         * After - [1, 2, 3, 4, 5, 6, 9, 8, 7, 10, 19, 12]
         * Iteration i=5 completed
         * Before - [1, 2, 3, 4, 5, 6, 9, 8, 7, 10, 19, 12]
         * Found [i,j] == [6,7] - swapping [9,8]
         * After - [1, 2, 3, 4, 5, 6, 8, 9, 7, 10, 19, 12]
         * Before - [1, 2, 3, 4, 5, 6, 8, 9, 7, 10, 19, 12]
         * Found [i,j] == [6,8] - swapping [8,7]
         * After - [1, 2, 3, 4, 5, 6, 7, 9, 8, 10, 19, 12]
         * Iteration i=6 completed
         * Before - [1, 2, 3, 4, 5, 6, 7, 9, 8, 10, 19, 12]
         * Found [i,j] == [7,8] - swapping [9,8]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 12]
         * Iteration i=7 completed
         * Iteration i=8 completed
         * Iteration i=9 completed
         * Before - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 12]
         * Found [i,j] == [10,11] - swapping [19,12]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         * Iteration i=10 completed
         * Iteration i=11 completed
         * Final - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         */

        selectionSort(input);
        System.out.println("Final - " + Arrays.toString(input));
    }
}
