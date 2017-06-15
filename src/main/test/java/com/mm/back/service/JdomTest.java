package com.mm.back.service;

import java.io.File;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/12
 * Time:23:23
 * Desc:描述该类的作用
 */
public class JdomTest {
    public static final String fileName = "test.xml";


    public static void main(String[] args) throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();

        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(path));
        Element rootElement = doc.getRootElement();
        List<Element> elements = rootElement.getChildren();
        for (Element element : elements) {
            System.out.println(element.getName());
        }

    }
}
