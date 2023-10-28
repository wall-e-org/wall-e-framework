package tech.huning.wall.e.util.pdf.specs;

import tech.huning.wall.e.util.pdf.exception.PdfException;
import tech.huning.wall.e.util.pdf.model.PdfParam;
import tech.huning.wall.e.util.pdf.model.PdfResult;

/**
 * Pdf处理库
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public interface IPdfLibrary {

    /**
     * 转为图片
     * @param param pdf信息入参
     * @return 读取结果
     * @throws PdfException pdf异常
     */
    PdfResult convert(PdfParam param) throws PdfException;

    /**
     * 目录清理
     * @param param   pdf信息入参
     * @throws PdfException pdf异常
     */
    void cleanFileDir(PdfParam param) throws PdfException;

    /**
     * 组装图片文件路径
     * @param param   pdf信息入参
     * @param pageNo  页码
     * @return 全路径
     * @throws PdfException pdf异常
     */
    String pkgImageFilePath(PdfParam param, int pageNo) throws PdfException;

    /**
     * 获取总页数
     * @param filePath 文件路径
     * @return 总页数
     * @throws PdfException pdf异常
     */
    long getTotalPage(String filePath) throws PdfException;

}
