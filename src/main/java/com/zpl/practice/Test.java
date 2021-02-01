package com.zpl.practice;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

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

    public static void main(String[] args) throws IOException {

        // BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\张沛霖\\Desktop\\测试图片.jpg"));
        //
        // ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // ImageIO.write(bufferedImage, "jpg", stream);
        // BASE64Encoder encoder = new BASE64Encoder();
        // //转换成base64串
        // String png_base64 = encoder.encodeBuffer(stream.toByteArray()).trim();
        // //删除 \r\n
        // png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
        // System.out.println("data:image/jpg;base64,"+png_base64);
        pdf2Tif("C:\\Users\\张沛霖\\Desktop\\bef8a960504046ec933c439f24cddd99.pdf", "", "");
    }


    public static boolean pdf2Tif(String pdfPath, String tifPath, String type) throws IOException {
        // long pdfStart = System.currentTimeMillis();
        // //从对象存储取 pdf
        // byte[] hxTifArray  = hcpService.getObjectToByteArray(pdfPath);
        // long pdfEnd = System.currentTimeMillis();
        // logger.info("从对象存储获取 pdf 耗时：{} ms, pdf 长度为：{},路径为：{}",pdfEnd - pdfStart,(hxTifArray != null ? hxTifArray.length : "hxTifArray 为 null"),pdfPath);
        // if(hxTifArray == null || hxTifArray.length == 0) {
        //     throw new IOException("获取对象存储 PDF 文件失败,路径为：" + pdfPath + ",pdf 长度为：" + (hxTifArray != null ? 0 : "hxTifArray 为 null"));
        // }

        File file = new File(pdfPath);
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] byteArray = IOUtils.toByteArray(fileInputStream);

        logger.info("{}", byteArray.length);
        try (PDDocument doc = PDDocument.load(byteArray);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream)) {

            //pdf 页数
            int pageCount = doc.getNumberOfPages();
            logger.info("pdf 总页数：{}", pageCount);
            // 根据PDDocument对象创建pdf渲染器
            PDFRenderer renderer = new PDFRenderer(doc);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("TIFF");
            ImageWriter writer = writers.next();
            ImageWriteParam param = writer.getDefaultWriteParam();
            //选择压缩模式
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            //压缩算法
            param.setCompressionType("ZLib");
            //压缩率 0.1 ~ 1，压缩率小 文件小，耗时长
            param.setCompressionQuality(1f);

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

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(combinedImage, "jpg", stream);
            BASE64Encoder encoder = new BASE64Encoder();
            //转换成base64串
            String png_base64 = encoder.encodeBuffer(stream.toByteArray()).trim();
            //删除 \r\n
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
            System.out.println("data:image/jpg;base64," + png_base64);
            // ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // ImageIO.write(combinedImage, "jpg", stream);
            // BASE64Encoder encoder = new BASE64Encoder();
            // //转换成base64串
            // String png_base64 = encoder.encodeBuffer(stream.toByteArray()).trim();
            // //删除 \r\n
            // png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
            // System.out.println("data:image/jpg;base64,"+png_base64);


            //写入输出流中
            writer.setOutput(imageOutputStream);
            long writeStart = System.currentTimeMillis();
            writer.write(null, new IIOImage(combinedImage, null, null), param);
            long writeEnd = System.currentTimeMillis();
            logger.info("写入 tif 耗时：{} ms,tif byteArray 长度：{}", writeEnd - writeStart, byteArrayOutputStream.toByteArray().length);

            //向对象存储中写入 tif
            // long tifStart = System.currentTimeMillis();
            // boolean hxTifFileBoolean = hcpService.createObject(byteArrayOutputStream.toByteArray(), tifPath);
            // long tifEnd = System.currentTimeMillis();
            // logger.info("向对象存储存储 tif 耗时：{} ms",tifEnd - tifStart);
            // if(!hxTifFileBoolean) {
            //     logger.error("向对象存储中创建 tif 文件失败，路径为：{}", tifPath);
            //     return false;
            // } else {
            //     logger.info("向对象存储中创建 tif 文件成功，路径为：{}",tifPath);
            // }
        } catch (IOException e) {
            logger.error("pdf文件转tif异常,文件名:{},异常信息:{}", pdfPath,
                    e.getMessage());
            return false;
        }
        return true;
    }
}
