package com.feedzai.netsim.engine;

/**
 * Created by Mayank on 8/23/16.
 */

/**
 * User defined exception class for Network
 */

public class NetworkException extends Exception {
    private final String message;

    public NetworkException(String message) {
        this.message = message;
    }

    public void printStackTracee() {
        System.out.println(this.message);
    }
}
