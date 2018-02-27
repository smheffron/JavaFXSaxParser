/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmldomparser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Shelby
 */
public class ParserViewController implements Initializable {

    @FXML
    private TextArea textArea;
    
    @FXML
    private AnchorPane anchorPane;
    
    private XMLNode root;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleOpen(ActionEvent event) {
        
      FileChooser fileChooser = new FileChooser();
      File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
      if (file != null) {
        try
        {
            textArea.setText("");
            root = ParseXMLFile.parseXML(file);
          
            
            printDOM();
            
                
        } catch (Exception ex) {
            handleException(ex);
        }
        
        
        
        }
    }
    
    private void handleException(Exception e){
        Alert alert = new Alert(AlertType.ERROR, "Error encountered while parsing: " + e + " ?", ButtonType.OK);
        alert.showAndWait();
    }

    private void printDOM() {
        
        textArea.setText(ParseXMLFile.getResults());
        
        
        
        
    }
    
    
}
