package c482project0;

public abstract class Parts extends Inventory 
{
    protected int partId;
    protected String partName;
    protected int partInventory;
    protected double partPrice;
    protected int partMax;
    protected int partMin;
    
    public Parts
    (
        int partId, 
        String partName, 
        int partInventory, 
        double partPrice, 
        int partMax, 
        int partMin
    )
    {
        this.partId = partId;
        this.partName = partName;
        this.partInventory = partInventory;
        this.partPrice = partPrice;
        this.partMax = partMax;
        this.partMin = partMin;
    }
    
    //The Setters
    public void setPartId(int partId)
    {
        this.partId = partId;
    }
    
    public void setPartName(String partName)
    {
        this.partName = partName;
    }
    
    public void setPartInventory(int partInventory)
    {
        this.partInventory = partInventory;
    }
    
    public void setPartPrice(double partPrice)
    {
        this.partPrice = partPrice;
    }
    
    public void setPartMax(int partMax)
    {
        this.partMax = partMax;
    }
    
    public void setPartMin(int partMin)
    {
        this.partMin = partMin;
    }
    
    //The Getters
    
    public int getPartId()
    {
        return partId;
    }
    
    public String getPartName()
    {
        return partName;
    }
    
    public int getPartInventory()
    {
        return partInventory;
    }
    
    public double getPartPrice()
    {
        return partPrice;
    }
    
    public int getPartMax()
    {
        return partMax;
    }
    
    public int getPartMin()
    {
        return partMin;
    }
}
