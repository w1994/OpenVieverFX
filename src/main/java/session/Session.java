package session;

import file.PdfFile;

import java.io.File;
import java.util.List;

/**
 * Created by wojci on 03.04.2016.
 */
public class Session {

    private File session;
    private List<PdfFile> files;

    public Session(){

    }

    public static Session SessionBuilder(File csvSession){
        //to do - retrive data from csv and return new session
        return new Session();
    }

}
