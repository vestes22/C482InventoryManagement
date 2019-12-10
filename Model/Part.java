package Model;

public abstract class Part 
{
    protected int id;
    protected String name;
    protected double price;
    protected int stock;
    protected int min;
    protected int max;
    static int partIdCounter = 10;
    
    public Part(int id, String name, double price, int stock, int min, int max)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    public Part(){};
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public void setStock(int stock)
    {
        this.stock = stock;
    }
    
    public void setMin(int min)
    {
        this.min = min;
    }
    
    public void setMax(int max)
    {
        this.max = max;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public int getStock()
    {
        return stock;
    }
    
    public int getMax()
    {
        return max;
    }
    
    public int getMin()
    {
        return min;
    }
    
    public static int partIdGenerator()
    {
        partIdCounter += 1;
        return partIdCounter;
    }    
}
