package com.zpl.practice;

import org.apache.commons.io.IOUtils;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author 张沛霖
 * @date 2020/12/29
 */
public class Test {

    public static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws Exception {
        // pdf2Tif("C:\\Users\\张沛霖\\Desktop\\bef8a960504046ec933c439f24cddd99.pdf", "", "");
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("TIFF");
        ImageWriter writer = null;
        while (writers.hasNext()) {
            writer = writers.next();
            if (writer instanceof com.twelvemonkeys.imageio.plugins.tiff.TIFFImageWriter) {
                break;
            }
        }

        writers = ImageIO.getImageWritersByFormatName("TIFF");
        ImageWriter writer1 = null;
        while (writers.hasNext()) {
            writer1 = writers.next();
            if (writer1 instanceof com.twelvemonkeys.imageio.plugins.tiff.TIFFImageWriter) {
                break;
            }
        }

        System.out.println(writer == writer1);
    }


    public static boolean pdf2Tif(String pdfPath, String tifPath, String type) throws Exception {
        File file = new File(pdfPath);
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] byteArray = IOUtils.toByteArray(fileInputStream);
        logger.info("{}", byteArray.length);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("TIFF");
        ImageWriter writer = null;
        while (writers.hasNext()) {
            writer = writers.next();
            if (writer instanceof com.twelvemonkeys.imageio.plugins.tiff.TIFFImageWriter) {
                break;
            }
        }
        if (!(writer instanceof com.twelvemonkeys.imageio.plugins.tiff.TIFFImageWriter)) {
            throw new Exception("找不到 twelvemonkeys TIFFImageWriter");
        }
        try (PDDocument doc = PDDocument.load(byteArray);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream)) {

            //pdf 页数
            int pageCount = doc.getNumberOfPages();
            // 根据PDDocument对象创建pdf渲染器
            PDFRenderer renderer = new PDFRenderer(doc);

            // 合并多页 pdf，将每页 pdf 分别 render 为一个 bufferedImage，然后合并成一个大的 bufferedImage
            BufferedImage[] bufferedImages = new BufferedImage[pageCount];
            int totalHeight = 0;
            //分别 render 每页 pdf
            for (int i = 0; i < pageCount; i++) {
                long renderImageStart = System.currentTimeMillis();
                BufferedImage image = renderer.renderImageWithDPI(i, 160, ImageType.RGB);
                long renderImageEnd = System.currentTimeMillis();
                logger.info("pdf renderImage 耗时：{}", renderImageEnd - renderImageStart);
                bufferedImages[i] = image;
                //计算图片总高度
                totalHeight += image.getHeight();
            }

            //设置图片总高度
            BufferedImage combinedImage = new BufferedImage(bufferedImages[0].getWidth(), totalHeight, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = combinedImage.getGraphics();

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

            ImageWriteParam param = writer.getDefaultWriteParam();
            //选择压缩模式
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            //压缩算法
            param.setCompressionType("ZLib");
            //压缩率 0.1 ~ 1，压缩率小 文件小，耗时长
            param.setCompressionQuality(1f);

            //写入输出流中
            writer.setOutput(imageOutputStream);
            long writeStart = System.currentTimeMillis();
            writer.write(null, new IIOImage(combinedImage, null, null), param);
            long writeEnd = System.currentTimeMillis();
            logger.info("写入 tif 耗时：{} ms,tif byteArray 长度：{}", writeEnd - writeStart, byteArrayOutputStream.toByteArray().length);
        } catch (IOException e) {
            logger.error("pdf文件转tif异常,文件名:{},异常信息:{}", pdfPath,
                    e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            writer.dispose();
        }
        return true;
    }
}
