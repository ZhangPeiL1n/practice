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
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * pdf 转 tif
 *
 * @author 张沛霖
 * @date 2020/12/29
 */
public class PDF2TIF {

    static final Logger logger = LoggerFactory.getLogger(PDF2TIF.class);

    public static void main(String[] args) throws Exception {

        // String[] fileName = new String[]{"440174907691008.pdf", "440164440642008.pdf", "900011503961008.pdf"};
        //
        // test("C:\\Users\\张沛霖\\Desktop\\PDF 转 tiff 替换方案.pdf", "C:\\Users\\张沛霖\\Desktop\\新建文件夹\\压缩测试");

        // test1("C:\\Users\\张沛霖\\Desktop\\PDF 转 tiff 替换方案.pdf", "C:\\Users\\张沛霖\\Desktop\\新建文件夹\\压缩测试");
        HashSet<String> strings = new HashSet<>();
        strings.add("a");
        System.out.println(strings.contains("a"));
    }

    /**
     * 多页 pdf 转 多个 tif
     *
     * @param path
     * @param outputDir
     * @throws IOException
     */
    public static void test(String path, String outputDir) throws Exception {
        // File file = new File("C:\\Users\\张沛霖\\Desktop\\新建文件夹\\440164440642008.pdf");
        File file = new File(path);

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
        ImageWriteParam param = writer.getDefaultWriteParam();
        //压缩模式
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionType("ZLib");
        param.setCompressionQuality(1f);

        try (PDDocument doc = PDDocument.load(file)) {
            int pageCount = doc.getNumberOfPages();

            // 创建pdf渲染器
            PDFRenderer renderer = new PDFRenderer(doc);


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

        } finally {
            writer.dispose();
        }
    }

    /**
     * 多页 pdf 转成 1个 tif
     *
     * @param pdfPath
     * @param pdfPath
     * @throws IOException
     */
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

