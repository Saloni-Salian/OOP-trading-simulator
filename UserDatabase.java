import java.util.*;
import java.io.*;
public class UserDatabase implements Databases <Portfolio>, Serializable
{
    private ArrayList <Portfolio> userdatabase;
    private File userFile;
    public UserDatabase()
    {
        this.userdatabase= new ArrayList <Portfolio>();
        this.userFile = new File("Users.txt");
    }

    public void addToDatabase(Portfolio port){
        userdatabase.add(port);
    }

    public void removeFromDatabase(Portfolio port){
        userdatabase.remove(port);
    }

    public Portfolio retrieveFromDatabase(String input){
        int i = 0;
        while(i<userdatabase.size())
        {
            Portfolio p = userdatabase.get(i);
            if(input.equalsIgnoreCase(p.getEmail()))
            {
                return p;
            }
            i++;
        }
        return null;
    }

    public boolean equalsEmail(String email)
    {
        int i = 0;
        while(i<userdatabase.size())
        {
            Portfolio p = userdatabase.get(i);
            if(email.equalsIgnoreCase(p.getEmail()))
                return true;
            i++;
        }
        return false;
    }

    public boolean equalsPassword(String password)
    {
        int i = 0;
        while(i<userdatabase.size())
        {
            Portfolio p = userdatabase.get(i);
            if(password.equalsIgnoreCase(p.getPassword()))
                return true;
            i++;
        }
        return false;
    }

    public ArrayList<Portfolio> getDatabase(){
        return userdatabase;
    }

    public void readPortfolioFile()
    {
        try {
            userFile.createNewFile();
        }
        catch (Exception e) {}

        // If the file is empty
        if (userFile.length() != 0) {

            try {
                // If file doesn't exist
                FileInputStream fis = new FileInputStream("Users.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);

                Portfolio user = null;

                while (fis.available() != 0) {
                    user = (Portfolio)ois.readObject();
                    userdatabase.add(user);
                }

                // Closing the connection to release memory resources
                ois.close();
                fis.close();
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                e.printStackTrace();
            }
        }

    }

    public void writePortfolioFile()
    {
        if (userdatabase != null) {
            try {
                // Initially assigning the object null to
                // avoid GC involvement
                FileOutputStream fos = null;
                fos = new FileOutputStream("Users.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(Portfolio p : userdatabase)
                    oos.writeObject(p);
                oos.close();
                fos.close();
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
            }
        }
    }

    public String printname(){
        String output = "Name";
        int i = 0;
        int count = 0;
        while(i<getDatabase().size())
        {
            Portfolio prod = getDatabase().get(i);
            count++;
            output = output + "\n" + count + ": " + prod.getFirstName();
            i++;
        }
        return output;
    }
}
