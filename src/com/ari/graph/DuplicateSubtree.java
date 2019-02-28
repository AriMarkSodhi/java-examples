package com.ari.graph;

import java.util.HashMap;
import java.util.Objects;

public class DuplicateSubtree {

    HashMap<String, Integer> subTreeMap;
    Node root;

    private DuplicateSubtree(Node root) {
        this.subTreeMap = new HashMap<String, Integer>();
        this.root = root;
    }

    private String findAllDuplicates(Node node) {
        if (node == null)
            return "";

        String str = "(";
        str += findAllDuplicates(node.left);
        str += Integer.toString(node.data);
        str += findAllDuplicates(node.right);
        str += ")";

        if (subTreeMap.get(str) != null && subTreeMap.get(str) == 1)
            System.out.print("Dup:"+node.data + " ");

        if (subTreeMap.containsKey(str))
            subTreeMap.put(str, subTreeMap.get(str) + 1);
        else
            subTreeMap.put(str, 1);


        return str;
    }

    void findAllDuplicates() {
        findAllDuplicates(root);
        System.out.println("Subtrees="+subTreeMap);
    }

    // Driver code
    public static void main(String args[]) {
        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node5 = new Node(4);
        Node node6 = new Node(3);
        Node node7 = new Node(4);
        Node node8 = new Node(3);



        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);

        node3.setLeft(node7);
        node3.setRight(node8);

        DuplicateSubtree search = new DuplicateSubtree(root);
        search.findAllDuplicates();
    }

    static class Node {
        private int data;
        private Node left;
        private Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return data == node.data &&
                    Objects.equals(left, node.left) &&
                    Objects.equals(right, node.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, left, right);
        }
    }
}
