package com.sam;

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

public class Main {

    static ArrayList<String> getImages(String url) {
        ArrayList<String> listImage = new ArrayList<String>();
        Document doc;

        try {
            doc = Jsoup.connect(url).get();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

            for (Element image : images) {
                listImage.add(image.attr("src"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listImage;
    }

    static void printImageUrls(ArrayList<String> images) {
        for (String url : images) {
            System.out.println(url);
        }
    }

    public static void main(String[] args) {
        String destination = "/Users/admin/Desktop";
        String url = "http://ghstkng.tumblr.com";
        ArrayList<String> images = new ArrayList<String>();
        images = getImages(url);

        BufferedImage image = null;
        int i = 0;
        for (String imageUrl : images) {
            try {
                URL imageUrlUrl = new URL(imageUrl);
                image = ImageIO.read(imageUrlUrl);

                String fileLocation = destination + "/image" + i + ".jpg";
                ImageIO.write(image, "jpg", new File(fileLocation));

                i++;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printImageUrls(images);
    }
}
