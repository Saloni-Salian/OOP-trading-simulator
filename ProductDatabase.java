import java.util.*;
import java.io.*;
public class ProductDatabase extends ParentDatabasesOfProducts implements Serializable
{
    private File productFile;
    public ProductDatabase()
    {
        productFile = new File("Products.txt");
    }

    public String printBond(){
        String output = "Bonds";
        int i = 0;
        int count = 0;
        while(i<getDatabase().size())
        {
            Product prod = getDatabase().get(i);
            if(prod.getType() == 'b')
            {
                count++;
                output = output + "\n" + count + ": " + prod.getName();
            }
            i++;
        }
        return output;
    }

    public void readProductFile()
    {
        try {
            productFile.createNewFile();
        }
        catch (Exception e) {
        }

        // If the file is empty
        if (productFile.length() != 0) {

            try {
                // If file doesn't exist
                FileInputStream fis = new FileInputStream("Products.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);

                Product prod = null;

                while (fis.available() != 0) {
                    prod = (Product)ois.readObject();
                    getDatabase().add(prod);
                }

                // Closing the connection to release memory
                // resources using close() method
                ois.close();
                fis.close();
            }

            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                System.out.println("Changes made in previous and");
            }
        }

    }

    public void writeProductFile()
    {
        if (getDatabase() != null) {
            try {
                // Initially assigning the object null to
                // avoid GC involvement
                FileOutputStream fos = null;
                fos = new FileOutputStream("Products.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                for(Product p : getDatabase())
                    oos.writeObject(p);

                oos.close();
                fos.close();
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
            }
        }
    }
}
