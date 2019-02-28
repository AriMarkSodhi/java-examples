package com.ari.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MinCostPath {

    static List<String> permStr = new ArrayList<>();
    /**
     * lowest cost path
     * <p>
     * 3
     * 0    1    2
     * 0    0 1000 5000
     * 1 5000    0 1000
     * 2 1000 5000    0
     * <p>
     * buildPaths    - cost
     * 01,12,20 - 3000
     * 02,21,10 - 15000
     * 10,02,21 - 15000
     * 12,20,01 - 3000
     * 20,01,12 - 3000
     * 21,10,02 - 15000
     */

    private int[][] rawData;
    private List<List<Integer>> paths = new ArrayList<>();
    private List<Integer> pathCosts = new ArrayList<>();

    public MinCostPath(int[][] costs) {
        rawData = costs;
        // list of cities
        List<Integer> cities = new ArrayList<Integer>();
        for (int i = 1; i <= rawData.length; i++) {
            cities.add(i - 1);
        }

        int[] citiesArray = cities.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        generatePermutations(citiesArray, 0, 0);
        System.out.println("num buildPaths " + permStr.size() + " -> " + permStr);

        // permutations of cities
        Permutations.of(cities).forEach(buildPaths());

        System.out.println("num buildPaths " + paths.size() + " -> " + paths);
    }

    private Consumer<Stream<Integer>> buildPaths() {
        return ps -> {
            List<Integer> path = new ArrayList<>();
            ps.forEach(p -> path.add(p));
            paths.add(path);
        };
    }

    public static void main(String args[]) {
        /**
         *      0    1    2    3
         * 0    0 1000 5000 1000
         * 1 5000    0 1000 2000
         * 2 1000 5000    0 7000
         * 3 5000 1000 2000    0
         */
        int costs[][] = {{0, 1000, 5000, 1000}, {5000, 0, 1000, 2000}, {1000, 5000, 0, 7000}, {5000, 1000, 2000, 0}};

        MinCostPath mcp = new MinCostPath(costs);
        int result = mcp.minCost();
        System.out.println(result);
    }

    static void generatePermutations(int[] a, int k, int depth) {

        System.out.println("genPerm-[k=" + k + "]-->" + Arrays.toString(a)+" depth="+depth);

        if (k == a.length) {
            StringBuilder str = new StringBuilder();
            str.append(" [");
            for (int i = 0; i < a.length; i++) {
                str.append(a[i] + "" + ((i < a.length - 1) ? "," : ""));
            }
            str.append("]");
            System.out.println("Adding path:" + str.toString()+" depth="+depth);
            permStr.add(str.toString());
        } else {
            System.out.println("iterate ["+k+".."+a.length+"] depth="+depth);
            for (int i = k; i < a.length; i++) {
                swap(a, k, i);
                generatePermutations(a, k + 1, depth+1);
                swap(a, k, i);
            }
        }
    }

    private static void swap(int[] a, int k, int i) {
        int temp = a[k];
        a[k] = a[i];
        a[i] = temp;
        System.out.println("swap    [k="+k+"]-->[" + a[k] +","+a[i]+ "]-->" + Arrays.toString(a));

    }

    public int minCost() {
        paths.forEach(path -> {
            int cost = 0;
            for (int i = 0; i < path.size(); i++) {
                int a = rawData[path.get(i)][path.get(i == (path.size() - 1) ? 0 : i + 1)];
                cost += a;
            }
            pathCosts.add(cost);
        });
//        System.out.println(pathCosts);

        return pathCosts.stream().min(Integer::compare).orElse(Integer.MAX_VALUE);
    }


    public static class Permutations {

        public static <T> Stream<Stream<T>> of(final List<T> items) {
            // get matric cell ids, get permutations of ids which gives set of buildPaths
            return IntStream.range(0, factorial(items.size())).mapToObj(i -> permutation(i, items).stream());
        }

        private static int factorial(final int num) {

            return IntStream.rangeClosed(2, num).reduce(1, (x, y) -> x * y);
        }

        private static <T> List<T> permutation(final int count, final LinkedList<T> input, final List<T> output) {
//            System.out.println("1--> in:"+input +" out:"+ output);
            if (input.isEmpty()) {
                return output;
            }

            final int factorial = factorial(input.size() - 1);
            //swap
            output.add(input.remove(count / factorial));
            //           System.out.println("2--> move input --> output:"+count+"/"+factorial+"="+count/factorial);

            //         System.out.println("3--> in:"+input +" out:"+ output);

            return permutation(count % factorial, input, output);
        }

        private static <T> List<T> permutation(final int count, final List<T> items) {
            return permutation(count, new LinkedList<>(items), new ArrayList<>());
        }

    }

}

