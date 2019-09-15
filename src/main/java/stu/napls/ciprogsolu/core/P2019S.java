package stu.napls.ciprogsolu.core;

import stu.napls.ciprogsolu.util.FileToolbox;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class P2019S {

    private String content;
    private String[] elements;
    private Image image;

    public P2019S() throws IOException {
        // Read file as an ArrayList
        ArrayList<String> contentList = FileToolbox.readFileAsList(this.getClass().getClassLoader().getResource("p2019s/file/p2019s_image1_dst").getPath());

        // Assume there is only one line in the file
        content = contentList.get(0);
        elements = content.split(" ");
        image = new Image();
        int index = 0;
        for (int i = 0; i < this.elements.length; i += 3) {
            image.pixels.add(new Pixel(elements[i], elements[i + 1], elements[i + 2], index));
            image.sortPixels.add(new Pixel(elements[i], elements[i + 1], elements[i + 2], index++));
        }
    }

    public void doQ1() {
        System.out.println("The number of the pixels is " + this.image.getPixelNumber() + "\n");
    }

    public void doQ2() {
        // This problem can be solved within one loop. For convenience, it's more readable to break them up.
        int width = 0;
        boolean isRealWidth;
        for (int i = 0; i < image.getPixelNumber(); i++) {
            if (image.pixels.get(i).isWhite && image.getPixelNumber() % (i + 1) == 0) {
                width = i + 1;
                isRealWidth = true;
                for (int j = i + width; j < image.getPixelNumber(); j = j + width) {
                    if (!image.pixels.get(j).isWhite) {
                        isRealWidth = false;
                        break;
                    }
                }
                if (isRealWidth) {
                    break;
                }
            }
        }
        System.out.println("The width of the image is " + width + "\n");
    }

    public void doQ3() {
        // Little tricky. Better to use some sort algorithm explicitly.
        sortByIntensity();
        Pixel pixel = image.sortPixels.get(image.getPixelNumber() / 2);
        System.out.println("The n/2-th triplet is " + pixel.toString());
        System.out.println("The index of this pixel is " + pixel.index + "\n");
    }

    private void sortByIntensity() {
        Collections.sort(image.sortPixels, new Comparator<Pixel>() {
            @Override
            public int compare(Pixel p1, Pixel p2) {
                return Integer.compare(p1.intensity, p2.intensity);
            }
        });
    }

    public void doQ4() {
        int k = 4;
        sortByIntensity();
        Pixel pixel;
        for (int i = 0; i < k; i++) {
            pixel = image.sortPixels.get(image.getPixelNumber() * i / k);
            System.out.println("The e" + i + " triplet is " + pixel.toString());
            System.out.println("The index of this pixel is " + pixel.index + "\n");
        }
    }

    public void doQ5() {
        int k = 128;
        int epoch = 10;
        ClusteringTool clusteringTool = new ClusteringTool(k, epoch);
        ArrayList<Pixel> pixels = clusteringTool.representingPixels;
        System.out.println("p40 is " + pixels.get(40));
        System.out.println("p80 is " + pixels.get(80));
        System.out.println("p120 is " + pixels.get(120));
    }

    class ClusteringTool {

        ArrayList<Pixel> representingPixels;
        ArrayList<ArrayList<Pixel>> representingClusters;

        ClusteringTool(int k, int epoch) {
            // Step 1
            sortByIntensity();
            ArrayList<Pixel> pixels = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                pixels.add(image.sortPixels.get(image.getPixelNumber() * i / k));
            }

            ArrayList<ArrayList<Pixel>> clusters = new ArrayList<>();
            for (int e = 0; e < epoch; e++) {
                // Step 2
                clusters = new ArrayList<>();
                for (int i = 0; i < k; i++) {
                    clusters.add(new ArrayList<Pixel>());
                }
                Pixel pixel;
                for (int i = 0; i < image.getPixelNumber(); i++) {
                    pixel = image.sortPixels.get(i);
                    clusters.get(getClusterId(pixels, pixel)).add(pixel);
                }
                pixels = new ArrayList<>();
                // Step 3
                for (int i = 0; i < k; i++) {
                    pixels.add(getClusterCentralPixel(clusters.get(i)));
                }
            }
            this.representingPixels = pixels;
            this.representingClusters = clusters;
        }
    }

    private Pixel getClusterCentralPixel(ArrayList<Pixel> cluster) {
        int clusterSize = cluster.size();
        int centralR = 0;
        int centralG = 0;
        int centralB = 0;
        for (Pixel pixel :
                cluster) {
            centralR += pixel.r;
            centralG += pixel.g;
            centralB += pixel.b;
        }
        centralR = (int) Math.floor(centralR / clusterSize);
        centralG = (int) Math.floor(centralG / clusterSize);
        centralB = (int) Math.floor(centralB / clusterSize);

        Pixel centralPixel = cluster.get(0);
        int distance = getDistance(cluster.get(0), centralR, centralG, centralB);
        int newDistance;
        for (int i = 1; i < cluster.size(); i++) {
            newDistance = getDistance(cluster.get(i), centralR, centralG, centralB);
            if (newDistance <= distance) {
                centralPixel = cluster.get(i);
                distance = newDistance;
            }
        }
        return centralPixel;
    }

    private int getClusterId(ArrayList<Pixel> pixels, Pixel pixel) {
        int clusterId = 0;
        int distance = getDistance(pixels.get(0), pixel);
        int newDistance;
        for (int i = 1; i < pixels.size(); i++) {
            newDistance = getDistance(pixels.get(i), pixel);
            if (newDistance <= distance) {
                clusterId = i;
                distance = newDistance;
            }
        }
        return clusterId;
    }

    private int getDistance(Pixel p1, Pixel p2) {
        return Math.abs(p1.r - p2.r) + Math.abs(p1.g - p2.g) + Math.abs(p1.b - p2.b);
    }

    private int getDistance(Pixel p, int r, int g, int b) {
        return Math.abs(p.r - r) + Math.abs(p.g - g) + Math.abs(p.b - b);
    }

    public void doQ6() throws IOException {
        Pixel[] newImage = new Pixel[image.getPixelNumber()];
        int k = 32;
        int epoch = 10;
        ClusteringTool clusteringTool = new ClusteringTool(k, epoch);
        ArrayList<Pixel> representingPixels = clusteringTool.representingPixels;
        ArrayList<ArrayList<Pixel>> clusters = clusteringTool.representingClusters;
        Pixel representingPixel;
        Pixel pixel;
        ArrayList<Pixel> cluster;
        for (int i = 0; i < clusters.size(); i++) {
            representingPixel = representingPixels.get(i);
            cluster = clusters.get(i);
            for (int j = 0; j < cluster.size(); j++) {
                pixel = cluster.get(j);
                newImage[pixel.index] = representingPixel;
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("image.tif");
        byte[] header = {77, 77, 0, 42, 0, 0, 0, 8, 0, 7, 1, 0, 0, 4, 0, 0, 0, 1, 0, 0, 0, 100, 1, 1, 0, 4, 0, 0, 0, 1, 0, 0, 0, -126, 1, 2, 0, 3, 0, 0, 0, 3, 0, 0, 0, 98, 1, 6, 0, 3, 0, 0, 0, 1, 0, 2, 0, 0, 1, 17, 0, 4, 0, 0, 0, 1, 0, 0, 0, 104, 1, 21, 0, 3, 0, 0, 0, 1, 0, 3, 0, 0, 1, 23, 0, 4, 0, 0, 0, 1, 0, 0, -104, 88, 0, 0, 0, 0, 0, 8, 0, 8, 0, 8};
        fileOutputStream.write(header);
        for (int i = 0; i < newImage.length; i++) {
            pixel = newImage[i];
            fileOutputStream.write((byte) pixel.r);
            fileOutputStream.write((byte) pixel.g);
            fileOutputStream.write((byte) pixel.b);
        }
        fileOutputStream.close();
    }

    class Image {
        ArrayList<Pixel> pixels = new ArrayList<>();
        ArrayList<Pixel> sortPixels = new ArrayList<>();

        private int getPixelNumber() {
            return this.pixels.size();
        }
    }

    class Pixel {
        int index;
        int r;
        int g;
        int b;
        int intensity;
        boolean isWhite;

        Pixel(String r, String g, String b, int index) {
            this.index = index;
            this.r = Integer.parseInt(r);
            this.g = Integer.parseInt(g);
            this.b = Integer.parseInt(b);
            this.isWhite = this.r == 255 && this.g == 255 && this.b == 255;
            this.intensity = this.r * this.r + this.g * this.g + this.b * this.b;
        }

        public String toString() {
            return "(" + this.r + " " + this.g + " " + this.b + ")";
        }
    }
}
