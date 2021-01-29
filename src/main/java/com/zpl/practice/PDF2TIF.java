package com.zpl.practice;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * pdf 转 tif
 *
 * @author 张沛霖
 * @date 2020/12/29
 */
public class PDF2TIF {

    static final Logger logger = LoggerFactory.getLogger(PDF2TIF.class);

    public static void main(String[] args) throws IOException {

        String[] fileName = new String[]{"440174907691008.pdf", "440164440642008.pdf", "900011503961008.pdf"};

        // test("C:\\Users\\张沛霖\\Desktop\\PDF 转 tiff 替换方案.pdf", "C:\\Users\\张沛霖\\Desktop\\新建文件夹\\压缩测试");
        test1("C:\\Users\\张沛霖\\Desktop\\PDF 转 tiff 替换方案.pdf", "C:\\Users\\张沛霖\\Desktop\\新建文件夹\\压缩测试");

    }

    public static void test1() {
        final Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("TIFF");
        ImageWriter writer = writers.next();

    }

    /**
     * 多页 pdf 转 多个 tif
     *
     * @param path
     * @param outputDir
     * @throws IOException
     */
    public static void test(String path, String outputDir) throws IOException {
        // File file = new File("C:\\Users\\张沛霖\\Desktop\\新建文件夹\\440164440642008.pdf");
        File file = new File(path);

        try (PDDocument doc = PDDocument.load(file)) {
            int pageCount = doc.getNumberOfPages();
            logger.info("pdf 页数：{}", pageCount);

            // 创建pdf渲染器
            PDFRenderer renderer = new PDFRenderer(doc);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("TIFF");
            ImageWriter writer = writers.next();
            ImageWriteParam param = writer.getDefaultWriteParam();
            //压缩模式
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionType("ZLib");
            param.setCompressionQuality(1f);

            for (int i = 0; i < pageCount; i++) {
                try (FileOutputStream out = new FileOutputStream(outputDir + "\\" + i + ".tif");
                     ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(out)) {

                    writer.setOutput(imageOutputStream);

                    long pdfRenderStart = System.currentTimeMillis();
                    BufferedImage image = renderer.renderImageWithDPI(i, 160, ImageType.RGB);
                    long pdfRenderEnd = System.currentTimeMillis();
                    logger.info("renderImage 耗时：{}", pdfRenderEnd - pdfRenderStart);

                    // writer.write(null,new IIOImage(image,null,tiffImageMetadata),param);
                    long writeStart = System.currentTimeMillis();
                    writer.write(null, new IIOImage(image, null, null), param);
                    long writeEnd = System.currentTimeMillis();
                    logger.info("写入 tif 耗时：{} ms,", writeEnd - writeStart);
                }
            }
        }
    }

    /**
     * 多页 pdf 转成 1个 tif
     *
     * @param path
     * @param outputDir
     * @throws IOException
     */
    public static void test1(String path, String outputDir) throws IOException {
        // File file = new File("C:\\Users\\张沛霖\\Desktop\\新建文件夹\\440164440642008.pdf");
        File file = new File(path);

        try (PDDocument doc = PDDocument.load(file);
             FileOutputStream out = new FileOutputStream(outputDir + "\\combined.tif");
             ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(out)) {

            int pageCount = doc.getNumberOfPages();
            logger.info("pdf 页数：{}", pageCount);

            // 根据PDDocument对象创建pdf渲染器
            PDFRenderer renderer = new PDFRenderer(doc);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("TIFF");
            ImageWriter writer = writers.next();
            ImageWriteParam param = writer.getDefaultWriteParam();
            //压缩模式
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionType("ZLib");
            param.setCompressionQuality(1f);

            BufferedImage[] bufferedImages = new BufferedImage[pageCount];
            int totalHeight = 0;
            for (int i = 0; i < pageCount; i++) {
                writer.setOutput(imageOutputStream);
                long pdfRenderStart = System.currentTimeMillis();
                BufferedImage image = renderer.renderImageWithDPI(i, 160, ImageType.RGB);
                long pdfRenderEnd = System.currentTimeMillis();
                logger.info("pdf 提取 bufferedImage 耗时：{}", pdfRenderEnd - pdfRenderStart);
                bufferedImages[i] = image;
                //计算图片总高度
                totalHeight += image.getHeight();
            }
            // writer.write(null,new IIOImage(image,null,tiffImageMetadata),param);

            //设置图片总高度
            BufferedImage bufferedImage = new BufferedImage(bufferedImages[0].getWidth(), totalHeight, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.getGraphics();

            //下个图片起始的高度
            int nextImageVerticalPosition = 0;
            //合并多个图片
            for (int i = 0; i < pageCount; i++) {
                if (i == 0) {
                    graphics.drawImage(bufferedImages[0], 0, 0, null);
                    nextImageVerticalPosition = bufferedImages[0].getHeight();
                } else {
                    graphics.drawImage(bufferedImages[i], 0, nextImageVerticalPosition, null);
                    nextImageVerticalPosition += bufferedImages[i].getHeight();
                }
            }
            graphics.dispose();

            long writeStart = System.currentTimeMillis();
            writer.write(null, new IIOImage(bufferedImage, null, null), param);
            long writeEnd = System.currentTimeMillis();
            logger.info("pdf 转 tif 耗时：{} ms", writeEnd - writeStart);
        }
    }
}

