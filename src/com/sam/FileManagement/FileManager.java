package com.sam.FileManagement;

import com.sam.Display.Display;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 10/23/14.
 */
public class FileManager {
    File directory;
    Display mDisplay;

    public FileManager (String destination) {
        directory = new File(destination);
        mDisplay = new Display();
        try {
            System.out.println("Saving into directory " + directory.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImage(BufferedImage image, String url) {
        String ext = getExt(url);
        String imageLocation = getFileName(url);

        File imageFile = null;
        try {
            imageFile = new File(directory.getCanonicalPath() + "/" + imageLocation + "." + ext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mDisplay.savingInto(imageFile.getPath());
            ImageIO.write(image, ext, imageFile);
        } catch (IOException e) {
            System.err.println("ERROR::cannot write file " + imageFile.getPath());
            e.printStackTrace();
        }
    }

    public String getFileName(String url) {
        return FilenameUtils.getBaseName(url);
    }

    public String getExt(String url) {
        return FilenameUtils.getExtension(url);
    }

}
