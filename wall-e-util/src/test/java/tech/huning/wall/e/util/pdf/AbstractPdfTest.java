package tech.huning.wall.e.util.pdf;

import tech.huning.wall.e.util.os.SystemUtil;

import java.io.File;
import java.net.URL;

public abstract class AbstractPdfTest {

    protected String getFilePath() {
        URL url = this.getClass().getClassLoader().getResource("aurora.pdf");
        assert url != null;
        return url.getFile();
    }

    protected String getImagePath() {
        File file = new File(SystemUtil.getUserHome() + File.separator + ".pdf");
        if(!file.exists()) {
            file.mkdirs();
        }
        return file.getPath();
    }

}
