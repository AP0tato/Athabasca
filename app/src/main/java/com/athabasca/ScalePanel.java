package com.athabasca;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ScalePanel extends JPanel{
    private double scaleFactor = 1.0;

    ScalePanel(JComponent element, double scale){
        add(element);
        setScaleFactor(scale);
    }
    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
        revalidate(); // Recalculate layout
        repaint();    // Redraw components
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Apply scaling
        g2d.scale(scaleFactor, scaleFactor);
    }

}
