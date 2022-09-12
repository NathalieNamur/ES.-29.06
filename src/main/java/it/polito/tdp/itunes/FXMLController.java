package it.polito.tdp.itunes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.BilancioAlbum;
import it.polito.tdp.itunes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdiacenze;
    
    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnPercorso;

    @FXML
    private ComboBox<Album> cmbA1;

    @FXML 
    private ComboBox<?> cmbA2;

    @FXML
    private TextField txtN;

    @FXML 
    private TextArea txtResult; 

    @FXML 
    private TextField txtX; 

    
    
    @FXML
    void doCalcolaAdiacenze(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	
    	if(cmbA1.getItems().isEmpty()) {
    		txtResult.setText("ERRORE: Creare prima un grafo.");
    		return;
    	}
    	
    	Album a = cmbA1.getValue();
    	
    	if(a==null) {
    		txtResult.setText("ERRORE: Selezionare un album.");
    		return; 
    	}
    	
    	
    	for(BilancioAlbum b : model.getSuccessori(a)) {
    		txtResult.appendText(b.toString()+"\n");
    	}
    		
    }
    

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    }

    
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	
    	int n; 
    	
    	try {
    		
    		n = Integer.parseInt(txtN.getText());
    		
    	} catch(NumberFormatException e){
    		txtResult.setText("ERRORE: Inserire numero canzoni correttamente.");
    		return;
    	}
    	
    	
    	model.creaGrafo(n);
    	txtResult.appendText("Grafo creato.");
    	txtResult.appendText("\nNumero vertici grafo: "+model.getNumVertici());
    	txtResult.appendText("\nNumero archi grafo: "+model.getNumArchi());
    	
    	
    	cmbA1.getItems().setAll(model.getVertici());
    	
    }

    
    
    @FXML
    void initialize() {
        assert btnAdiacenze != null : "fx:id=\"btnAdiacenze\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA2 != null : "fx:id=\"cmbA2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    
    public void setModel(Model model) {
    	this.model = model;
    }


}
