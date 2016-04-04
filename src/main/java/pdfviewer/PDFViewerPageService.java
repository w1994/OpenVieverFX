package pdfviewer;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.jpedal.examples.viewer.OpenViewerFX;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by wojci on 02.04.2016.
 */
public class PDFViewerPageService extends Service<Integer> {

    private OpenViewerFX viewer;
    private Integer pageNumber;

    public PDFViewerPageService(OpenViewerFX viewer){
        this.viewer = viewer;
        pageNumber = -1;
    }

    @Override
    protected Task<Integer> createTask() {

        return new Task<Integer>() {

            @Override
            protected Integer call(){
                if(viewer != null && viewer.getPdfDecoder() != null){
                    pageNumber = new Integer(viewer.getPdfDecoder().getPageNumber());
                }
                return pageNumber;
            }
        };

    }
}
