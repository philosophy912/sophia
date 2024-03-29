package com.philosophy.image.common;


import com.philosophy.image.api.ICompare;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @author lizhe
 */
public class Pixel implements ICompare {

    private String[][] getPx(BufferedImage bi) {
        int[] rgb = new int[3];
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        String[][] list = new String[width][height];
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = bi.getRGB(i, j);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                list[i][j] = rgb[0] + "," + rgb[1] + "," + rgb[2];
            }
        }
        return list;
    }

    private double pixel(BufferedImage image1, BufferedImage image2) {
        // 分析图片相似度 begin
        String[][] list1 = getPx(image1);
        String[][] list2 = getPx(image2);
        int similar = 0;
        int not = 0;
        int i = 0;
        for (String[] strings : list1) {
            if ((i + 1) == list1.length) {
                continue;
            }
            int j = 0;
            for (int m = 0; m < strings.length; m++) {
                String[] value1 = list1[i][j].split(",");
                String[] value2 = list2[i][j].split(",");
                int k = 0;
                for (int n = 0; n < value2.length; n++) {
                    if (Math.abs(Integer.parseInt(value1[k]) - Integer.parseInt(value2[k])) < 5) {
                        similar++;
                    } else {
                        not++;
                    }
                }
                j++;
            }
            i++;
        }
        // int[] result = new int[]{ similar, not };
        return (double) similar / (double) (not + similar);
    }

    @Override
    public double compare(BufferedImage image1, BufferedImage image2) {
        return pixel(image1, image2);
    }
}
