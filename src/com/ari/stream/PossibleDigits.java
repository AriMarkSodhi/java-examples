package com.ari.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class PossibleDigits {

    /**
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 0
     * <p>
     * starting number and number of digits
     * <p>
     * possible navigation
     * <p>
     * From To
     * 0 {4, 6}
     * 1 {6, 8}
     * 2 {7, 9}
     * 3 {4, 8}
     * 4 { 3, 9, 0}
     * 5 {}
     * 6 {1, 7, 0}
     * 7 {2, 6}
     * 8 {1, 3}
     * 9 {2, 4}
     */

    private static HashMap<Integer, ArrayList<Integer>> possiblePaths;

    static {
        possiblePaths = new HashMap<>();
        possiblePaths.put(0, new ArrayList<>(Arrays.asList(4, 6)));
        possiblePaths.put(1, new ArrayList<>(Arrays.asList(6, 8)));
        possiblePaths.put(2, new ArrayList<>(Arrays.asList(7, 9)));
        possiblePaths.put(3, new ArrayList<>(Arrays.asList(4, 8)));
        possiblePaths.put(4, new ArrayList<>(Arrays.asList(3, 9, 0)));
        possiblePaths.put(5, new ArrayList<>(Arrays.asList()));
        possiblePaths.put(6, new ArrayList<>(Arrays.asList(1, 7, 0)));
        possiblePaths.put(7, new ArrayList<>(Arrays.asList(2, 6)));
        possiblePaths.put(8, new ArrayList<>(Arrays.asList(1, 3)));
        possiblePaths.put(9, new ArrayList<>(Arrays.asList(2, 4)));
    }

    private Stream<Product> prods;
    private Function filter;


    private static int numPaths(int startingNum, int numPathElements) {
        System.out.println("calling numPaths for digit " + startingNum + " numPathElements to check:" + numPathElements);

        if (numPathElements == 0) return 1;
        int pathCount = 0;

        ArrayList<Integer> paths = possiblePaths.get(startingNum);
        for (Integer path : paths) pathCount += numPaths(path, numPathElements - 1);
        return pathCount;

    }


    public static void main(String[] args) {
        // write your code here

        int numPossiblePaths = numPaths(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("NumPaths " + numPossiblePaths);

        List<String> myList = new ArrayList<>();
        myList.add("foo1");
        myList.add("foo2");

        myList.forEach(
                (name) -> {
                    System.out.println(name);
                }
        );

        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1, "prod1", 12.50));
        products.add(new Product(2, "prod2", 2.50));
        products.add(new Product(3, "prod3", 22.50));
        products.add(new Product(4, "prod4", 112.50));
        products.add(new Product(5, "prod5", 212.50));

        Collections.sort(products, Comparator.comparing(p -> p.getPrice()));

        products.forEach(
                (prod) -> {
                    System.out.println(prod);
                }
        );


        Stream<Product> filter = products.stream().filter(product -> product.getPrice() > 100.00)
                .filter(product -> product.getPrice() <= 200.00);
        List<String> names = filter.map(product -> product.getName()).collect(Collectors.toList());


        names.forEach(
                (prod) -> {
                    System.out.println(prod);
                }
        );

        Function<Product, Product> fn = prod ->
        {
            if (prod.getPrice() >= 200) return null;
            return prod;
        };


        List<Product> prodlist = myBigFilter(products.stream(), fn);
        prodlist.forEach((prod) -> {
                    System.out.println(prod);
                }
        );


        String palindrome = "A Santa at Nasa";

        if (isPalindrome(palindrome)) {
            System.out.println("Yes " + palindrome + " is a palindrome");
        } else {
            System.out.println("No " + palindrome + " is not a palindrome");
        }

        List<Test> tests = new ArrayList<Test>();

        int arr1[] = {10, 20, 60, 50, 30, 40};
        int k1 = 3;

        Test test1 = new Test(k1, arr1);

        int arr2[] = {10, 20, 60, 50, 30, 40};
        int k2 = 2;

        Test test2 = new Test(k2, arr1);

        tests.add(test1);
        tests.add(test2);

        tests.forEach(
                (test) -> {
                    System.out.println("Solving for=" + Arrays.toString(test.boardLengths) + " and numPainters=" + test.getNumPainters() + " result=" + distributeWork(test));
                });


    }


    static boolean isPalindrome(final String s) {
        String strL = s.replaceAll("[^a-zA-Z0-9]", "");

        if (strL.length() == 0) {
            return false;
        }
        String strR = new StringBuffer(strL).reverse().toString();
        if (strL.length() != strR.length()) {
            return false;
        }
        return strL.equalsIgnoreCase(strR);
    }


    static List<Product> myBigFilter(Stream<Product> prods, Function<Product, Product> filter) {

        List<Product> list = new ArrayList<Product>();

        prods.forEach(
                (prod) -> {
                    Product filteredProd = filter.apply(prod);
                    if (filteredProd != null) list.add(filteredProd);
                }
        );
        return list;
    }

    /**
     * Constraints:
     * 1<=T<=100
     * 1<=k<=30
     * 1<=n<=50
     * 1<=A[i]<=500
     * <p>
     * Example:
     * Input:
     * 2
     * 2 4
     * 10 10 10 10
     * 2 4
     * 10 20 30 40
     * Output:
     * 20
     * 60
     */
    static class Test {
        int numPainters;
        int numBoards;
        int[] boardLengths;

        public Test(int numPainters, int[] boardLengths) {
            this.numPainters = numPainters;
            this.numBoards = boardLengths.length;
            this.boardLengths = boardLengths;
        }

        public int getNumPainters() {
            return numPainters;
        }

        public int getNumBoards() {
            return numBoards;
        }

        public int[] getBoardLengths() {
            return boardLengths;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Test)) return false;
            Test test = (Test) o;
            return numPainters == test.numPainters &&
                    numBoards == test.numBoards &&
                    Arrays.equals(boardLengths, test.boardLengths);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(numPainters, numBoards);
            result = 31 * result + Arrays.hashCode(boardLengths);
            return result;
        }
    }


    /**
     * int arr1[] = { 10, 20, 60, 50, 30, 40 };
     * int k1 = 3;
     * ****************************************
     * {0,0} {1,1} {2,5}
     * {0,0} {1,2} {3,5}
     * {0,0} {1,3} {4,5}
     * {0,0} {1,4} {5,5}
     * {0,1} {2,2} {3,5}
     * {0,1} {2,3} {4,5}
     * {0,1} {2,4} {5,5}
     * {0,2} {3,3} {4,5}
     * {0,2} {3,4} {5,5}
     * {0,3} {4,4} {5,5}
     * ****************************************
     * {10} {20} {60,50,30,40} sum=180
     * {10} {20,60} {50,30,40} sum=120
     * {10} {20,60,50} {30,40} sum=130
     * {10} {20,60,50,30} {40} sum=160
     * {10,20} {60} {50,30,40} sum=120
     * {10,20} {60,50} {30,40} sum=110
     * {10,20} {60,50,30} {40} sum=140
     * {10,20,60} {50} {30,40} sum=90
     * {10,20,60} {50,30} {40} sum=90
     * {10,20,60,50} {30} {40} sum=140
     */

    static int sum(int arr[], int from, int to) {
        return IntStream.rangeClosed(from, to).map(i -> arr[i]).sum();
    }

    static int partitionWork(int arr[], int n, int k) {
        //System.out.println("Calling partitionWork " + Arrays.toString(arr) + "numBoards="+ n +" numPainters=" + k);

        int best = Integer.MAX_VALUE;

        if (n < k) return best;
        if (n == k) return Arrays.stream(arr).max().orElse(best);

        for (int i = 1; i <= n - k + 1; i++) {
            for (int j = i; j <= n - k + 1; j++) {

                int sumP1 = sum(arr, 0, i - 1);
                int sumP2 = sum(arr, i, j);
                int sumP3 = sum(arr, j + 1, n - 1);

                int max = Math.max(Math.max(sumP1, sumP2), sumP3);

                best = Math.min(best, max);
                //System.out.println("["+i+","+j+"] -- ["+0+","+(i-1)+"],["+(i)+","+(j)+"],["+(j+1)+","+(n-1)+"] max->"+max+" best->"+best);

            }
        }

        return best;
    }


    static int distributeWork(Test test) {
        int best = partitionWork(test.getBoardLengths(), test.getNumBoards(), test.numPainters);
        //System.out.println("best case="+ best);
        return best;
    }


}



