package tech.huning.wall.e.util.pdf.core;

import tech.huning.wall.e.util.pdf.exception.PdfException;
import tech.huning.wall.e.util.pdf.model.PdfParam;
import tech.huning.wall.e.util.pdf.model.PdfResult;
import tech.huning.wall.e.util.pdf.specs.AbstractPdfLibrary;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * icepdf处理库
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class ICEPdfLibrary extends AbstractPdfLibrary {

    @Override
    public PdfResult convert(PdfParam param) throws PdfException {

        PdfResult pdfResult = new PdfResult();
        logger.debug("convert start: {}", param.getFilePath());

        //清理文件
        cleanFileDir(param);

        Document document = new Document();
        try {
            document.setFile(param.getFilePath());
            //缩放比例
            float scale = 2.5f;
            //旋转角度
            float rotation = 0f;

            long totalPage = param.getTotalPage();
            if(totalPage <= 0) {
                totalPage = document.getNumberOfPages();
            }
            logger.debug("totalPage:{}", totalPage);

            List<String> imageFiles = new ArrayList<String>();
            for (int pageNo = 0; pageNo < totalPage; pageNo++) {
                BufferedImage bufferedImage = (BufferedImage)
                        document.getPageImage(pageNo, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, scale);
                try {
                    String imageFilePath = pkgImageFilePath(param, pageNo);
                    File file = new File(imageFilePath);
                    ImageIO.write(bufferedImage, param.getImageFormat(), file);
                    imageFiles.add(imageFilePath);
                } catch (IOException e) {
                    throw new PdfException(e);
                } finally {
                    if(null != bufferedImage) {
                        bufferedImage.flush();
                        bufferedImage.getGraphics().dispose();
                    }
                }
            }

            pdfResult.setImageFiles(imageFiles);
            pdfResult.setTotalPage(totalPage);
            pdfResult.setSuccess(true);
        } catch (PDFException e) {
            pdfResult.setSuccess(false);
            throw new PdfException(e);
        } catch (PDFSecurityException e) {
            pdfResult.setSuccess(false);
            throw new PdfException(e);
        } catch (IOException e) {
            pdfResult.setSuccess(false);
            throw new PdfException(e);
        } catch (PdfException e) {
            pdfResult.setSuccess(false);
            throw e;
        } finally {
            document.dispose();
        }

        return pdfResult;
    }

    @Override
    public long getTotalPage(String filePath) throws PdfException {
        Document document = new Document();
        long totalPage = -1;
        try {
            document.setFile(filePath);
            totalPage = document.getNumberOfPages();
        } catch (PDFException e) {
            throw new PdfException(e);
        } catch (PDFSecurityException e) {
            throw new PdfException(e);
        } catch (IOException e) {
            throw new PdfException(e);
        } finally {
            document.dispose();
        }
        return totalPage;
    }

}
