package com.ari.algorithms.sorting;

import java.util.Arrays;

public class InsertionSort {
    /**
     *     - O(n2)
     *
     * START WITH A SORTED
     * SUB-LIST OF SIZE 1
     * INSERT THE NEXT
     * ELEMENT INTO THE
     * SORTED SUB-LIST AT THE
     * RIGHT POSITION. NOW
     * THE SORTED SUB-LIST
     * HAS 2 ELEMENTS
     * THIS CONTINUES TILL THE
     * ENTIRE LIST IS SORTED
     */
    public static void swap(int[] listToSort, int i, int j) {
        int temp = listToSort[i];
        listToSort[i] = listToSort[j];
        listToSort[j] = temp;
    }

    public static void insertionSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length -1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (listToSort[j] < listToSort[j - 1]) {
                    System.out.println("Before - "+Arrays.toString(listToSort));
                    System.out.println("Found [l,h] == [" + j + "," + (j-1) + "] - swapping [" + listToSort[j] + "," + listToSort[j-1] + "]");
                    swap(listToSort, j, j - 1);
                    System.out.println("After - "+Arrays.toString(listToSort));
                } else {
                    break;
                }
            }
            System.out.println("Completed pass "+i);
        }
    }


    public static void main(String[] args) {
        int[] input = {3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12};
        /**
         * Before - [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [1,0] - swapping [2,3]
         * After - [2, 3, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Completed pass 0
         * Completed pass 1
         * Before - [2, 3, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [3,2] - swapping [6,7]
         * After - [2, 3, 6, 7, 8, 1, 9, 4, 5, 10, 19, 12]
         * Completed pass 2
         * Completed pass 3
         * Before - [2, 3, 6, 7, 8, 1, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [5,4] - swapping [1,8]
         * After - [2, 3, 6, 7, 1, 8, 9, 4, 5, 10, 19, 12]
         * Before - [2, 3, 6, 7, 1, 8, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [4,3] - swapping [1,7]
         * After - [2, 3, 6, 1, 7, 8, 9, 4, 5, 10, 19, 12]
         * Before - [2, 3, 6, 1, 7, 8, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [3,2] - swapping [1,6]
         * After - [2, 3, 1, 6, 7, 8, 9, 4, 5, 10, 19, 12]
         * Before - [2, 3, 1, 6, 7, 8, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [2,1] - swapping [1,3]
         * After - [2, 1, 3, 6, 7, 8, 9, 4, 5, 10, 19, 12]
         * Before - [2, 1, 3, 6, 7, 8, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [1,0] - swapping [1,2]
         * After - [1, 2, 3, 6, 7, 8, 9, 4, 5, 10, 19, 12]
         * Completed pass 4
         * Completed pass 5
         * Before - [1, 2, 3, 6, 7, 8, 9, 4, 5, 10, 19, 12]
         * Found [l,h] == [7,6] - swapping [4,9]
         * After - [1, 2, 3, 6, 7, 8, 4, 9, 5, 10, 19, 12]
         * Before - [1, 2, 3, 6, 7, 8, 4, 9, 5, 10, 19, 12]
         * Found [l,h] == [6,5] - swapping [4,8]
         * After - [1, 2, 3, 6, 7, 4, 8, 9, 5, 10, 19, 12]
         * Before - [1, 2, 3, 6, 7, 4, 8, 9, 5, 10, 19, 12]
         * Found [l,h] == [5,4] - swapping [4,7]
         * After - [1, 2, 3, 6, 4, 7, 8, 9, 5, 10, 19, 12]
         * Before - [1, 2, 3, 6, 4, 7, 8, 9, 5, 10, 19, 12]
         * Found [l,h] == [4,3] - swapping [4,6]
         * After - [1, 2, 3, 4, 6, 7, 8, 9, 5, 10, 19, 12]
         * Completed pass 6
         * Before - [1, 2, 3, 4, 6, 7, 8, 9, 5, 10, 19, 12]
         * Found [l,h] == [8,7] - swapping [5,9]
         * After - [1, 2, 3, 4, 6, 7, 8, 5, 9, 10, 19, 12]
         * Before - [1, 2, 3, 4, 6, 7, 8, 5, 9, 10, 19, 12]
         * Found [l,h] == [7,6] - swapping [5,8]
         * After - [1, 2, 3, 4, 6, 7, 5, 8, 9, 10, 19, 12]
         * Before - [1, 2, 3, 4, 6, 7, 5, 8, 9, 10, 19, 12]
         * Found [l,h] == [6,5] - swapping [5,7]
         * After - [1, 2, 3, 4, 6, 5, 7, 8, 9, 10, 19, 12]
         * Before - [1, 2, 3, 4, 6, 5, 7, 8, 9, 10, 19, 12]
         * Found [l,h] == [5,4] - swapping [5,6]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 12]
         * Completed pass 7
         * Completed pass 8
         * Completed pass 9
         * Before - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 12]
         * Found [l,h] == [11,10] - swapping [12,19]
         * After - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         * Completed pass 10
         * Final - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         */
        insertionSort(input);
        System.out.println("Final - " + Arrays.toString(input));
    }
}
