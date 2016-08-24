package com.feedzai.netsim.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Represents the path followed by a packet in the network.
 */
public class NetworkPath {

    /**
     * Time token to follow this path.
     * @return The time (ms)
     */

    private final ArrayList<Node> nodes;
    private final int time;

    public NetworkPath(ArrayList<Node> nodes, int time) {
        this.nodes = nodes;
        this.time = time;
    }
    public int getTime() {
        // TODO: Change this so that it corresponds to the time taken to follow the path
        return time;
    }

    @Override public String toString() {
        ArrayList<String>  path = new ArrayList<>();
        for(Node node : nodes) {
            path.add(node.getName());
        }
        Collections.reverse(path);
        return Arrays.toString(path.toArray());
    }
}
