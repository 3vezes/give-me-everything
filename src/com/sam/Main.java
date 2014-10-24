package com.sam;

import com.sam.Display.Display;
import com.sam.FileManagement.FileManager;
import com.sam.Network.Network;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Main {

    final static boolean DEBUGGING = true;

    static void printToLineImageUrls(ArrayList<String> images) {
        for (String url : images) {
            System.out.println(url);
        }
    }

    // Usage: give-me-everything [options] url [url...] [download to location]
    public static void main(String[] args) {
        String destination = "~/Desktop/";
        String folderName = "give-me-everything/";
        FileManager fm;
        Display display = new Display();
        Network network = new Network();

        String url;
        if (args.length>=2) {
            url = args[0];
            ArrayList<String> urlsPulled = new ArrayList<String>();
            String dirString = args[args.length-1];

            File dir = new File(dirString);
            dir.mkdir();
            fm = new FileManager(dirString);

            if (dir!=null) {
                urlsPulled = network.getImageUrls(url);
                display.foundList(urlsPulled);

                for (String singleUrl : urlsPulled) {
                    BufferedImage imageTemp;
                    display.singleLink(singleUrl);

                    imageTemp = network.getImageFromUrl(singleUrl);
                    if (imageTemp != null) {
                        // got image save to file
                        fm.saveImage(imageTemp, singleUrl);
                    }
                }
            }

            System.out.println("Done :)");

        } else {
            // no args passed in
            display.usage();
        }
    }
}
