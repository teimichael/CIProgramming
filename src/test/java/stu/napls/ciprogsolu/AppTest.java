package stu.napls.ciprogsolu;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class AppTest {

    @Test
    public void p2019sImageMaker() {
        try {
            BufferedImage image = ImageIO.read(new File(this.getClass().getClassLoader().getResource("p2019s/image/p2019s_image1_src.jpg").getPath()));
            Random random = new Random();
            for (int i = 0; i < image.getHeight(); i++) {
                image.setRGB(image.getWidth() - 1, i, Color.white.getRGB());
                if (i % 5 == 0) {
                    image.setRGB(random.nextInt(image.getWidth() - 5), random.nextInt(image.getHeight() - 1), Color.white.getRGB());
                }
            }
            image.setRGB(3, 0, Color.white.getRGB());
            ImageIO.write(image, "jpg", new File("p2019s_image1_dst.jpg"));
            StringBuilder stringBuilder = new StringBuilder();
            Color pixel;
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    pixel = new Color(image.getRGB(j, i));
                    stringBuilder.append(pixel.getRed());
                    stringBuilder.append(" ");
                    stringBuilder.append(pixel.getGreen());
                    stringBuilder.append(" ");
                    stringBuilder.append(pixel.getBlue());
                    stringBuilder.append(" ");
                }
            }
            FileWriter fileWriter = new FileWriter("p2019s_image1_dst");
            fileWriter.write(stringBuilder.toString().trim());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
