package c482project0;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory 
{
    private ObservableList<Parts> allParts;
    private ObservableList<Products> allProducts;
    
    
    public void addPart()                 
    {
       Parts part = new InHouse(); 
       part.setPartId(Integer.parseInt(partIdText.getText()));
       part.setPartName(partNameText.getText());
       part.setPartInv(Integer.parseInt(partInvText.getText()));
       part.setPartPrice(Double.parseDouble(partPriceText.getText()));
       part.setPartMax(Integer.parseInt(partMaxText.getText()));
       part.setPartMin(Integer.parseInt(partMinText.getText()));
       part.setPartMachineId(Integer.parseInt(partMachineText.getText()));
       allParts.getItems().add(part);
       partIdText.clear();
       partNameText.clear();
       partInvText.clear();
       partPriceText.clear();
       partMaxText.clear();
       partMinText.clear();
       partMachineText.clear();
    }
    
    /*
    public void addProduct(Products product)
    {
        //FIXME
    }
    */
    /*
    public Parts searchParts(int partId)
    {
        //FIXME
    }
    */
    /*
    public Products searchProducts(int productId)
    {
        //FIXME
    }
    */
    /*
    public ObservableList<Parts> searchPart(String partName)
    {
        //FIXME
    }
    */
    /*
    public ObservableList<Products> searchProduct(String productName)
    {
        //FIXME
    }
    */
    /*
    public void updatePart(int index, Parts part)
    {
        //FIXME
    }
    */
    /*
    public void updateProduct(int index, Products product)
    {
        //FIXME
    }
    */
    /*
    public void deletePart(Parts part)
    {
        //FIXME
    }
    */
    /*
    public void deleteProduct(Products product)
    {
        //FIXME
    }
    */
    
      
   
        
    public ObservableList<Parts> getAllParts()
    {
        ObservableList<Parts> allParts = FXCollections.observableArrayList();
        return allParts;
    }
    
    
    public ObservableList<Products> getAllProducts()
    {
        return allProducts;
    }
    
}
