import java.util.*;
public class LoginSystem
{
    public Portfolio login(String email, String password, UserDatabase database) throws TooManyAttemptsException{
        //Uses if statement to check if email is in the database. If not ask to reinput email or ends programs with too many attempts exception
        Portfolio p;
        if (database.equalsEmail(email)!=true || database.equalsPassword(password)!=true)
        {
            email = InputStaticmethods.inputString("Either your email or password was wrong, re-input your email.");
            password = InputStaticmethods.inputString("Either your email or password was wrong, re-input your password.");
            int count=1;
            while(database.equalsEmail(email)!=true || database.equalsPassword(password)!=true)
            {
                count++;
                email= InputStaticmethods.inputString("Either your email or password was wrong, re-input your email. Trial: " + count);
                password = InputStaticmethods.inputString("Either your email or password was wrong, re-input your email. Trial: " + count);
                if(count>2)
                {
                    throw new TooManyAttemptsException("You have tried " + count + " times. That is too many failed attempts");
                }
            }
            p = database.retrieveFromDatabase(email);
        }
        else
            p = database.retrieveFromDatabase(email);
        return p;
    }

    public Portfolio login(UserDatabase database) throws TooYoungException{
        String firstName = InputStaticmethods.inputString("Please enter your first name.");
        String lastName = InputStaticmethods.inputString("Please enter your last name.");
        int age = InputStaticmethods.inputInteger("Please enter your age.");
        if(age<18) //if the user is less than 18 years old, they can't use the app
        {
            throw new TooYoungException();
        }
        String email = InputStaticmethods.inputString("Please enter your email adress.");
        String password = InputStaticmethods.inputString("Please enter a password.");
        Portfolio p = new Portfolio(firstName, lastName, age, email, password);
        double money = InputStaticmethods.inputDouble("Please enter how much money you wish to deposit.");
        p.deposit(money);
        database.addToDatabase(p);
        return p;
    }
}
