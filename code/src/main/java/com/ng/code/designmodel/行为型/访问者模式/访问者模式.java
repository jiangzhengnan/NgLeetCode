package com.ng.code.designmodel.行为型.访问者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 
 * @creation : 2022/10/06
 * @description :
 * 概念：
 * 允许一个或者多个操作应用到一组对象上，解耦操作和对象本身。
 * 示例：
 * 从网站上爬取了很多资源文件，它们的格式有三种：PDF、PPT、Word。我们现在要开发一个工具来处理这批资源文件，
 * 抽取出来放在txt中
 */
public class 访问者模式 {

    static abstract class ResourceFile {
        protected String filePath;

        public ResourceFile(String filePath) {
            this.filePath = filePath;
        }

        abstract public void accept(Visitor vistor);
    }

    static class PdfFile extends ResourceFile {
        public PdfFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        //...
    }

    static class WordFile extends ResourceFile {
        public WordFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        //...
    }

    static class PPTFile extends ResourceFile {
        public PPTFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        //...
    }

    public interface Visitor {
        void visit(PdfFile pdfFile);

        void visit(PPTFile pdfFile);

        void visit(WordFile pdfFile);
    }

    static class Extractor implements Visitor {
        @Override
        public void visit(PPTFile pptFile) {
            //...
            System.out.println("Extract PPT.");
        }

        @Override
        public void visit(PdfFile pdfFile) {
            //...
            System.out.println("Extract PDF.");
        }

        @Override
        public void visit(WordFile wordFile) {
            //...
            System.out.println("Extract WORD.");
        }
    }

    static class Compressor implements Visitor {
        @Override
        public void visit(PPTFile pptFile) {
            //...
            System.out.println("Compress PPT.");
        }

        @Override
        public void visit(PdfFile pdfFile) {
            //...
            System.out.println("Compress PDF.");
        }

        @Override
        public void visit(WordFile wordFile) {
            //...
            System.out.println("Compress WORD.");
        }

    }


    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(extractor);
        }

        Compressor compressor = new Compressor();
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(compressor);
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        resourceFiles.add(new PPTFile("c.ppt"));
        return resourceFiles;
    }
}
