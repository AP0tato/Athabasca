package com.athabasca;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App 
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // TestFrame tf = new TestFrame();
            JFrame frame = new TestFrame();
            frame.setVisible(true);
        });
    }
}
