package tech.huning.wall.e.util.pdf.model;

/**
 * Pdf处理入参
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class PdfParam {

    public PdfParam(Builder builder){
        this.filePath = builder.filePath;
        this.imagePath = builder.imagePath;
        this.imageName = builder.imageName;
        this.imageFormat = builder.imageFormat;
        this.imageNameDelimiter = builder.imageNameDelimiter;
        this.imageStartPageNo = builder.imageStartPageNo;
    }

    private String filePath;
    private long totalPage;
    private String imagePath;
    private String imageName;
    private String imageFormat;
    private String imageNameDelimiter;
    private long imageStartPageNo;

    public String getFilePath() {
        return filePath;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public String getImageNameDelimiter() {
        return imageNameDelimiter;
    }

    public long getImageStartPageNo() {
        return imageStartPageNo;
    }

    public static class Builder {

        private String filePath;
        private long totalPage;
        private String imagePath;
        private String imageName;
        private String imageFormat;
        private String imageNameDelimiter;
        private long imageStartPageNo;

        public String getFilePath() {
            return filePath;
        }

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public long getTotalPage() {
            return totalPage;
        }

        public Builder setTotalPage(long totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public String getImagePath() {
            return imagePath;
        }

        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public String getImageName() {
            return imageName;
        }

        public Builder setImageName(String imageName) {
            this.imageName = imageName;
            return this;
        }

        public String getImageFormat() {
            return imageFormat;
        }

        public Builder setImageFormat(String imageFormat) {
            this.imageFormat = imageFormat;
            return this;
        }

        public String getImageNameDelimiter() {
            return imageNameDelimiter;
        }

        public Builder setImageNameDelimiter(String imageNameDelimiter) {
            this.imageNameDelimiter = imageNameDelimiter;
            return this;
        }

        public long getImageStartPageNo() {
            return imageStartPageNo;
        }

        public Builder setImageStartPageNo(long imageStartPageNo) {
            this.imageStartPageNo = imageStartPageNo;
            return this;
        }

        public PdfParam build() {
            return new PdfParam(this);
        }

    }

}
