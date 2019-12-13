package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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

public class AddProductController implements Initializable
{
    Stage stage;
    Parent scene;
    
    private ObservableList<Part> searchedParts = FXCollections.observableArrayList();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    @FXML
    private TextField addProdIdText;

    @FXML
    private TextField addProdNameText;

    @FXML
    private TextField addProdInvText;

    @FXML
    private TextField addProdPriceText;

    @FXML
    private TextField addProdMinText;

    @FXML
    private TextField addProdMaxText;

    @FXML
    private TextField addProdSearchText;

    @FXML
    private TableView<Part> addProdTopTable;

    @FXML
    private TableColumn<Part, Integer> topAddProdIdCol;

    @FXML
    private TableColumn<Part, String> topAddProdNameCol;

    @FXML
    private TableColumn<Part, Integer> topAddProdInvCol;

    @FXML
    private TableColumn<Part, Double> topAddProdPriceCol;

    @FXML
    private TableView<Part> addProdBottomTable;

    @FXML
    private TableColumn<Part, Integer> bottomAddProdIdCol;

    @FXML
    private TableColumn<Part, String> bottomAddProdNameCol;

    @FXML
    private TableColumn<Part, Integer> bottomAddProdInvCol;

    @FXML
    private TableColumn<Part, Double> bottomAddProdPriceCol;
    
    @FXML
    private Label warningLabel;

    /*
    * Takes selected Part and associates it with the Product.
    */
    @FXML
    void addProdAddButtonClicked(MouseEvent event) 
    {
        warningLabel.setText("");
        if (addProdTopTable.getSelectionModel().getSelectedItem() == null)
        {
            warningLabel.setText("Please select a part to associate.");
        }
        Part addedPart = addProdTopTable.getSelectionModel().getSelectedItem();
        if (isPresent(addedPart.getId(), associatedParts))
        {
            warningLabel.setText("That part is already associated with this product.");
        }
        else 
        {
            associatedParts.add(addedPart);
        }
        addProdBottomTable.setItems(associatedParts);
        bottomAddProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomAddProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomAddProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomAddProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    /*
    * Takes the selected Part and dissociates it from the Product.
    */
    @FXML
    void addProdDeleteButtonClicked(MouseEvent event) 
    {
        warningLabel.setText("");
        int index = addProdBottomTable.getSelectionModel().getSelectedIndex();
        if (addProdBottomTable.getSelectionModel().getSelectedItem() == null)
        {
            warningLabel.setText("Please select a part to remove.");
        }
        associatedParts.remove(index);
        addProdBottomTable.setItems(associatedParts);
        bottomAddProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomAddProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomAddProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomAddProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /*
    * Asks the user if they want to exit without saving. Redirects to Main Screen.
    */
    @FXML
    void addProdCancelButtonClicked(MouseEvent event) throws Exception
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to exit without saving?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.isPresent() && answer.get() == ButtonType.OK)
        {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /*
    * Saves the Product information and adds Product to Inventory. Redirects to Main Screen.
    * Generates an alert box if entered information does not conform to constraints.
    */
    @FXML
    void addProdSaveButtonClicked(MouseEvent event) 
    {
        try
        {
            String name = addProdNameText.getText();
            int inv = Integer.parseInt(addProdInvText.getText());
            double price = Double.parseDouble(addProdPriceText.getText());
            int max = Integer.parseInt(addProdMaxText.getText());
            int min = Integer.parseInt(addProdMinText.getText());
            Product product = new Product(Product.productIdGenerator(), name, price, inv, min, max);
            for (Part part : associatedParts)
            {
                product.addAssociatedPart(part);
            }
            
            if(min > max)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Invalid Values");
                alert.setContentText("Min values cannot be greater than Max values.");
                alert.showAndWait();
            }
            
            else if (name.equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Empty Fields");
                alert.setContentText("All fields must be completed before saving.");
                alert.showAndWait();
            }
            
            else if (inv > max || inv < min)
            {
                Alert invAlert = new Alert(Alert.AlertType.WARNING);
                invAlert.setTitle("WARNING: Invalid Values");
                invAlert.setContentText("Inventory must be between Max and Min values.");
                invAlert.showAndWait();
            }
            
            else
            {
                Inventory.addProduct(product);
                associatedParts.clear();
        
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING: Empty Fields");
            alert.setContentText("All fields must be completed before saving.");
            alert.showAndWait();
        }    
    }
    
    /*
    * Allows user to search for a Part by both Name and ID. Displays results in TableView.
    */
    @FXML
    void addProdSearchButtonClicked(MouseEvent event)
    {
        searchedParts.clear();
        warningLabel.setText("");
        try
        {
            try
            {
                int id = Integer.parseInt(addProdSearchText.getText());
                searchedParts.add(Inventory.lookupPart(id));
            }
            catch(Exception e)
            {
                String name = addProdSearchText.getText();
                searchedParts = Inventory.lookupPart(name);
            }
            if (searchedParts.size() == 0)
            {
                warningLabel.setText("Sorry, we couldn't find that part.");
            }
            else
            {
                addProdTopTable.setItems(searchedParts);
                topAddProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                topAddProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                topAddProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                topAddProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        }
        catch(Exception e)
        {
            warningLabel.setText("Sorry, we couldn't find that part.");
        }
    }
    
    /*
    * Searches whether a Part exists based on ID.
    * Used in addProdAddButtonClicked() to prevent the same Part from being associated more than once.
    */
    public boolean isPresent(int id, ObservableList<Part> parts)
    {
       for (Part part : parts)
       {
           if (part.getId() == id)
           {
               return true;
           }
       }
       return false;
    }
    
    @Override 
    public void initialize(URL url, ResourceBundle rb)
    {
        addProdIdText.setDisable(true);
        addProdTopTable.setItems(Inventory.getAllParts());
        topAddProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        topAddProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        topAddProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topAddProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
}
