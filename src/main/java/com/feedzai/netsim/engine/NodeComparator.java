package com.feedzai.netsim.engine;
import java.util.Comparator;
/**
 * Created by Mayank on 8/23/16.
 */

/**
 * Comparator for the priority queue
 */
public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        if(o1.getDistance() == o2.getDistance()) {
            return 0;
        }
        return (o1.getDistance() > o2.getDistance()) ? 1 : -1;
    }
}


