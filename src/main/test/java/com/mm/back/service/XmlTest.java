package com.mm.back.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/6/12
 * Time:22:44
 * Desc:描述该类的作用
 */
public class XmlTest {
    public static final String fileName = "test.xml";

    public static void main(String[] args) throws Exception {
        String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();

        //dom解析
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(path);
        NodeList nodeList = document.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getNodeName());
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node node1 = childNodes.item(j);
                if (node1.getNodeType()== Node.ELEMENT_NODE) {
                    System.out.println(node1.getNodeName());
                    NodeList childNodes1 = node.getChildNodes();
                }

            }
        }

        //SAX解析
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxparser = saxParserFactory.newSAXParser();
        InputStream is = new FileInputStream(path);
        saxparser.parse(is, new MySAXHandler());




        //dom4j
        SAXReader reader = new SAXReader();
        org.dom4j.Document doc = reader.read(new File(path));
        Element rootElement = doc.getRootElement();
        List<org.dom4j.Node> list = rootElement.selectNodes("/books/book");



        //jdom
        SAXBuilder builder = new SAXBuilder();
        org.jdom2.Document jdoc = builder.build(new File(path));
        org.jdom2.Element element = jdoc.getRootElement();
        List<org.jdom2.Element> elements = element.getChildren();






    }
}
