package tech.huning.wall.e.util.pdf;

import tech.huning.wall.e.util.pdf.core.MagickPdfLibrary;
import tech.huning.wall.e.util.pdf.exception.PdfException;
import tech.huning.wall.e.util.pdf.model.PdfParam;
import tech.huning.wall.e.util.pdf.model.PdfResult;
import tech.huning.wall.e.util.pdf.specs.IPdfLibrary;
import tech.huning.wall.e.util.pdf.specs.IPdfProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfProcessor implements IPdfProcessor {

    private final Logger logger = LoggerFactory.getLogger(PdfProcessor.class);

    private Class<? extends IPdfLibrary> clazz = MagickPdfLibrary.class;

    private PdfProcessor(){}

    public static PdfProcessor getInstance() {
        return PdfProcessorHolder.INSTANCE;
    }

    private static class PdfProcessorHolder {
        private static final PdfProcessor INSTANCE = new PdfProcessor();
    }

    @Override
    public IPdfProcessor load(Class<? extends IPdfLibrary> clazz) {
        this.clazz = clazz;
        return this;
    }

    @Override
    public PdfResult getTotalPage(PdfParam param) {
        PdfResult pdfResult = new PdfResult();

        try {
            IPdfLibrary pdfLibrary = this.clazz.newInstance();
            pdfResult.setTotalPage(pdfLibrary.getTotalPage(param.getFilePath()));
            pdfResult.setSuccess(true);
        } catch (InstantiationException | IllegalAccessException | PdfException e) {
            pdfResult.setSuccess(false);
            logger.error(e.getMessage(), e);
        }

        return pdfResult;
    }

    @Override
    public PdfResult convertToImage(PdfParam param) {
        PdfResult pdfResult = new PdfResult();

        try {
            IPdfLibrary pdfLibrary = this.clazz.newInstance();
            pdfResult = pdfLibrary.convert(param);
            pdfResult.setSuccess(true);
        } catch (InstantiationException | IllegalAccessException | PdfException e) {
            pdfResult.setSuccess(false);
            logger.error(e.getMessage(), e);
        }

        return pdfResult;
    }

}
