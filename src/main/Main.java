package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("java-bonsai");
        window.setResizable(false);

        BonsaiPanel bp = new BonsaiPanel();
        
        window.add(bp);
        window.pack();

        window.setLocationRelativeTo(null); // center
        window.setVisible(true);

        bp.startGameThread();

    }


}