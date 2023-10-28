package tech.huning.wall.e.util.pdf.core;

import tech.huning.wall.e.util.pdf.exception.PdfException;
import tech.huning.wall.e.util.pdf.model.PdfParam;
import tech.huning.wall.e.util.pdf.model.PdfResult;
import tech.huning.wall.e.util.pdf.specs.IPdfLibrary;

/**
 * SpirePdf处理库
 * https://www.e-iceblue.com/Download/pdf-for-java-free.html
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class SpirePdfLibrary implements IPdfLibrary {
    @Override
    public PdfResult convert(PdfParam param) throws PdfException {
        return null;
    }

    @Override
    public void cleanFileDir(PdfParam param) throws PdfException {

    }

    @Override
    public String pkgImageFilePath(PdfParam param, int pageNo) throws PdfException {
        return null;
    }

    @Override
    public long getTotalPage(String filePath) throws PdfException {
        return 0;
    }

}
