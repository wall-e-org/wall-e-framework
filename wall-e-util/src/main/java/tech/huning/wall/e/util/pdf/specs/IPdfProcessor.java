package tech.huning.wall.e.util.pdf.specs;

import tech.huning.wall.e.util.pdf.model.PdfParam;
import tech.huning.wall.e.util.pdf.model.PdfResult;

/**
 * Pdf处理器
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public interface IPdfProcessor {

    /**
     * 加载库
     * @param clazz 处理器类
     * @return 处理器
     */
    IPdfProcessor load(Class<? extends IPdfLibrary> clazz);

    /**
     * 获取总页数
     * @param param pdf信息入参
     * @return 读取结果
     */
    PdfResult getTotalPage(PdfParam param);

    /**
     * 转为图片
     * @param param pdf信息入参
     * @return 读取结果
     */
    PdfResult convertToImage(PdfParam param);

}
