package com.ari.algorithms.sorting;

import java.util.Arrays;

public class HeapSort {
    /**
     * -
     *
     * HEAP SORT
     * USES A HEAP TO HELP SORT
     * ELEMENTS IN ASCENDING OR
     * DESCENDING ORDER
     * FIRST CONVERTS THE UNSORTED
     * LIST OR ARRAY INTO A HEAP - THIS
     * CAN BE DONE IN PLACE
     * USE THE HEAP TO ACCESS THE
     * MAXIMUM ELEMENT AND PUT IT
     * IN THE RIGHT POSITION IN THE
     * ARRAY
     *
     */
    private static void swap(int[] listToSort, int i, int j) {
        int temp = listToSort[i];
        listToSort[i] = listToSort[j];
        listToSort[j] = temp;
    }

    private static int getParentIndex(int index, int endIndex) {
        if (index < 0 || index > endIndex) return -1;
        return (index - 1) / 2;
    }

    private static int getRightChildIndex(int index, int endIndex) {
        int rightChildIndex = 2 * index + 2;
        if (rightChildIndex > endIndex) return -1;
        return rightChildIndex;
    }

    private static int getLeftChildIndex(int index, int endIndex) {
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex > endIndex) return -1;
        return leftChildIndex;
    }

    private static void heapify(int[] listToSort, int endIndex) {
        int index = getParentIndex(endIndex, endIndex);
        while (index >= 0) {
            percolateDown(listToSort, index, endIndex);
            index--;
        }
    }

    private static void percolateDown(int[] listToSort, int index, int endIndex) {
        int leftChildIndex = getLeftChildIndex(index, endIndex);
        int rightChildIndex = getRightChildIndex(index, endIndex);

        if (leftChildIndex != -1 && listToSort[leftChildIndex] > listToSort[index]) {
            swap(listToSort, leftChildIndex, index);
            percolateDown(listToSort, leftChildIndex, index);
        }
        if (rightChildIndex != -1 && listToSort[rightChildIndex] > listToSort[index]) {
            swap(listToSort, rightChildIndex, index);
            percolateDown(listToSort, rightChildIndex, index);
        }
    }


    private static void heapSort(int[] listToSort) {
        heapify(listToSort, listToSort.length - 1);

        int endIndex = listToSort.length - 1;
        while (endIndex > 0) {
            swap(listToSort, 0, endIndex);
            endIndex--;
            percolateDown(listToSort, 0, endIndex);
        }
    }


    public static void main(String[] args) {
        int[] input = {3, 2, 7, 6, 8, 1, 9, 4, 5, 10, 19, 12};
        heapSort(input);
        System.out.println("Final - " + Arrays.toString(input));
    }
}
