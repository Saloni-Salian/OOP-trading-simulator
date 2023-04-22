public class Stock extends Product
{
    private String valuation;
    public Stock(String name, int stocks, double cost, double value){
        super(name,stocks,cost,value, 's');
    }
    
    public String getValuation(){
        double buy = buyPrice(1);
        double sell = sellPrice(1);
        double difference = sell-buy;
        if(difference>3.0)
        {
            valuation="very good";
        }
        else if (difference>0.0)
        {
            valuation = "good";
        }
        else if (difference == 0.0)
        {
            valuation= "fine";
        }
        else 
            valuation = "poor";
        return this.valuation;
    }//calculates the valuation based on the difference between the buying and selling price.
}
