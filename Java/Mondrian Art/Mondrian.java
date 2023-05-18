// Jerry
// 01/25/2023
// CSE 123
// TA: GAURAV
// This class has two functions to draw two kinds of Mondrian.
// The class import the color class to fill the mondrian.
import java.util.*;
import java.awt.*;

public class Mondrian {

    private Random rand = new Random();

    // Takes a 2d array pixels represented the canvas
    // Draws a mondrian picture on the pixels with the color object
    public void paintBasicMondrian(Color[][] pixels) {
        int ox = 0;
        int oy = 0;
        divide(pixels, ox, oy, pixels.length, pixels[0].length);
    }

    // helper method to divide the canvas in to multi piece
    private void divide(Color[][] pixels, int ox, int oy, int x, int y) {
        if (x - ox < (pixels.length/4) && y - oy < (pixels[0].length/4)) {
            Color[] color = new Color[4];
            color[0] = Color.RED;
            color[1] = Color.YELLOW;
            color[2] = Color.CYAN;
            color[3] = Color.WHITE;
            Color toFill = color[rand.nextInt(4)];
            fill(pixels, toFill, ox, oy, x, y);
        } else if (x - ox >= pixels.length/4 && y - oy >= pixels[0].length/4) {
            int nx = rand.nextInt((x - 10) - (10 + ox) + 1) + (10 + ox);
            int ny = rand.nextInt((y - 10) - (10 + oy) + 1) + (10 + oy);
            divide(pixels, ox, oy, nx, ny);
            divide(pixels, ox, ny + 1, nx, y);
            divide(pixels, nx + 1, oy, x, ny);
            divide(pixels, nx + 1, ny + 1, x, y);
        } else if (x - ox >= pixels.length/4) {
            int nx = rand.nextInt((x - 10) - (10 + ox) + 1) + (10 + ox);
            divide(pixels, ox, oy, nx, y);
            divide(pixels, nx + 1, oy, x, y);
        } else if (y - oy >= pixels[0].length/4) {
            int ny = rand.nextInt((y - 10) - (10 + oy) + 1) + (10 + oy);
            divide(pixels, ox, oy, x, ny);
            divide(pixels, ox, ny + 1, x, y);
        }
    }

    // helper method to fill each piece
    private void fill(Color[][] pixels, Color toFill, int ox, int oy, int x, int y) {
        for (int i = ox; i < x; i++) {
            for (int j = oy; j < y; j++) {
                pixels[i][j] = toFill;
            }
        }
    }

    // Takes a 2d array pixels represented the canvas
    // Draws a mondrian picture on the pixels with the color object
    // This mondrian picture has a higher possibiliy of having the red
    // on the upper left and a higher possibility of blue on the lower right
    public void paintComplexMondrian(Color[][] pixels) {
        int ox = 0;
        int oy = 0;
        divideComplex(pixels, ox, oy, pixels.length, pixels[0].length);
    }

    // the helper method for the complex way of dividing the canvas
    private void divideComplex(Color[][] pixels, int ox, int oy, int x, int y) {
        if (x - ox < (pixels.length/4) && y - oy < (pixels[0].length/4)) {
            double line = -((double)pixels[0].length/pixels.length)*x + pixels[0].length;
            if (y < line) {
                Color[] color = new Color[8];
                color[0] = Color.RED;
                color[1] = Color.YELLOW;
                color[2] = Color.CYAN;
                color[3] = Color.WHITE;
                color[4] = Color.RED;
                color[5] = Color.RED;
                color[6] = Color.RED;
                color[7] = Color.RED;
                Color toFill = color[rand.nextInt(8)];
                fill(pixels, toFill, ox, oy, x, y);
            } else {
                Color[] color = new Color[8];
                color[0] = Color.RED;
                color[1] = Color.YELLOW;
                color[2] = Color.CYAN;
                color[3] = Color.WHITE;
                color[4] = Color.CYAN;
                color[5] = Color.CYAN;
                color[6] = Color.CYAN;
                color[7] = Color.CYAN;
                Color toFill = color[rand.nextInt(8)];
                fill(pixels, toFill, ox, oy, x, y);
            }
        } else if (x - ox >= pixels.length/4 && y - oy >= pixels[0].length/4) {
            int nx = rand.nextInt((x - 10) - (10 + ox) + 1) + (10 + ox);
            int ny = rand.nextInt((y - 10) - (10 + oy) + 1) + (10 + oy);
            divideComplex(pixels, ox, oy, nx, ny);
            divideComplex(pixels, ox, ny + 1, nx, y);
            divideComplex(pixels, nx + 1, oy, x, ny);
            divideComplex(pixels, nx + 1, ny + 1, x, y);
        } else if (x - ox >= pixels.length/4) {
            int nx = rand.nextInt((x - 10) - (10 + ox) + 1) + (10 + ox);
            divideComplex(pixels, ox, oy, nx, y);
            divideComplex(pixels, nx + 1, oy, x, y);
        } else if (y - oy >= pixels[0].length/4) {
            int ny = rand.nextInt((y - 10) - (10 + oy) + 1) + (10 + oy);
            divideComplex(pixels, ox, oy, x, ny);
            divideComplex(pixels, ox, ny + 1, x, y);
        }
    }
}
