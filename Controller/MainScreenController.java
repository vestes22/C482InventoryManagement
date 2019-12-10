package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainScreenController implements Initializable{

    Stage stage;
    Parent scene;
    private ObservableList<Part> searchedParts = FXCollections.observableArrayList();
    private ObservableList<Product> searchedProducts = FXCollections.observableArrayList();
    
    @FXML
    private Button exitButton;

    @FXML
    private Label label;
    
    @FXML 
    private Label warningLabel;
    
    @FXML 
    private Label prodWarningLabel;

    @FXML
    private Button partSearchButton;

    @FXML
    private Button productSearchButton;

    @FXML
    private Button partAddButton;

    @FXML
    private Button partModifyButton;

    @FXML
    private Button partDeleteButton;

    @FXML
    private Button prodAddButton;

    @FXML
    private Button prodModifyButton;

    @FXML
    private Button prodDeleteButton;
    
    @FXML
    private TextField partSearchText;

    @FXML
    private TextField productSearchText;
    
    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;
    
    @FXML
    private TableView<Product> prodTable;

    @FXML
    private TableColumn<Product, Integer> prodIDCol;

    @FXML
    private TableColumn<Product, String> prodNameCol;

    @FXML
    private TableColumn<Product, Integer> prodInvCol;

    @FXML
    private TableColumn<Product, Double> prodPriceCol;
    
    
    //Changes scene to the Add Part screen.
    @FXML
    void addPartButton(MouseEvent event) throws Exception
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartFXML.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    //Changes scene to the Add Product screen.
    @FXML
    void addProdButton(MouseEvent event) throws Exception
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductFXML.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    //Deletes the selected part.
    @FXML
    void deletePartButton(MouseEvent event) 
    {
        boolean delete = Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
        
        if (delete == true)
        {
            partTable.setItems(Inventory.getAllParts());
            warningLabel.setText("Part deleted.");
        }
        else
        {
            warningLabel.setText("Please select part to delete.");
        }

    }

    //Deletes the selected product.
    @FXML
    void deleteProdButton(MouseEvent event) 
    {
        boolean delete = Inventory.deleteProduct(prodTable.getSelectionModel().getSelectedItem());
        
        if (delete == true)
        {
            prodTable.setItems(Inventory.getAllProducts());
            prodWarningLabel.setText("Product deleted.");
        }
        else
        {
            prodWarningLabel.setText("Please select a product to delete.");
        }
    }
    
    //Changes scene to the Modify Part screen.
    @FXML
    void modifyPartButton(MouseEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPartFXML.fxml"));
        loader.load();
        ModifyPartController modifyController = loader.getController();
        try
        {
            modifyController.selectModifiedPart(partTable.getSelectionModel().getSelectedItem());
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(Exception exception)
        {
            warningLabel.setText("Please select a part to modify.");
        }
        
    }

    @FXML
    void modifyProdButton(MouseEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyProductFXML.fxml"));
        loader.load();
        ModifyProductController modifyProdController = loader.getController();
        try
        {
            modifyProdController.selectModifiedProduct(prodTable.getSelectionModel().getSelectedItem());
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(Exception e)
        {
            prodWarningLabel.setText("Please select a product to modify.");
        }
    }

    //Searches the Part Table.
    @FXML
    void searchPartButton(MouseEvent event) 
    {
        searchedParts.clear();
        try
        {
            int id = Integer.parseInt(partSearchText.getText());
            searchedParts.add(Inventory.lookupPart(id));
            partTable.setItems(searchedParts);
            partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        }
        catch(Exception e)
        {
            warningLabel.setText("Sorry, we couldn't find that part.");
        }
    }

    @FXML
    void searchProdButton(MouseEvent event) 
    {
        searchedProducts.clear();
        try
        {
            prodWarningLabel.setText("");
            int id = Integer.parseInt(productSearchText.getText());
            searchedProducts.add(Inventory.lookupProduct(id));
            prodTable.setItems(searchedProducts);
            prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        }
        catch(Exception e)
        {
            prodWarningLabel.setText("Sorry, we couldn't find that product.");
        }
    }
    
    @FXML
    void exit(MouseEvent event) 
    {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        partTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        prodTable.setItems(Inventory.getAllProducts());
        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}