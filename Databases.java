import java.util.ArrayList;
public interface Databases <E>
{
    public void addToDatabase(E val);
    public void removeFromDatabase(E val);
    public <E> E retrieveFromDatabase(String str);
    public <E> ArrayList <E> getDatabase();
}
