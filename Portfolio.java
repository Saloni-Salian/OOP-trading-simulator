import java.util.*;
import java.io.Serializable;
public class Portfolio extends ParentDatabasesOfProducts implements Serializable
{
    private int numStocks;
    private double funds;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;

    public Portfolio(String firstName, String lastName, int age, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.numStocks = getDatabase().size();
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public int getAge(){
        return this.age;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public int getNumStocks(){
        this.numStocks = getDatabase().size();
        return numStocks;
    }

    public double getFunds(){
        return this.funds;
    }

    public void deposit(double amount){
        this.funds+=amount;
    }

    public void withdraw(double amount){
        this.funds-=amount;
    }

}