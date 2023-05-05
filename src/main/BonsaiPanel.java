package main;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

public class BonsaiPanel extends JPanel implements Runnable { 

    public final int tileSize = 5; // 5 x 5 px tile
    public final int maxScreenCol = 100;
    public final int maxScreenRow = 120;
    public final int screenWidth = tileSize * maxScreenCol; // 500px
    public final int screenHeight = tileSize * maxScreenRow; // 600px


    long lastGenTime;
    long genDelay = 500;

    int FPS = 30;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    BonsaiGenerator bonsaiGen = new BonsaiGenerator(this);


    public BonsaiPanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta -=1;
            }

        }
    }

    public void update() {

        long currentTime = System.currentTimeMillis();

        if (keyH.enterKeyPressed == true && currentTime - lastGenTime > genDelay) {

            bonsaiGen.update();

            lastGenTime = currentTime;

        }


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;   
        
        bonsaiGen.draw(g2);
        
        g2.dispose();

    }

    
}
