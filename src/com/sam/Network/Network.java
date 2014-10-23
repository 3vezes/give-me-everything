package com.sam.Network;

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

/**
 * Created by admin on 10/23/14.
 */
public class Network {
    public Network() {

    }

    public ArrayList<String> getImages(String url) {
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

    public void saveToFile (ArrayList<String> images, String destination, int x) {
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

    public void scrapeFromPages(String url, String destination, ArrayList<String> images) {
        for (int i = 650; i <= 679; i++) {
            String pageUrl = url + i;
            images = getImages(pageUrl);
            saveToFile(images, destination, i);
        }
    }
}
