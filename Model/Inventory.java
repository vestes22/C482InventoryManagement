package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory 
{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Product> searchedProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> searchedParts = FXCollections.observableArrayList();
    
    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct)
    {
       allProducts.add(newProduct);
    }
    
    /*
    * Searches Parts by Part ID.
    */
    public static Part lookupPart(int partId)
    {
        int index = -1;
        for (int i = 0; i < allParts.size(); i++)
        {
            if (allParts.get(i).getId() == partId)
            {
                index = i;
            }
        }
        return allParts.get(index);
    }
    
    /*
    * Searches Products by Product ID.
    */
    public static Product lookupProduct(int productId)
    {
        int index = -1;
        for (int i = 0; i < allProducts.size(); i++)
        {
            if (allProducts.get(i).getId() == productId)
            {
                index = i;
            }
        }
        return allProducts.get(index);
    }
    
    /*
    * Searches Parts by Part Name.
    */
    public static ObservableList<Part> lookupPart(String partName)
    {
        searchedParts.clear();
        
        for (int i = 0; i < allParts.size(); i++)
        {
            if (allParts.get(i).getName().equals(partName))
            {
                searchedParts.add(allParts.get(i));
            }
        }
        return searchedParts;
    }
    
    /*
    * Searches Products by Product Name.
    */
    public static ObservableList<Product> lookupProduct(String productName)
    {
        searchedProducts.clear();
        
        for (int i = 0; i < allProducts.size(); i++)
        {
            if (allProducts.get(i).getName().equals(productName))
            {
                searchedProducts.add(allProducts.get(i));
            }
        }
        return searchedProducts;
    }
    
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }
    
    public static void updateProduct(int index, Product selectedProduct)
    {
        allProducts.set(index, selectedProduct);
    }
    
    /*
    * Deletes a Part from Inventory.
    */
    public static boolean deletePart(Part selectedPart)
    {
        if(allParts.remove(selectedPart))
        {
            return true;
        }
        
        return false;
    }
    
    /*
    Deletes a Product from Inventory.
    */
    public static boolean deleteProduct(Product selectedProduct)
    {
        if(allProducts.remove(selectedProduct))
        {
            return true;
        }
        return false;
    }
    
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }
}

