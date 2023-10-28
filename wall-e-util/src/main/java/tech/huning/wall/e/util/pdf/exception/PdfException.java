package tech.huning.wall.e.util.pdf.exception;

/**
 * Pdf处理异常
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class PdfException extends Exception {

    public PdfException() {
        super();
    }

    public PdfException(String message) {
        super(message);
    }

    public PdfException(String message, Throwable cause) {
        super(message, cause);
    }

    public PdfException(Throwable cause) {
        super(cause);
    }

}
