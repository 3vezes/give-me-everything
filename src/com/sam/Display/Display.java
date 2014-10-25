package com.sam.Display;

import java.util.ArrayList;

public class Display {
    
    public Display() {}

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

    public void singleLink(String url) {
        System.out.println("Downloading from " + url);
        System.out.println("...");
    }

    public void savingInto(String directory) {
        System.out.println("Saving into " + directory);
    }

    public void foundList(ArrayList<String> imageUrls) {
        System.out.println("Found " + imageUrls.size() + " links");
    }
}
