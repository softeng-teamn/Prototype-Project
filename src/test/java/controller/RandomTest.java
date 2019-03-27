package controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.testfx.framework.junit.ApplicationTest;
import testclassifications.SlowTest;
import testclassifications.UiTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.DebugUtils.informedErrorMessage;



public class RandomTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        DBController.init();
        Parent mainNode = FXMLLoader.load(getClass().getResource("../home.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    @Category({UiTest.class, SlowTest.class})
    public void randomTest() {
        // Test is 30 seconds long
        long randomTestLength = 1_000_000_000L * 60; // Seconds

        long start = System.nanoTime();
        while (System.nanoTime() - start < randomTestLength) {
            List<Window> windows = this.listWindows();

            // There should only be one window
            //verifyThat(windows.size(), is(1), informedErrorMessage(this));

            Window window = windows.get(0);

            // Window information
            double width = window.widthProperty().doubleValue();
            double height = window.heightProperty().doubleValue();
            double x = window.getX();
            double y = window.getY();

            Random random = new Random();


            // Choose a random point within the window
            Point2D point = new Point2D((float) random.nextInt((int) width) + x, (float) random.nextInt((int) height) + y);

            // Try clicking there
            try {
                this.clickOn(point);
            } catch (Exception e) {
                // Force a failure to log the exception
                // The test will now fail
                verifyThat(true, is(false), informedErrorMessage(this));
            }
        }
    }
}