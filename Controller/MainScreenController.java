package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private Label warningLabel;
    
    @FXML 
    private Label prodWarningLabel;

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
    
    
    /*
    * Redirects to Add Part screen.
    */
    @FXML
    void addPartButton(MouseEvent event) throws Exception
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddPartFXML.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /*
    * Redirects to Add Product screen.
    */
    @FXML
    void addProdButton(MouseEvent event) throws Exception
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddProductFXML.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /*
    * Asks user if they want to delete the selected Part.
    * Removes Part from Inventory and redisplays TableView.
    */
    @FXML
    void deletePartButton(MouseEvent event) 
    {
        warningLabel.setText("");
        if(partTable.getSelectionModel().getSelectedItem() == null)
        {
            warningLabel.setText("Please select part to delete.");
        }
        
        if(partTable.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete this part?");
            Optional<ButtonType> answer = alert.showAndWait();

            if(answer.isPresent() && answer.get() == ButtonType.OK)
            {
                boolean delete = Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());

                if (delete == true)
                {
                    partTable.setItems(Inventory.getAllParts());
                    warningLabel.setText("Part deleted.");
                }
            }
        }
    }

    /*
    * Asks user if they want to delete the selected Product.
    * Removes Product from Inventory and redisplays TableView.
    */
    @FXML
    void deleteProdButton(MouseEvent event) 
    {
        prodWarningLabel.setText("");
        if(prodTable.getSelectionModel().getSelectedItem() == null)
        {
            prodWarningLabel.setText("Please select product to delete.");
        }
        
        if(prodTable.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete this product?");
            Optional<ButtonType> answer = alert.showAndWait();

            if(answer.isPresent() && answer.get() == ButtonType.OK)
            {
                boolean delete = Inventory.deleteProduct(prodTable.getSelectionModel().getSelectedItem());

                if (delete == true)
                {
                    prodTable.setItems(Inventory.getAllProducts());
                    prodWarningLabel.setText("Product deleted.");
                }
            }
        }
    }
    
    /*
    * Redirects user to the Modify Part screen.
    */
    @FXML
    void modifyPartButton(MouseEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ModifyPartFXML.fxml"));
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

    /*
    * Redirects user to the Modify Product screen.
    */
    @FXML
    void modifyProdButton(MouseEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ModifyProductFXML.fxml"));
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

    /*
    * Allows user to search for a Part based on Part Name and Part ID.
    * Displays results in the TableView.
    */
    @FXML
    void searchPartButton(MouseEvent event) 
    {
        searchedParts.clear();
        warningLabel.setText("");
        try
        {
            try
            {
                int id = Integer.parseInt(partSearchText.getText());
                searchedParts.add(Inventory.lookupPart(id));
            }
            catch(Exception e)
            {
                String name = partSearchText.getText();
                searchedParts = Inventory.lookupPart(name);
            }
            if (searchedParts.size() == 0)
            {
                warningLabel.setText("Sorry, we couldn't find that part.");
            }
            else
            {
                partTable.setItems(searchedParts);
                partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        }
        catch(Exception e)
        {
            warningLabel.setText("Sorry, we couldn't find that part.");
        }
    }

    /*
    * Allows user to search for a Product based on Product Name and Product ID.
    * Displays results in the TableView.
    */
    @FXML
    void searchProdButton(MouseEvent event) 
    {
        searchedProducts.clear();
        prodWarningLabel.setText("");
        try
        {
            try
            {
                int id = Integer.parseInt(productSearchText.getText());
                searchedProducts.add(Inventory.lookupProduct(id));
            }
            catch(Exception e)
            {
                String name = productSearchText.getText();
                searchedProducts = Inventory.lookupProduct(name);
            }
            if (searchedProducts.size() == 0)
            {
                prodWarningLabel.setText("Sorry, we couldn't find that product.");
            }
            else
            {
                prodTable.setItems(searchedProducts);
                prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        }
        catch(Exception e)
        {
            warningLabel.setText("Sorry, we couldn't find that part.");
        }
    }
    
    /*
    * Closes the program.
    */
    @FXML
    void exit(MouseEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to exit the program?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.isPresent() && answer.get() == ButtonType.OK)
        {
            Platform.exit();
        }
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
