package com.sam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Document doc;
        String url = "http://ghstkng.tumblr.com/";

        try {
            doc = Jsoup.connect(url).get();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

            for (Element image : images) {
                System.out.println("src: " + image.attr("src"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
