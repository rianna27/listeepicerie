/*
 * Auteur: Rianna Smuk
 * Date: 10 Mai 2021
 * Description: Code qui permet d'afficher  votre liste d'épicerie. 
 */


package application;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SampleController implements Initializable{

    @FXML
    private TextField txtPrix;

    
    @FXML
    private Button btnEffacer;

    @FXML
    private TableColumn<Liste1, String> columnNom;

    @FXML
    private TextField txtQuantite;

    @FXML
    private Button btnRecommencer;

    @FXML
    private TableColumn<Liste1, Double> columnQuantite;

    @FXML
    private Button btnModifier;

    @FXML
    private ComboBox<String> cboDep;

    @FXML
    private TableColumn<Liste1, Double> columnPrix;

    @FXML
    private Button btnAjouter;

    @FXML
    private TableColumn<Liste1, String> columnDep;
    
    @FXML
    private TableColumn<Liste1, String> columnTotal;

    @FXML
    private TextField txtNom;
    
   
    
 
    
    @FXML
   	private TableView<Liste1> ListeTable;
  //liste pour les départements  - cette liste permettrra de donner les valeurs du comboBox

  	private ObservableList<String> list=(ObservableList<String>) FXCollections.observableArrayList("Produit agricole","Viande", "Fruits de mer", "Bière et vin", "Santé et Beauté", "Deli / Aliments préparés");                                            
  			
  			
  	//Placer les epicerie dans une observable list

  	public ObservableList<Liste1> listeData=FXCollections.observableArrayList();

  	//Créer une méthode pour accéder à la liste des épiceries

  	public  ObservableList<Liste1> getlisteData()
  	{
  		return listeData;
  	}
  	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
   		cboDep.setItems(list);
		//attribuer les valeurs aux colonnes du TableView
		columnDep.setCellValueFactory(new PropertyValueFactory<>("departement"));
		columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		columnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		columnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		ListeTable.setItems(listeData);
		btnModifier.setDisable(true);
		btnEffacer.setDisable(true);
		btnRecommencer.setDisable(true);
		showliste(null);
		
		//Mettre à jour l'affichage d'une epicerie sélectionnée
				ListeTable.getSelectionModel().selectedItemProperty().addListener((
						observable, oldValue, newValue)-> showliste(newValue));
    }
   	
	
	
	
	@FXML
	public void verifNum() // méthode pour trouver des input non numériques
	{
		txtQuantite.textProperty().addListener((observable,oldValue,newValue)-> 
		{
			if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
			{
				txtQuantite.setText(newValue.replaceAll("[^\\d*]", "")); //si le input est non numérique, ca le remplace
			}
		});}
	
	@FXML
	public void verifNum2() // méthode pour trouver des input non numériques
	{
		txtPrix.textProperty().addListener((observable,oldValue,newValue)-> 
		{
			if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
			{
				txtPrix.setText(newValue.replaceAll("[^\\d*]", "")); //si le input est non numérique, ca le remplace
			}
		});}
	
	
	
  //Ajouter 
  	@FXML
  	void ajouter()
  	{
  		// vérifier si un champ n'est pas vide
  		
  		if(noEmptyInput())
		{
  		Liste1 tmp=new Liste1();
  		tmp=new Liste1();
  		tmp.setNom(txtNom.getText());
  		tmp.setQuantite(Double.parseDouble(txtQuantite.getText()));
  		tmp.setPrix(Double.parseDouble(txtPrix.getText()));
  		tmp.setDepartement(cboDep.getValue());
  		Double quantite= Double.parseDouble(txtQuantite.getText());
  		Double prix= Double.parseDouble(txtPrix.getText());
  		Double tot= quantite * prix;
  		tmp.setTotal(tot);
  		listeData.add(tmp);
  		clearFields();
      
    }
    }
  	
  	
  		
  
  	
  	
  	//Effacer le contenu des champs
	@FXML
	void clearFields()
	{
		cboDep.setValue(null);
		txtNom.setText("");
		txtQuantite.setText("");
		txtPrix.setText("");
		btnAjouter.setDisable(false);
		btnModifier.setDisable(true);
		btnEffacer.setDisable(true);
		btnRecommencer.setDisable(true);
		
		
		
	}
	
	
	//Afficher les données
	 
		public void showliste(Liste1 liste)
		{
			if(liste !=null)
			{
				cboDep.setValue(liste.getDepartement());
				txtNom.setText(liste.getNom());
				txtQuantite.setText(Double.toString(liste.getQuantite()));
				txtPrix.setText(Double.toString(liste.getPrix()));
				btnAjouter.setDisable(true);
				btnModifier.setDisable(false);
				btnEffacer.setDisable(false);
				btnRecommencer.setDisable(false);
			
			
			}
			else
			{
				clearFields();
			}
		}
		//Mise à jour 
		
		@FXML
		public void updateListe()
		{
		// vérifier si un champ n'est pas vide
			if(noEmptyInput())
			{
		   
			Liste1 liste=ListeTable.getSelectionModel().getSelectedItem();
			liste.setNom(txtNom.getText());
			liste.setQuantite(Double.parseDouble(txtQuantite.getText()));
			liste.setPrix(Double.parseDouble(txtPrix.getText()));
			liste.setDepartement(cboDep.getValue());
			Double quantite= Double.parseDouble(txtQuantite.getText());
	  		Double prix= Double.parseDouble(txtPrix.getText());
	  		Double tot= quantite * prix;
	  		liste.setTotal(tot);
	  		ListeTable.refresh();
	  	
			
			
			
		 }
		}
		
		//Effacer une épicerie
		
		@FXML
		public void deleteListe()
		{
			int selectedIndex = ListeTable.getSelectionModel().getSelectedIndex();
			if (selectedIndex >=0)
			{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Erreur");
				alert.setContentText("confirmer la suppression!");
				Optional<ButtonType> result= alert.showAndWait();
				if(result.get()==ButtonType.OK)
			    ListeTable.getItems().remove(selectedIndex);
				
			}
		}
		
	
		
		//vérifier les champs vides 
		private boolean noEmptyInput()
		{
			String errorMessage="";
			if(txtNom.getText().trim().equals(""))
			{
				errorMessage+="Le champ nom ne doit pas être vide! \n";
			}
			if(txtPrix.getText()==null||txtPrix.getText().length()==0)
			{
				errorMessage+="Le champ prix ne doit pas être vide! \n";
			}
			if(txtQuantite.getText()==null||txtQuantite.getText().length()==0)
			{
				errorMessage+="Le champ quantité ne doit pas être vide! \n";
			}
			if(cboDep.getValue()==null)
			{
				errorMessage+="Le champ département ne doit pas être vide! \n";
			}
			if(errorMessage.length()==0)
			{
				return true;
			}
			else
			{
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("Champs manquants");
				alert.setHeaderText("Completer les champs manquants");
				alert.setContentText(errorMessage);
				alert.showAndWait();
				return false;
			}
		}
		//Sauvegarder de données
		
		//Récupérer le chemin (path) des fichiers si ca existe
		
		public File getEpicerieFilePath()
		{
			Preferences prefs = Preferences.userNodeForPackage(Main.class);
			String filePath = prefs.get("filePath", null);
			
			if(filePath != null)
			{
				return new File(filePath);
			}
			else 
			{
				return null;
			}
		}
		
		//Attribuer un chemin de fichiers
		
		
		public void setEpicerieFilePath(File file)
		{
			Preferences prefs = Preferences.userNodeForPackage(Main.class);
		
			
			if(file != null)
			{
				prefs.put("filePath", file.getPath());
			}
			else 
			{
				prefs.remove("filePath");
			}
		}
		
		//Prendre les données de type XML et les convertir en données de type JavaFx
		
		public void loadEpicerieDataFromFile(File file)
		{
			try {
				JAXBContext context = JAXBContext.newInstance(EpicerieListeWrapper.class);
				Unmarshaller um = context.createUnmarshaller();
				
				EpicerieListeWrapper wrapper = (EpicerieListeWrapper) um.unmarshal(file);
				listeData.clear();
				listeData.addAll(wrapper.getliste());
				setEpicerieFilePath(file);
				
				// Donner le titre du fichier chargé
				Stage pStage=(Stage) ListeTable.getScene().getWindow();
				pStage.setTitle(file.getName());
			
				
			
			
			
			} 
			catch(Exception e) {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setHeaderText("les données n'ont pas été trouvées");
				alert.setTitle("Erreur");
				alert.setContentText("Les données ne pouvaient pas être trouvées dans le fichier : \n" +file.getPath());
				alert.showAndWait();
				
			}
		}
			
			//Prendre les données de type JaveFx et les convertir en données de type XML
			
			public void saveEpicerieDataToFile(File file)
			{
				try {
					JAXBContext context = JAXBContext.newInstance(EpicerieListeWrapper.class);
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					EpicerieListeWrapper wrapper = new EpicerieListeWrapper();
					wrapper.setliste(listeData);
					m.marshal(wrapper, file);
					
					//Sauvegarde dans le registre
					setEpicerieFilePath(file);
					// Donner le titre du fichier sauvegardé
					Stage pStage=(Stage) ListeTable.getScene().getWindow();
					pStage.setTitle(file.getName());
					
				} catch(Exception e) {
					
				
					Alert alert=new Alert(AlertType.ERROR);
					alert.setHeaderText("Données non sauvegardées");
					alert.setTitle("Erreur");
					alert.setContentText("Les données ne pouvaient pas être sauvegardées dans le fichier : \n" +file.getPath());
					alert.showAndWait();
					
				}
			}
			
			//Commencer un nouveau
			@FXML
			private void handlenew()
			{
				getlisteData().clear();
				setEpicerieFilePath(null);
			}
			
			
	//Le FileChooser  permet à l'usager de choisir le fichier à chercher
			@FXML
			private void handleOpen() {
				FileChooser fileChooser = new FileChooser();
				
				//Permettre un filtre sur l'extension du fichier à chercher
				FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
				fileChooser.getExtensionFilters().add(exFilter);
				
				// montrer le dialogue
				File file = fileChooser.showOpenDialog(null);
				
				if(file !=null) {
					loadEpicerieDataFromFile(file);
				}
			}
			
			//Sauvegarder le fichier correspondant à l'épicerie actif. S'il n'y a pas de fichier, le menu sauvegarder sous va s'affichier
			
			@FXML
			private void handleSave() {
				
				File etudiantFile = getEpicerieFilePath();
				if (etudiantFile != null) {
					saveEpicerieDataToFile(etudiantFile);
				}
				else {
					handleSaveAs();
				}
			}
			
			//Ouvrir le FileChooser pour trouver le chemin
			
			@FXML
			private void handleSaveAs() {
				FileChooser fileChooser = new FileChooser(); 
				
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)","*.xml");
				fileChooser.getExtensionFilters().add(extFilter);
				
				//Sauvegarder
				
				File file = fileChooser.showSaveDialog(null);
				
				if (file != null) {
					//Vérification de l'extension
					if(!file.getPath().endsWith(".xml")) {
						file = new File(file.getPath() + ".xml");
					}
					saveEpicerieDataToFile(file);
					
				}
				
			}
			
		
	 }

  	
  		
