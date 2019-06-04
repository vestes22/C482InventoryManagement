package c482project0;

import javafx.collections.ObservableList;

public class Products extends Inventory
{
    private int productId;
    private String productName;
    private int productInventory;
    private double productPrice;
    private int productMax;
    private int productMin;
    private ObservableList<Parts> associatedParts;
    
    public Products()
    {
        this.productId = 0;
        this.productName = "";
        this.productInventory = 0;
        this.productPrice = 0;
        this.productMax = 0;
        this.productMin = 0;
    }
    
    public Products(int productId, String productName, int productInventory, double productPrice, int productMax, int productMin)
    {
        this.productId = productId;
        this.productName = productName;
        this.productInventory = productInventory;
        this.productPrice = productPrice;
        this.productMax = productMax;
        this.productMin = productMin;
    }
    
    //Setters
    public void setProductId(int productId)
    {
        this.productId = productId;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    
    public void setProductInventory(int productInventory)
    {
        this.productInventory = productInventory;
    }
    
    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }
    
    public void setProductMax(int productMax)
    {
        this.productMax = productMax;
    }
    
    public void setProductMin(int productMin)
    {
        this.productMin = productMin;
    }
    
    //Getters
    public int getProductId()
    {
        return productId;
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public int getProductInventory()
    {
        return productInventory;
    }
    
    public double getProductPrice()
    {
        return productPrice;
    }
    
    public int getProductMax()
    {
        return productMax;
    }
    
    public int getProductMin()
    {
        return productMin;
    }
    
    //Additional methods
    public void addAssociatedPart(Parts part)                        //FIXME: Are these to be declared public? Private? Protected? Research!
    {
        //FIXME
    }
    
    public void deleteAssociatedPart(Parts part)
    {
        //FIXME
    }
    
    /*
    public ObservableList<Parts> getAllAssociatedParts()
    {
        ObservableList<Parts> associatedParts = new List();
        return associatedParts;
    }
*/
    
}
