package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.ResourceBundle;
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
    private Button addProdSearchButton;

    @FXML
    private Button addProdAddButton;

    @FXML
    private Button addProdDeleteButton;

    @FXML
    private Button addProdCancelButton;

    @FXML
    private Button addProdSaveButton;

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
    
    @FXML 
    private Label productWarningLabel;
    
    

    @FXML
    void addProdAddButtonClicked(MouseEvent event) 
    {
        productWarningLabel.setText("");
        Part addedPart = addProdTopTable.getSelectionModel().getSelectedItem();
        if (isPresent(addedPart.getId(), associatedParts))
        {
            productWarningLabel.setText("That part is already associated with this product.");
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

    //Searches whether a Part exists based on ID.
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
    
    @FXML
    void addProdCancelButtonClicked(MouseEvent event) throws Exception
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void addProdDeleteButtonClicked(MouseEvent event) 
    {
        int index = addProdBottomTable.getSelectionModel().getSelectedIndex();
        associatedParts.remove(index);
        addProdBottomTable.setItems(associatedParts);
        bottomAddProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomAddProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomAddProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomAddProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

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
            Product product = new Product(Product.productIdGenerator(), name, price, inv, max, min);
            for (Part part : associatedParts)
            {
                product.addAssociatedPart(part);
            }
            
            if(min > max)
            {
                productWarningLabel.setText("*Min values cannot be greater than Max values.");
            }
            
            else if (name.equals(""))
            {
                productWarningLabel.setText("*All fields must be completed before saving.");
            }
            
            else if (inv > max || inv < min)
            {
                productWarningLabel.setText("*Inventory must be between Max and Min values.");
            }
            
            else
            {
                Inventory.addProduct(product);
                associatedParts.clear();
        
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch (Exception e)
        {
            productWarningLabel.setText("*All fields must be completed before saving.");
        }    
    }

    @FXML
    void addProdSearchButtonClicked(MouseEvent event)
    {
        searchedParts.clear();
        warningLabel.setText("");
        try
        {
            int id = Integer.parseInt(addProdSearchText.getText());
            searchedParts.add(Inventory.lookupPart(id));
            addProdTopTable.setItems(searchedParts);
            topAddProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            topAddProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            topAddProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            topAddProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        }
        catch(Exception e)
        {
            warningLabel.setText("Sorry, we couldn't find that part.");
        }
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
