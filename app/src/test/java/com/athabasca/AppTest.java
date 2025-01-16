package com.athabasca;

import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import org.junit.Test;

public class AppTest {
    @Test public void runApp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            try {
                AddClient addClient = new AddClient();
                addClient.setVisible(true); // Make sure the AddClient window is visible
                latch.countDown(); // Count down the latch to indicate the UI is ready  
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Wait for the latch to be counted down
        latch.await();

        // Add a delay to ensure the ClientList window is displayed
        Thread.sleep(50000); // Sleep for 5 seconds to keep the window open for observation
    }
}