import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
public class InvestmentApp extends Frame{
    private UserDatabase uDatabase;//database of all users
    private Portfolio user;// portfolio of the current user
    private LoginSystem log;
    private ProductDatabase pDatabase;// database of all products
    private static TextArea infoArea = new TextArea();
    private Button buyButton;
    private Button sellButton;
    private Button valuationButton;
    private Button bondreturnButton;
    private Button portfolioButton;
    private Button depositButton;
    private Button logoutButton;
    
    public static void print(String text){
        infoArea.setText(text);
    } 

    public static void printMenu(){
        infoArea.setText("Menu\n1. Buy product.\n2. Sell product.\n3. Check product valuation.\n4. Return on bond.\n5. Check Portfolio.\n6. Add funds\n7. Logout");
    }

    public InvestmentApp() throws IOException {
        super("Investment App");
        this.uDatabase = new UserDatabase();
        uDatabase.readPortfolioFile();
        this.pDatabase = new ProductDatabase();
        pDatabase.readProductFile();
        this.log= new LoginSystem();
        haveAccount();
    }

    public static void main(String args[]) throws IOException {
        InvestmentApp home = new InvestmentApp();
    }

    public String buy(){
        print(pDatabase.printDatabase());
        String product_name = InputStaticmethods.inputString("Enter the name of the product you want to buy.");
        Product prod = pDatabase.retrieveFromDatabase(product_name);
        String output ="";
        if(prod == null)
        {
            output ="There is no record of the product.";
        }
        else
        {
            int amount = InputStaticmethods.inputInteger("How many shares do you want to buy?");
            if(amount>prod.getNumprod())
            {
                output ="There is not enough available product.";
            }
            else if(this.user.getFunds()<prod.buyPrice(amount))
            {
                output ="There is not enough available funds.";
            }
            else
            {
                //check if stock is already bought, if yes then increase stock not add
                if(user.contains(prod))
                {
                    user.increaseProduct(prod,amount);
                }
                else
                {
                    Product newprod;
                    if(prod.getType() == 's')
                    {
                        Stock s = (Stock)prod;
                        Stock news = new Stock(s.getName(),s.getNumprod(),s.getCost(),s.getValue());
                        newprod = news;
                    }
                    else
                    {
                        Bonds b = (Bonds)prod;
                        Bonds newb = new Bonds(b.getName(),b.getNumprod(),b.getCost(),b.getValue(),b.getRate(), b.getYears());
                        newprod = newb;
                    }
                    user.addStock(newprod,amount);
                }
                user.withdraw(prod.buyPrice(amount));
                pDatabase.reduceProduct(prod,amount);
                output ="The shares have been bought";
            }
        }
        return output;
    }//method to buy products

    public String sell(){//tested
        String output = "";
        int check = user.getDatabase().size();//checks the size of the database in the user's portfolio
        if(check !=0)
        {
            print(user.printDatabase());
            String product_name = InputStaticmethods.inputString("Enter the name of the product you want to sell.");
            Product uProd = user.retrieveFromDatabase(product_name);
            Product dProd = pDatabase.retrieveFromDatabase(product_name);
            if(uProd == null)
            {
                output = "There is no record of the product in your portfolio.";
            }
            else
            {
                int amount = InputStaticmethods.inputInteger("How many shares do you want to sell?");
                if(amount>uProd.getNumprod())
                {
                    output = "There is not enough available product.";
                }
                else if (amount==uProd.getNumprod())
                {
                    user.removeFromDatabase(uProd);
                    user.deposit(uProd.sellPrice(amount));
                    pDatabase.increaseProduct(dProd,amount);
                    output = "The shares have been sold.";
                }
                else
                {
                    user.reduceProduct(uProd,amount);
                    user.deposit(uProd.sellPrice(amount));
                    pDatabase.increaseProduct(dProd,amount);
                    output = "The shares have been sold.";
                }
            }
        }
        else
            output = "You have no products in your portfolio.";
        return output;
    }//method to sell products

    public String productValuation(){
        String output = "";
        print(pDatabase.printDatabase());
        String product_name = InputStaticmethods.inputString("Enter the name of the product.");
        Product prod = pDatabase.retrieveFromDatabase(product_name);
        if(prod == null)
        {
            output = "There is no record of the product.";
        }
        else
        {
            String val = prod.getValuation();
            output ="The valuation of " + prod.getName() + " is " + val + ".";
        }
        return output;
    }//method to print the valuation of the product

    public String returnBond(){
        String output ="";
        print(pDatabase.printBond());
        String product_name = InputStaticmethods.inputString("Enter the name of the bond.");
        Product prod = pDatabase.retrieveFromDatabase(product_name);
        if(prod == null)
        {
            output = "There is no record of the product.";
        }
        else
        {
            if(prod.getType() == 'b')
            {
                Bonds b = (Bonds)prod;
                int amount = InputStaticmethods.inputInteger("Please enter how many bonds you want to check the return on.");
                output = "The value of " + b.getName() + " is " + b.valueOf(amount) + ".";
            }
            else
                output = "That is a stock not a bond.";
        }
        return output;
    }//method to print the interest that the bond will return

    public String checkPortfolio(){
        String output ="Your portfolio";
        output = output + "\nName: " + user.getFirstName() + " " + user.getLastName();
        output = output + "\nAge: " + user.getAge();
        output = output + "\nEmail: " + user.getEmail();
        output = output + "\nFunds: $" + user.getFunds();
        output = output + "\n" + user.printDatabase();
        return output;
    }//method to print the user's portfolio's details on the screen.

    public String addFunds(){
        double amount = InputStaticmethods.inputDouble("Enter how much money you wish to deposit.");
        user.deposit(amount);
        return "The funds have been deposited.";
    }//method to add funds to the user's portfolio.

    public void exit() throws IOException{
        print(uDatabase.printname());
        uDatabase.writePortfolioFile();
        pDatabase.writeProductFile();
        System.exit(0);
    }//saves the information in the two databases before exiting

    public void haveAccount() throws TooManyAttemptsException{
        String answer= InputStaticmethods.inputString("Do you have an account?[yes/no]");
        if(answer.equalsIgnoreCase("yes"))
        {
            user = log.login(InputStaticmethods.inputString("Enter your email"),InputStaticmethods.inputString("Enter your password"),uDatabase);
        }
        else if(answer.equalsIgnoreCase("no"))
        {
            user = log.login(uDatabase);
        }
        else
        {
            answer= InputStaticmethods.inputString("Try Again. Do you have an account?[yes/no]");
            int count = 1;
            while(answer.equalsIgnoreCase("yes")==false && answer.equalsIgnoreCase("no")==false){
                answer= InputStaticmethods.inputString("Try Again. Do you have an account?[yes/no]");
                count++;
                if(count>3)
                {
                    throw new TooManyAttemptsException("You have tried " + count + " times. That is too many failed attempts");
                }
            }
            user = log.login(InputStaticmethods.inputString("Enter your email"), InputStaticmethods.inputString("Enter your password"), uDatabase);
        }
        GUI();
    }

    public void GUI()
    {
        this.setLayout(new FlowLayout());

        this.buyButton = new Button("Buy");
        buyButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt) {      
                    print(buy());
                }
            });
        this.add(buyButton);

        this.sellButton = new Button("Sell");
        sellButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt) {      
                    print(sell());
                }
            });
        this.add(sellButton);

        this.valuationButton = new Button("Valuation");
        valuationButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt) {      
                    print(productValuation());
                }
            });
        this.add(valuationButton);

        this.bondreturnButton = new Button("Bond Return");
        bondreturnButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt) {      
                    print(returnBond());
                }
            });
        this.add(bondreturnButton);

        this.portfolioButton = new Button("Check Portfolio");
        portfolioButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt) {      
                    print(checkPortfolio());
                }
            });
        this.add(portfolioButton);

        this.depositButton = new Button("Deposit");
        depositButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt) {      
                    print(addFunds());
                }
            });
        this.add(depositButton);

        this.logoutButton = new Button("Exit");
        logoutButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt) {      
                    try
                    {
                        exit();
                    }
                    catch (IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                }
            });
        this.add(logoutButton);

        // Output console
        infoArea.setEditable(false);
        this.add(infoArea); 

        printMenu();

        WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc); 

        this.setSize(500,500);
        this.setLocationRelativeTo(null); // Centers the window on the screen
        this.setVisible(true);
    }
}