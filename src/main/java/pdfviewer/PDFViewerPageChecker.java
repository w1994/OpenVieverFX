package pdfviewer;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.jpedal.examples.viewer.OpenViewerFX;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by wojci on 02.04.2016.
 */
public class PDFViewerPageChecker implements Runnable {

    private OpenViewerFX viewer;
    private Integer pageNumber;
    private PDFViewerPageService service;

    public PDFViewerPageChecker(OpenViewerFX viewer){
        this.viewer = viewer;
        this.service = new PDFViewerPageService(viewer);
        this.pageNumber = -1;
    }

    @Override
    public void run(){

        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.setOnRunning(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent t) {
                    System.out.println("done:" + t.getSource().getValue());
                }
            });

            if(service.getState().equals(PDFViewerPageService.State.READY)){
                service.start();
            }
        }
    }
}
