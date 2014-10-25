package com.sam.Network;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Network {

    public Network() {}

    public ArrayList<String> getImageUrls(String url) {
        ArrayList<String> imageUrls = new ArrayList<String>();
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|JPE?G|gif)]");
            for (Element image : images) {
                String imageUrl = getUrlWithNoParameters(image.attr("src"));
                if (!imageUrls.contains(imageUrl)) {
                    imageUrls.add(imageUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageUrls;
    }

    public BufferedImage getImageFromUrl(String imageUrl) {
        BufferedImage image = null;
        try {
            URL imageUrlUrl = new URL(imageUrl);
            image = ImageIO.read(imageUrlUrl);
        } catch (MalformedURLException e) {
            System.err.println("ERROR::Bad url " + imageUrl);
        } catch (IOException e) {
            System.err.println("ERROR::Bad io " + imageUrl);
        }
        return image;
    }

    public String getUrlWithNoParameters (String url) {
        String result = null;
        try {
            URI uri = new URI(url);
            result = uri.getScheme();
            result += "://";
            result += uri.getAuthority();
            result += uri.getRawPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }
}
