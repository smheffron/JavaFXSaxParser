/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmldomparser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Shelby
 */
public class XMLNode {
    String name;
    String content;
    HashMap<String,String> attributes;
    ArrayList<XMLNode> properties;
    
    int depth = 0;

    public XMLNode() {
        this.attributes = new HashMap();
        this.properties = new ArrayList();
    }
}
