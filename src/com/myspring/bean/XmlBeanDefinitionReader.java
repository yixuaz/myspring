package com.myspring.bean;

import com.myspring.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinition(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinition(inputStream);
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        registerBeanDefinition(document);
        inputStream.close();
    }

    private void registerBeanDefinition(Document document) {
        Element root = document.getDocumentElement();
        NodeList nl = root.getChildNodes();
        for(int i = 0; i < nl.getLength(); i++){
            Node cur = nl.item(i);
            if(cur instanceof Element && "bean".equals(cur.getNodeName())){
                Element node = (Element) cur;
                parseBeanDefinition(node);
            }
        }
    }

    private void parseBeanDefinition(Element node) {
        String name = node.getAttribute("id");
        String className = node.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(node,beanDefinition);
        beanDefinition.setBeanClassName(className);
        getBeanRegistry().put(name,beanDefinition);
        
    }

    private void processProperty(Element node, BeanDefinition beanDefinition) {
        NodeList nl = node.getElementsByTagName("property");
        for(int i = 0; i < nl.getLength(); i++) {
            Node cur = nl.item(i);
            if (cur instanceof Element && "property".equals(cur.getNodeName())) {
                Element propertyEle = (Element) cur;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().add(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().add(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
