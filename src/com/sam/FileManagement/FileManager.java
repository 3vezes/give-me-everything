package com.sam.FileManagement;

import com.sam.Display.Display;
import org.apache.commons.io.FilenameUtils;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileManager {

    File directory;
    Display mDisplay;
    ImageWriter mWriter;

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
            if (ext == "jpe?g" || ext == "JPE?G") {
                mWriter = ImageIO.getImageWritersByFormatName("jpeg").next();
                ImageWriteParam param = mWriter.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Needed see javadoc
                param.setCompressionQuality(1.0F); // Highest quality
                mWriter.write(image);
            } else {
                ImageIO.write(image, ext, imageFile);
            }
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
