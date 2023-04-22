public class Bonds extends Product
{
    private double rate;
    private int years;
    private String valuation;
    private double interest;
    public Bonds(String name, int numprod, double cost, double value, double rate, int years)
    {
        super(name,numprod,cost,value,'b');
        this.rate=rate;
        this.years=years;
        this.interest=this.rate*this.years;
    }

    public double getRate(){
        return this.rate;
    }
    
    public int getYears(){
        return this.years;
    }
    
    public double valueOf(int amount){
        double value=getValue();
        double principle = buyPrice(amount);
        return principle*(1+(rate*years));
    }//return the simple interest generated on the amount

    public String getValuation(){
        if(interest>0.15)
        {
            valuation="very good";
        }
        else if (interest>0.1)
        {
            valuation = "good";
        }
        else if (interest > 0.05)
        {
            valuation= "fine";
        }
        else 
            valuation = "poor";
        return this.valuation;
    }//calculates the valuation based on the interest.
}
