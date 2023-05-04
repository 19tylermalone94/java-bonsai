package main;

import java.util.HashMap;
import java.util.Random;
import java .awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BonsaiGenerator {

    BonsaiPanel bp;
    BufferedImage bonsaiImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    int[][] numberArray = new int[100][100];
    HashMap<Integer, Integer> colorMap = new HashMap<Integer, Integer>();
    Random random = new Random();
    int colorVariance = 18;
    int backgroundColor, bucketColor, soilColor, trunkColor, leafColor;
    int trunkWidth;
    int maxBranchLen = 15;
    int boxHeight = 8;
    int soilHeight = 2;

    public BonsaiGenerator(BonsaiPanel bp) {

        this.bp = bp;
        update();

    }

    public int makeRandomColor(int avgR, int avgG, int avgB) {
        int a = 255; //alpha

        int shift = random.nextInt(colorVariance);
        int shiftDirection = random.nextInt(2);
        if (shiftDirection == 0) {
            shift *= -1;
        }
        int r = avgR + shift;

        shift = random.nextInt(colorVariance);
        shiftDirection = random.nextInt(2);
        if (shiftDirection == 0) {
            shift *= -1;
        }
        int g = avgG + shift;

        shift = random.nextInt(colorVariance);
        shiftDirection = random.nextInt(2);
        if (shiftDirection == 0) {
            shift *= -1;
        }
        int b = avgB + shift;
        
        return (a<<24) | (r<<16) | (g<<8) | b; // colored pixel
    }

    public void refreshColors() {
        backgroundColor = makeRandomColor(173, 220, 220); // light blue
        bucketColor = makeRandomColor(100, 100, 20);  // brownish color
        soilColor = makeRandomColor(40, 36, 22); // dark brown
        trunkColor = makeRandomColor(90, 73, 17); // brown
        leafColor = makeRandomColor(18, 45, 18); // green

        
        colorMap.put(0, backgroundColor);
        colorMap.put(1, bucketColor);
        colorMap.put(2, soilColor);
        colorMap.put(3, trunkColor);
        colorMap.put(4, leafColor);

    }

    public void generateBonsaiArray() {

        // draw background
        for (int i = 0; i < numberArray.length; i++) {
            for (int j = 0; j < numberArray[i].length; j++) {
                numberArray[i][j] = 0;
            }
        }

        // draw bucket
        for (int i = numberArray.length - boxHeight; i < numberArray.length; i++) {
            for (int j = numberArray.length/4; j < numberArray.length - numberArray.length/4; j++) {
                    numberArray[i][j] = 1;
            }
        }

        // draw soil
        for (int i = numberArray.length - boxHeight - soilHeight; i < numberArray.length - boxHeight; i++) {
            for (int j = numberArray.length/4 + soilHeight; j < numberArray.length - numberArray.length/4 - soilHeight; j++) {
                numberArray[i][j] = 2;
            }
        }

        int direction = random.nextInt(2 + 1) - 1; // left or right growing

        generateTrunk(numberArray.length/2 - trunkWidth/2, numberArray.length - boxHeight - soilHeight, trunkWidth, direction, maxBranchLen);

    }

    public void generateTrunk(int col, int row, int width, int direction, int maxDist) {

        // escape case
        if (width <= 0) {
            generateLeaves(col, row);
            return;
        }

        int shifter = 1;
        int randDist = random.nextInt(maxDist); // distance to grow until next split
        for (int i = row; i > row - randDist; i--) {
            for (int j = col; j < col + width; j++) {
                numberArray[i][j + shifter] = 3;
            }
            // 6/10 chance to shift next row sideways
            int chooser = random.nextInt(10);
            if (chooser < 6) {
                shifter += direction;
            }
        }

        // split left
        generateTrunk(col + shifter, row-randDist, width-1, -1, maxDist - 1);
        // split right
        generateTrunk(col + shifter, row-randDist, width-1, 1, maxDist - 1);

    }

    public void generateLeaves(int col, int row) {
        // draws a randomly sized rhombus centered on the end of a branch

        // rhomus top half
        int radius = random.nextInt(5) + 1;
        int width = 1;
        for (int i = row - radius; i <row; i++) {
            for (int j = col - width; j < col + width; j++) {
                if (numberArray[i][j] != 3) {
                    numberArray[i][j] = 4;
                }
            }
            width += 1;
        }

        // rhombus bottom half
        width = 1;
        for (int i = row + radius; i >= row; i--) {
            for (int j = col - width; j < col + width; j++) {
                if (numberArray[i][j] != 3) {
                    numberArray[i][j] = 4;
                }
            }
            width += 1;
        }

    }

    public void makeImg() {

        // each pixel is colored according to the array numbers and with a slight variance from the mapped color
        for (int i = 0; i < numberArray.length; i++) {
            for (int j = 0; j < numberArray[i].length; j++) {
                refreshColors();
                bonsaiImg.setRGB(j, i, colorMap.get(numberArray[i][j]));
            }
        }
    }

    public void update() {

        trunkWidth = random.nextInt(6 - 4) + 4;

        generateBonsaiArray();

        makeImg();

    }

    public void draw(Graphics2D g2) {

        g2.drawImage(bonsaiImg, 0, 0, bp.screenWidth, bp.screenHeight, null);

    }
    
}
