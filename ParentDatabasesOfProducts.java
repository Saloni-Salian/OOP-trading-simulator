import java.util.*;
import java.io.Serializable;

public class ParentDatabasesOfProducts implements Databases <Product>, Serializable
{
    private ArrayList <Product> totalProducts;
    public ParentDatabasesOfProducts()
    {
        this.totalProducts= new ArrayList <Product>();
    }

    public void addStock(Product prod, int numprod){
        prod.setNumprod(numprod);
        addToDatabase(prod);
    }

    public ArrayList<Product> getDatabase(){
        return this.totalProducts;
    }

    public void addToDatabase(Product prod){
        totalProducts.add(prod);
    }

    public void removeFromDatabase(Product prod){
        totalProducts.remove(prod);
    }

    public void reduceProduct(Product prod, int numprod){
        Product changeprod = retrieveFromDatabase(prod.getName());//gets the reference of the needed product
        if(numprod<=prod.getNumprod())
        {
            int newNumProd=changeprod.getNumprod()-numprod;
            changeprod.setNumprod(newNumProd);//sets the new reference value
        }
        else 
        {
            System.out.println("There are not enough shares");
        }
    }

    public void increaseProduct(Product prod, int numprod){
        Product changeprod = retrieveFromDatabase(prod.getName());//gets the reference of the needed product
        int newNumProd=changeprod.getNumprod()+numprod;
        changeprod.setNumprod(newNumProd);//sets the new reference value
    }

    public Product retrieveFromDatabase(String name){
        int i = 0;
        while(i<totalProducts.size())
        {
            Product prod = totalProducts.get(i);
            if(name.equalsIgnoreCase(prod.getName()))
            {
                return prod;
            }
            i++;
        }
        return null;
    }

    public boolean contains(Product prod){
        int i = 0;
        while(i<totalProducts.size())
        {
            Product productfromdatabase = totalProducts.get(i);
            String name = prod.getName();
            if(name.equalsIgnoreCase(productfromdatabase.getName()))
            {
                return true;
            }
            i++;
        }
        return false;
    }//checks if the database contains the product

    public String printDatabase(){
        String output = "Products";
        int i = 0;
        while(i<totalProducts.size())
        {
            int count = i+1;
            output = output + "\n" + count + ": " + totalProducts.get(i).getName() + " -" + totalProducts.get(i).getNumprod();
            i=count;
        }
        return output;
    }
}
