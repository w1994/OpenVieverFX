package gui;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jpedal.examples.viewer.OpenViewerFX;
import pdfviewer.PDFViewer;

import pdfviewer.PDFViewerPageChecker;
import pdfviewer.PDFViewerPageService;

import java.io.File;
import java.io.IOException;

public class PDFSaverMain extends Application {

    private File file;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        final Button btn = new Button();
        btn.setText("File");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Stage stage = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
                file = fileChooser.showOpenDialog(stage);
                javafx.application.Platform.runLater(new PDFViewer(file.getName()));

            }
        });


        ProgressBar bar = new ProgressBar();

        final StackPane root = new StackPane();
        BorderPane border = new BorderPane();
        border.setTop(btn);
        border.setLeft(bar);
        root.getChildren().add(border);


        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add("/jfxdemos/styles.css");
        primaryStage.setTitle("TEST");
        primaryStage.setScene(scene);
        primaryStage.show();

        Task task = new Task<Void>() {
            @Override
            public Void call() {
                final int max = 100000000;
                for (int i = 1; i <= max; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    updateProgress(i, max);
                }
                return null;
            }
        };

        bar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

    }

    public static void main(String[] args) {

        launch(args);

    }

}