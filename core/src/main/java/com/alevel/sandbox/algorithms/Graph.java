package com.alevel.sandbox.algorithms;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Graph {

    private final Node root;

    public Graph(Node root) {
        this.root = root;
    }

    public boolean findDFS(int value) {
        return dfs(root, value, new HashSet<>());
    }

    public boolean findBFS(int value) {
        if (root.value == value) {
            return true;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> nodes = new ArrayDeque<>();

        nodes.add(root);
        visited.add(root);

        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            if (node.value == value) {
                return true;
            }
            for (Node connection : node.connections) {
                if (visited.add(connection)) {
                    nodes.add(connection);
                }
            }
        }
        return false;
    }

    private static boolean dfs(Node node, int value, Set<Node> visited) {
        if (!visited.add(node)) {
            return false;
        }
        if (node.value == value) {
            return true;
        }
        for (Node connection : node.connections) {
            if (dfs(connection, value, visited)) {
                return true;
            }
        }
        return false;
    }

    public static final class Node {

        private final int value;

        private final Set<Node> connections = new HashSet<>();

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Set<Node> getConnections() {
            return connections;
        }

        public boolean connect(Node other) {
            if (other == null || this.equals(other)) {
                return false;
            }
            boolean added = connections.add(other);
            other.connections.add(this);
            return added;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(value);
        }
    }




}
