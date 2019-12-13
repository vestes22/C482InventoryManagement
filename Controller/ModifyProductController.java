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

public class ModifyProductController implements Initializable
{
    Stage stage;
    Parent scene;
    private ObservableList<Part> searchedParts = FXCollections.observableArrayList();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    @FXML
    private TextField modifyProdIdText;

    @FXML
    private TextField modifyProdNameText;

    @FXML
    private TextField modifyProdInvText;

    @FXML
    private TextField modifyProdPriceText;

    @FXML
    private TextField modifyProdMinText;

    @FXML
    private TextField modifyProdMaxText;

    @FXML
    private TextField modifyProdSearchText;

    @FXML
    private TableView<Part> modifyProdTopTable;

    @FXML
    private TableColumn<Part, Integer> topModifyProdIdCol;

    @FXML
    private TableColumn<Part, String> topModifyProdNameCol;

    @FXML
    private TableColumn<Part, Integer> topModifyProdInvCol;

    @FXML
    private TableColumn<Part, Double> topModifyProdPriceCol;

    @FXML
    private TableView<Part> modifyProdBottomTable;

    @FXML
    private TableColumn<Part, Integer> bottomModifyProdIdCol;

    @FXML
    private TableColumn<Part, String> bottomModifyProdNameCol;

    @FXML
    private TableColumn<Part, Integer> bottomModifyProdInvCol;

    @FXML
    private TableColumn<Part, Double> bottomModifyProdPriceCol;
    
    @FXML
    private Label warningLabel;

    /*
    * Associates the selected Part to the Product being modified.
    */
    @FXML
    void modifyProdAddButtonClicked(MouseEvent event) 
    {
        warningLabel.setText("");
        if (modifyProdTopTable.getSelectionModel().getSelectedItem() == null)
        {
            warningLabel.setText("Please select a part to associate.");
        }
        Part addedPart = modifyProdTopTable.getSelectionModel().getSelectedItem();
        if (isPresent(addedPart.getId(), associatedParts))
        {
            warningLabel.setText("That part is already associated with this product.");
        }
        else 
        {
            associatedParts.add(addedPart);
        }
        modifyProdBottomTable.setItems(associatedParts);
        bottomModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /*
    * Dissociates the selected Part from the Product being modified.
    */
    @FXML
    void modifyProdDeleteButtonClicked(MouseEvent event) 
    {
        warningLabel.setText("");
        int index = modifyProdBottomTable.getSelectionModel().getSelectedIndex();
        if (modifyProdBottomTable.getSelectionModel().getSelectedItem() == null)
        {
            warningLabel.setText("Please select a part to remove.");
        }
        associatedParts.remove(index);
        modifyProdBottomTable.setItems(associatedParts);
        bottomModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    /*
    * Asks user if they want to exit without saving.
    * Redirects to the Main Screen.
    */
    @FXML
    void modifyProdCancelButtonClicked(MouseEvent event) throws Exception 
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
    * Saves the changes to the Product being modified and updates the Product in Inventory.
    * Redirects to the Main Screen.
    */
    @FXML
    void modifyProdSaveButtonClicked(MouseEvent event) 
    {
        try 
        {
            int index = -1;
            Product newProduct;
            int id = Integer.parseInt(modifyProdIdText.getText());
            String name = modifyProdNameText.getText();
            int inv = Integer.parseInt(modifyProdInvText.getText());
            double price = Double.parseDouble(modifyProdPriceText.getText());
            int max = Integer.parseInt(modifyProdMaxText.getText());
            int min = Integer.parseInt(modifyProdMinText.getText());
        
            //Finds the ObservableArray index of the part being modified by matching the Part ID, so we can replace it.
            for (int i = 0; i < Inventory.getAllProducts().size(); i++)
            {
                if (Inventory.getAllProducts().get(i).getId() == id)
                {
                    index = i;
                }
            }
        
            newProduct = new Product(id, name, price, inv, min, max);
        
            for (Part part : associatedParts)
            {
                newProduct.addAssociatedPart(part);
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
                Inventory.updateProduct(index, newProduct);
                associatedParts.clear();
          
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING: Empty Fields");
            alert.setContentText("All fields must be completed before saving.");
            alert.showAndWait();
        }      
    }

    /*
    * Allows user to search Parts by both Part Name and Part ID.
    * Displays the results in the TableView.
    */
    @FXML
    void modifyProdSearchButtonClicked(MouseEvent event) 
    {
        searchedParts.clear();
        warningLabel.setText("");
        try
        {
            try
            {
                int id = Integer.parseInt(modifyProdSearchText.getText());
                searchedParts.add(Inventory.lookupPart(id));
            }
            catch(Exception e)
            {
                String name = modifyProdSearchText.getText();
                searchedParts = Inventory.lookupPart(name);
            }
            if (searchedParts.size() == 0)
            {
                warningLabel.setText("Sorry, we couldn't find that part.");
            }
            else
            {
                modifyProdTopTable.setItems(searchedParts);
                topModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                topModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                topModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                topModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        }
        catch(Exception e)
        {
            warningLabel.setText("Sorry, we couldn't find that part.");
        }
    }
    
    /*
    * Allows us to use an instance of ModifyProductController to pass information from the Main Screen to the Modify Product screen.
    * Allows us to parse and pre-fill data for the Product to be modified.
    */
    public void selectModifiedProduct(Product product)
    {
        modifyProdIdText.setText(String.valueOf(product.getId()));
        modifyProdNameText.setText(product.getName());
        modifyProdInvText.setText(String.valueOf(product.getStock()));
        modifyProdPriceText.setText(String.valueOf(product.getPrice()));
        modifyProdMaxText.setText(String.valueOf(product.getMax()));
        modifyProdMinText.setText(String.valueOf(product.getMin()));
        
        for (Part part : product.getAllAssociatedParts())
        {
            associatedParts.add(part);
        }
        
        modifyProdBottomTable.setItems(associatedParts);
        bottomModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    /*
    * Searches whether a Part exists based on ID.
    * Used in modifyProdAddButtonClicked() to prevent the same Part from being associated more than once.
    */
    private boolean isPresent(int id, ObservableList<Part> parts)
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
        modifyProdIdText.setDisable(true);
        modifyProdTopTable.setItems(Inventory.getAllParts());
        topModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        topModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        topModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
