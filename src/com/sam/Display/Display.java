package com.sam.Display;

/**
 * Created by admin on 10/23/14.
 */
public class Display {
    public Display() {

    }

    public void usage() {
        System.out.println("Usage: give-me-everything " +
                "[options] " +
                "url " +
                "[url...] " +
                "location" +
                "\n");
        System.out.println("Error: " +
                "Missing 2 arguments");
    }
}
