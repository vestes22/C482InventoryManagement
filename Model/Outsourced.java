package Model;

public class Outsourced extends Part
{
    private String companyName;
    
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
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
