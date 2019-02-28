package com.ari.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    /**
     * - O(nlog n)
     * <p>
     * MergeSort follows THE DIVIDE
     * AND CONQUER APPROACH
     * TO CREATE SMALLER
     * SUB-PROBLEMS
     * A LIST IS BROKEN DOWN INTO
     * SMALLER AND SMALLER
     * PARTS RECURSIVELY
     * AT SOME POINT THERE WILL
     * BE A LIST OF LENGTH ONE
     * THEN MERGE THE SORTED LISTS
     * TOGETHER TO GET THE FULLY
     * SORTED LIST
     * WE CAN CONSIDER THAT A
     * SORTED LIST
     */
    public static void split(int[] listToSort, int[] listFirstHalf, int[] listSecondHalf) {
        int index = 0;
        int secondHalfStartIndex = listFirstHalf.length;
        for (int elements : listToSort) {
            if (index < secondHalfStartIndex) {
                listFirstHalf[index] = listToSort[index];
            } else {
                listSecondHalf[index - secondHalfStartIndex] = listToSort[index];
            }
            index++;
        }
    }

    public static void mergeSort(int[] listToSort) {
        if (listToSort.length == 1) return;

        int midIndex = listToSort.length/2 + listToSort.length % 2;
        int[] listFirstHalf = new int[midIndex];
        int[] listSecondHalf = new int[listToSort.length - midIndex];
        split(listToSort, listFirstHalf, listSecondHalf);

        System.out.println("mergeSort called on "+Arrays.toString(listToSort));

        mergeSort(listFirstHalf);
        mergeSort(listSecondHalf);

        merge(listToSort, listFirstHalf, listSecondHalf);

    }

    public static void merge(int[] listToSort, int[] listFirstHalf, int[] listSecondHalf) {
        int mergeIndex = 0;
        int firstHalfIndex = 0;
        int secondHalfIndex = 0;

        System.out.println("merging "+Arrays.toString(listToSort)+" "+ Arrays.toString(listFirstHalf)+""+Arrays.toString(listSecondHalf));

        while (firstHalfIndex < listFirstHalf.length && secondHalfIndex < listSecondHalf.length) {
            if (listFirstHalf[firstHalfIndex] < listSecondHalf[secondHalfIndex]) {
                listToSort[mergeIndex] = listFirstHalf[firstHalfIndex];
                firstHalfIndex++;
            } else if (secondHalfIndex < listSecondHalf.length) {
                listToSort[mergeIndex] = listSecondHalf[secondHalfIndex];
                secondHalfIndex++;
            }
            mergeIndex++;
        }
        if (firstHalfIndex < listFirstHalf.length)
        {
            while(mergeIndex < listToSort.length)
            {
                listToSort[mergeIndex++] = listFirstHalf[firstHalfIndex++];
            }
        }
        if (secondHalfIndex < listSecondHalf.length)
        {
            while (mergeIndex < listToSort.length)
            {
                listToSort[mergeIndex++] = listSecondHalf[secondHalfIndex++];
            }
        }
    }


    public static void main(String[] args) {
        int[] input = {3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12};
        /**
         * mergeSort called on [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12]
         * mergeSort called on [3, 2, 7, 6, 8, 1]
         * mergeSort called on [3, 2, 7]
         * mergeSort called on [3, 2]
         * merging [3, 2] [3][2]
         * merging [3, 2, 7] [2, 3][7]
         * mergeSort called on [6, 8, 1]
         * mergeSort called on [6, 8]
         * merging [6, 8] [6][8]
         * merging [6, 8, 1] [6, 8][1]
         * merging [3, 2, 7, 6, 8, 1] [2, 3, 7][1, 6, 8]
         * mergeSort called on [9, 4, 5, 10, 19, 12]
         * mergeSort called on [9, 4, 5]
         * mergeSort called on [9, 4]
         * merging [9, 4] [9][4]
         * merging [9, 4, 5] [4, 9][5]
         * mergeSort called on [10, 19, 12]
         * mergeSort called on [10, 19]
         * merging [10, 19] [10][19]
         * merging [10, 19, 12] [10, 19][12]
         * merging [9, 4, 5, 10, 19, 12] [4, 5, 9][10, 12, 19]
         * merging [3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12] [1, 2, 3, 6, 7, 8][4, 5, 9, 10, 12, 19]
         * Final - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 19]
         */
        mergeSort(input);
        System.out.println("Final - " + Arrays.toString(input));
    }
}
