package com.sam;

import com.sam.Network.Network;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.sam.Display.*;

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
        Display display = new Display();
        Network network = new Network();

        String url;
        if (args.length>=2) {
            url = args[0];
            ArrayList<String> urlsPulled = new ArrayList<String>();
            String dirString = args[args.length-1];

            File dir = new File(dirString);
            dir.mkdir();

            if (dir!=null) {
                urlsPulled = network.getImages(url);
                printToLineImageUrls(urlsPulled);
                network.saveToFile(urlsPulled, dirString, 0);
            }

        } else {
            // no args passed in
            display.usage();
        }
    }
}
