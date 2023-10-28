package tech.huning.wall.e.util.pdf.specs;

import tech.huning.wall.e.util.FileUtil;
import tech.huning.wall.e.util.pdf.common.PdfConstant;
import tech.huning.wall.e.util.pdf.exception.PdfException;
import tech.huning.wall.e.util.pdf.model.PdfParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractPdfLibrary implements IPdfLibrary {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void cleanFileDir(PdfParam param) throws PdfException {
        try {
            Path path = Paths.get(param.getImagePath());
            logger.debug("delete file:{}", path.toString());
            FileUtil.deleteFiles(new File(param.getImagePath()));
            logger.debug("create file:{} ", path.toString());
            Files.createDirectories(path);
        } catch (Exception e) {
            throw new PdfException(e);
        }
    }

    @Override
    public String pkgImageFilePath(PdfParam param, int pageNo) throws PdfException {
        StringBuilder imageFile = new StringBuilder();
        imageFile.append(param.getImagePath());
        imageFile.append(File.separator);
        imageFile.append(param.getImageName());
        imageFile.append(param.getImageNameDelimiter());
        imageFile.append(param.getImageStartPageNo() + pageNo);
        imageFile.append(PdfConstant.FULL_STOP);
        imageFile.append(param.getImageFormat());
        return imageFile.toString();
    }
}
