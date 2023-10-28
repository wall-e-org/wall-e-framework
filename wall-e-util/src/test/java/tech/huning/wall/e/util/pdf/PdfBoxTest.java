package tech.huning.wall.e.util.pdf;

import com.alibaba.fastjson.JSON;
import tech.huning.wall.e.util.pdf.common.PdfConstant;
import tech.huning.wall.e.util.pdf.core.PdfBoxLibrary;
import tech.huning.wall.e.util.pdf.model.PdfParam;
import tech.huning.wall.e.util.pdf.model.PdfResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * PdfBoxLibrary测试
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class PdfBoxTest  extends AbstractPdfTest {

    private static final Logger logger = LoggerFactory.getLogger(PdfBoxTest.class);

    @Test
    public void testGetTotalPage() {
        PdfParam pdfParam = new PdfParam.Builder()
                .setFilePath(getFilePath())
                .setImagePath(getImagePath())
                .setImageName(PdfConstant.EMPTY_STRING)
                .setImageFormat("jpg")
                .setImageNameDelimiter(PdfConstant.EMPTY_STRING)
                .setImageStartPageNo(100000)
                .build();

        PdfResult pdfResult = PdfProcessor.getInstance().load(PdfBoxLibrary.class).getTotalPage(pdfParam);
        logger.info(JSON.toJSONString(pdfResult));
    }

    @Test
    public void testGetTotalPageConcurrent() {
        CountDownLatch cdl = new CountDownLatch(100);
        long startT = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            calcTotalPage();
            cdl.countDown();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endT = System.currentTimeMillis();
        logger.info("PdfBoxLibrary - cost:{}", endT - startT);
    }
    private void calcTotalPage() {
        PdfParam pdfParam = new PdfParam.Builder()
                .setFilePath(getFilePath())
                .setImagePath(getImagePath())
                .setImageName(PdfConstant.EMPTY_STRING)
                .setImageFormat("jpg")
                .setImageNameDelimiter(PdfConstant.EMPTY_STRING)
                .setImageStartPageNo(100000)
                .build();

        PdfResult pdfResult = PdfProcessor.getInstance().load(PdfBoxLibrary.class).getTotalPage(pdfParam);
        logger.info(JSON.toJSONString(pdfResult));
    }

    @Test
    public void testConvertPdfToJpg() {
        PdfParam pdfParam = new PdfParam.Builder()
                .setFilePath(getFilePath())
                .setImagePath(getImagePath())
                .setImageName(PdfConstant.EMPTY_STRING)
                .setImageFormat("jpg")
                .setImageNameDelimiter(PdfConstant.EMPTY_STRING)
                .setImageStartPageNo(100000)
                .build();

        PdfResult pdfResult = PdfProcessor.getInstance().load(PdfBoxLibrary.class).convertToImage(pdfParam);
        logger.info(JSON.toJSONString(pdfResult));
    }


}
