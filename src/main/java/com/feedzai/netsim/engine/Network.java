
package com.feedzai.netsim.engine;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents the overall computer network.
 */

public class Network {

    /**
     * instance variables
     */
    private final int defaultLatency;
    private final ConcurrentHashMap<String, ArrayList<Node>> networkMap;
    private final PriorityQueue<Node> nodeListInNetwork;

    /**
     * constructors
     */
    public Network(int defaultLatency) {
        this(defaultLatency, new ConcurrentHashMap<String, ArrayList<Node>>());
    }

    public Network(int defaultLatency, ConcurrentHashMap<String, ArrayList<Node>> networkMap) {
        this.defaultLatency = defaultLatency;
        this.networkMap = networkMap;
        this.nodeListInNetwork = new PriorityQueue(10, new NodeComparator());
    }

    /**
     * function to create a network with default latency
     */
    public static Network createWithLatency(int latency) {
        return new Network(latency);
    }

    /**
     * function to connect two routers with default latency
     */
    public synchronized void connect(String idA, String idB) throws NetworkException {
        if(idA == null || idB == null) {
            throw new NetworkException("You must input two routers to add the route to the network");
        }

        addNodeToNetwork(idA, idB, this.defaultLatency);
        addNodeToNetwork(idB, idA, this.defaultLatency);
        addNodesToPQueue(idA, idB);
    }

    /**
     * function to connect two routers with given latency
     */
    public synchronized void connect(String idA, String idB, int latency) throws NetworkException {
        if(idA == null || idB == null || latency <= 0) {
            throw new NetworkException("You must input two routers and a latency >= 1 to add the route to the network");
        }

        addNodeToNetwork(idA, idB, latency);
        addNodeToNetwork(idB, idA, latency);
        addNodesToPQueue(idA, idB);
    }

    /**
     * function to send packets from one node to another in the network
     */
    public synchronized NetworkPath sendPacket(String idA, String idB) {
        // TODO: Simulates sending a packet from node idA to node idB. Returns the route that the packet followed
        // You may (or may not) need to throw exceptions... change signature if needed
        initializeSourceNode(idA);
        ArrayList<Node> nodeList = new ArrayList<>();
        Iterator<Node> iterator = nodeListInNetwork.iterator();

        while(iterator.hasNext()) {
            Node currentNode = iterator.next();
            nodeList.add(currentNode);
            ArrayList<Node> nodes = networkMap.get(currentNode.toString());
            for(Node node : nodes) {
                relax(currentNode, node, node.getDistance());
            }
        }

        Node startNode = null;
        Node endNode = null;
        ArrayList<Node> path = new ArrayList<>();

        for(Node node : nodeListInNetwork) {
            if(node.getName().equals(idA)) {
                startNode = node;
            }
            if(node.getName().equals(idB)) {
                endNode = node;
            }
        }

        while(!(endNode.getPredecessor().equals(startNode.toString()))) {
            path.add(endNode);
            endNode = endNode.getPredecessor();
        }

        return new NetworkPath(path, 6);
    }

    /**
     * function to add a node to network
     */
    public synchronized void addNodeToNetwork(String idA, String idB, int latency) {
        ArrayList<Node> listOfNodes;
        if (networkMap.containsKey(idA)) {
            listOfNodes = networkMap.get(idA);
        } else {
            listOfNodes = new ArrayList<>();
        }
        listOfNodes.add(new Node(idB, latency));
        networkMap.put(idA , listOfNodes);
    }

    /**
     * function to initialize the source node distance to zero
     */
    public synchronized void initializeSourceNode(String idA) {
        for(Node node : nodeListInNetwork) {
            if(node.getName().equals(idA)) {
                node.setDistance(0);
            }
        }
    }

    /**
     * function to add nodes to priority queue
     */
    public synchronized void addNodesToPQueue(String idA, String idB) {
        nodeListInNetwork.add(new Node(idA, Integer.MAX_VALUE));
        nodeListInNetwork.add(new Node(idB, Integer.MAX_VALUE));
    }

    /**
     * function to adjust the distance of all nodes in network
     */
    public synchronized void relax(Node node1, Node node2, int weight) {
        if(node1.getDistance() > (node2.getDistance() + weight)) {
            node2.setDistance(node1.getDistance() + weight);
            node2.setPredecessor(node1);
        }
    }
}
