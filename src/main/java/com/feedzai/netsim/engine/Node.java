package com.feedzai.netsim.engine;

/**
 * Created by Mayank on 8/23/16.
 */

/**
 * Representation of a node in a network
 */

public class Node {

    private int distance;
    private final String name;
    private Node predecessor;

    public Node(String name, int distance) {
        this.distance = distance;
        this.name = name;
        this.predecessor = null;
    }

    public String getName() {
        return this.name;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public int getDistance() {
        return this.distance;
    }

    public Node getPredecessor() {
        return this.predecessor;
    }

    @Override public String toString() {
        return this.getName();
    }

    @Override public boolean equals(Object o1) {
        if(this == o1) {
            return true;
        }

        if((o1 instanceof Node) && ((Node) o1).getName().equals(this.getName())) {
            return true;
        }
        return false;
    }

    @Override public int hashCode() {
        return 17 * this.getName().hashCode();
    }
}
