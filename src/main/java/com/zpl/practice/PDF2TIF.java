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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Iterator;

/**
 * @author 张沛霖
 * @date 2020/12/29
 */
public class PDF2TIF {

    static final Logger logger = LoggerFactory.getLogger(PDF2TIF.class);

    /**
     *
     */
    SoftReference<String> A = new SoftReference<>("aaaaaaaa");

    public static void main(String[] args) throws IOException {

        String[] fileName = new String[]{"440174907691008.pdf", "440164440642008.pdf", "900011503961008.pdf"};

        int max = 10;
        // int max = 100;


        long count = 0;

        for (int i = 0; i < max; i++) {
            long startTime = System.currentTimeMillis();
            String path = "C:\\Users\\张沛霖\\Desktop\\新建文件夹\\" + fileName[i % 3];

            // try {
            //     pdf2Tif(path, fileName[i % fileName.length], i);
            // } catch (PDFException | PDFSecurityException | InterruptedException e) {
            //     e.printStackTrace();
            // }

            // Test.pdf2TifPageOneByOne(path,i);

            // PDF2TIF.test(path, outputPath, i);
            long endTime = System.currentTimeMillis();
            System.out.printf("总耗时：%d%n", endTime - startTime);

            if (i != 0) {
                count += endTime - startTime;
            }
            logger.info("第 {} 次耗时：{}", i, endTime - startTime);


            System.out.println("-------------------------------------");
        }
        System.out.println(count / (max - 1));

    }


    public static void test(String path, String outputDir, int index) throws IOException {
        // File file = new File("C:\\Users\\张沛霖\\Desktop\\新建文件夹\\440164440642008.pdf");
        File file = new File(path);

        try (PDDocument doc = PDDocument.load(file);
             FileOutputStream out = new FileOutputStream(outputDir + "\\" + index + ".tif");
             ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(out)) {

            // 根据PDDocument对象创建pdf渲染器
            PDFRenderer renderer = new PDFRenderer(doc);

            long pdfRenderStart = System.currentTimeMillis();
            BufferedImage image = renderer.renderImageWithDPI(0, 160, ImageType.RGB);
            long pdfRenderEnd = System.currentTimeMillis();
            logger.info("pdf 提取 bufferedImage 耗时：{}", pdfRenderEnd - pdfRenderStart);

            final Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("TIFF");
            final ImageWriter writer = writers.next();
            final ImageWriteParam param = writer.getDefaultWriteParam();
            //压缩模式
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            // System.out.println(Arrays.toString(param.getCompressionTypes()));

            param.setCompressionType("ZLib");

            param.setCompressionQuality(1f);

            writer.setOutput(imageOutputStream);
            // writer.write(null,new IIOImage(image,null,tiffImageMetadata),param);
            long writeStart = System.currentTimeMillis();
            writer.write(null, new IIOImage(image, null, null), param);
            long writeEnd = System.currentTimeMillis();
            logger.info("pdf 转 tif 耗时：{} ms", writeEnd - writeStart);
        }

    }

}
