package pdfviewer;

import javafx.stage.Stage;
import org.jpedal.FileAccess;
import org.jpedal.examples.viewer.OpenViewerFX;
import org.jpedal.io.types.Stream;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wojci on 02.04.2016.
 */
public class PDFViewer implements Runnable, Observer {

    private OpenViewerFX viewer;
    private Stage primaryStage;
    private String file;

    public PDFViewer(String file) {
        this.primaryStage = new Stage();
        this.file = file;
    }

    @Override
    public void run() {
        viewer = new OpenViewerFX(primaryStage, null);
        viewer.setupViewer();
        viewer.openDefaultFile(file);
        observe(viewer.getPdfDecoder().getFileAccess());
    }

    public void observe(Observable o) {
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        int pageNumber = ((FileAccess) o).getPageNumber();
        System.out.println("All is flux!  Some variable is now " + pageNumber);
    }
}
