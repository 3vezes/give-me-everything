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

    static void printToLineImageUrls(ArrayList<String> images) {
        for (String url : images) {
            System.out.println(url);
        }
    }

    static void saveToFile (ArrayList<String> images, String destination, int x) {
        BufferedImage image = null;
        int i = 0;
        for (String imageUrl : images) {
            try {
                if (imageUrl.contains("http://")) {
                    URL imageUrlUrl = new URL(imageUrl);
                    image = ImageIO.read(imageUrlUrl);
                    String fileLocation = destination + "/" + "page" + x + "image" + i +".jpg";
                    if (image != null && image.getHeight() > 200) {
                        ImageIO.write(image, "jpg", new File(fileLocation));
                        i++;
                    }
                }
            } catch (MalformedURLException e) {
                System.out.println("Bad url " + imageUrl);
            } catch (IOException e) {
                System.out.println("Bad io " + imageUrl);
            }
        }
    }

    static void scrapeFromPages(String url, String destination, ArrayList<String> images) {
        for (int i = 650; i <= 679; i++) {
            String pageUrl = url + i;
            images = getImages(pageUrl);
            printToLineImageUrls(images);
            saveToFile(images, destination, i);
        }
    }

    public static void main(String[] args) {
        String destination = "/Users/admin/Desktop/";
        String folderName = "hey/";
        String url = "http://ghstkng.tumblr.com";
        ArrayList<String> images = new ArrayList<String>();

        String dirString = destination + folderName;
        File dir = new File(dirString);
        dir.mkdir();

        if (dir!=null) {
            images = getImages(url);
            printToLineImageUrls(images);
            saveToFile(images, dirString, 0);
        }

    }
}
