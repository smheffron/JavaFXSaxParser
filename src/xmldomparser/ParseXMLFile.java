/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmldomparser;

import java.io.File;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Shelby
 */
public class ParseXMLFile {

    private static XMLNode root = null;

    private static XMLNode currentNode = null;
    
    private static Stack<XMLNode> stack = new Stack<>();
    
    private static String currentElementName = null;
    
    private static String results;
    
    private static boolean firstNode;
    
    private static int currentDepth;
    
    private static String depthString;
    
    
    
    public static XMLNode parseXML(File XMLFile) throws Exception{
        
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            currentDepth = 0;
            depthString = "";
            results = "";
            firstNode = true;
                    
            
            DefaultHandler handler = new DefaultHandler() {
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    
                    XMLNode node = new XMLNode();
                    node.name=qName;
                    
                    node.depth = currentDepth;
                    
                    
                    depthString = "";
                            
                    for(int i = 0; i<currentDepth; i++){
                        depthString +="\t";
                    }
                    
                    
                    for(int i=0; i< attributes.getLength(); i++){
                        node.attributes.put(attributes.getQName(i), attributes.getValue(i));
                    }
                    
                    if(firstNode){
                        results+=("name: " + node.name
                            + "\n" + depthString + "content: " + node.content
                            );
                        firstNode = false;
                    }
                    
                    else{
                        results+=("\n\n" + depthString + "name: " + node.name
                            + "\n" + depthString + "content: " + node.content
                            );
                    }
                    
                    
                    
                    results+=("\n"+ depthString + "attribute: " + node.attributes.keySet() + " = " + node.attributes.values());
                    
                    
                    stack.push(node);
                    
                    currentDepth++;
                    
                    if(currentNode!=null){
                        
                        if(currentNode.properties!=null){
                            currentNode.properties.add(node);
                        }   
                    }
                    currentNode = node;  
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                 
                    XMLNode poppedNode = stack.pop();
                    
                    if(stack.empty()){
                        root = poppedNode;
                        currentNode = null;
                    }
                    else{
                        currentNode = stack.lastElement();
                        
                        currentDepth--;
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    currentNode.content = new String(ch, start, length);
                }
                                       
            }; 

            saxParser.parse(XMLFile.getAbsoluteFile(), handler);
            
        }catch(Exception e){
            throw(e);
        }
       
        
        
    return root;
    
    }  
    
    public static String getResults(){
        return ParseXMLFile.results;
    }
    
}
