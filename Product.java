import java.io.Serializable;
public class Product implements Serializable
{
    private String name;//name of the product
    private int numprod;//number of shares
    private double cost;//cost to buy 1 share
    private double value;//cost that 1 share sells
    private char type;//type of product
    public Product(String name, int numprod, double cost, double value, char type){
        this.name=name;
        this.numprod=numprod;
        this.cost=cost;
        this.value=value;
        this.type=type;
    }
    public double sellPrice(int amount){
        return this.value*amount;
    }//the value of the product
    
    public double buyPrice(int amount){
        return this.cost*amount;
    }//the cost of the product
    
    public void reduce(int amount){
        numprod-=amount;
    }//if a user buys the stock the numStock decreases
    
    public void increase(int amount){
        numprod+=amount;
    }//if a user sells the stock the numStock increases

    public double getValue(){
        return this.value;
    }
    
    public double getCost(){
        return this.cost;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getNumprod(){
        return this.numprod;
    }
    
    public char getType(){
        return this.type;
    }//return a character which indicates whether the product is a stock or a bond
    
    public void setNumprod(int numprod){
        this.numprod=numprod;
    }
    
    public String getValuation(){
        return "Generic product value";
    }//Overriden in bonds and stock
}
