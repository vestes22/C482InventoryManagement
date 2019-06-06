package c482project0;

public class Outsourced extends Parts
{
    private String companyName;
    
    public void Outsourced
    (
        int partId, 
        String partName, 
        int partInventory, 
        double partPrice, 
        int partMin, 
        int partMax, 
        String companyName
    )
    {
        this.partId = partId;
        this.partName = partName;
        this.partInventory = partInventory;
        this.partPrice = partPrice;
        this.partMin = partMin;
        this.partMax = partMax;
        this.companyName = companyName;
    }
    
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    public String getCompanyName()
    {
        return companyName;
    }
}
