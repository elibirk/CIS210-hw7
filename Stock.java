package hw7_perry_leah;


	import java.util.Random;
	
public class Stock {
	private String name;//name of stock
	private String symbol;//symbol of stock
	private double currentPrice;//current price
	private double nextPrice;//next price
	private double priceChange = 0;//change in price
	private double priceChangePercentage = 0;//percentage that price changed
	private final double defaultPrice = 46.87; //default price of next and current
	private final double defaultval = 0;
	private final double minval = 0;
	private static int count;
	
	public Stock(String nam, String sym, double current, double next) {
	    name = nam;
	    symbol = sym;
	    if (current >= minval) {//if current is positive
			currentPrice = current;//prior to negatives to speed up program
		}//end if
		else {
			System.out.println("Prices cannot be negative, defaulting to 0.");
			currentPrice = defaultval;
		}//end else
	    
	    if (next >= minval) {//if next is positive
			nextPrice = next;//prior to negatives to speed up program
		}//end if
		else {
			System.out.println("Prices cannot be negative, defaulting to 0.");
			nextPrice = defaultval;
		}//end else
	    count++; //adds to total number of stocks
	}//end constructor w/ args
	public Stock() {
	    name = "Microsoft";
	    symbol = "MSFT";
	    currentPrice = defaultPrice;
	    nextPrice = defaultPrice;
	    count++; //adds to total number of stocks
	}//end default constructor
	
	//getters:
	public static int getCount(){//get number of stocks
		return count;
	}//end get count
	public String getName(){//get stock name
		return name;
	}//end get name
	public String getSymbol(){//get stock symbol
		return symbol;
	}//end get symbol
	public double getCurrentPrice(){//get stock price
		return currentPrice;
	}//end get current price
	public double getNextPrice(){//get stock's next price
		return nextPrice;
	}//end get next price
	public double getPriceChange(){//get change in stock price
		return priceChange;
	}//end get price change
	public double getPriceChangePercentage(){//get percentage of change
		return priceChangePercentage;
	}//end get price change percentage
	
	//setters:
	public void setName(String nam){//set stock name
		name = nam;
	}//end get name
	public void setSymbol(String sym){//set stock symbol
		symbol = sym;
	}//end get symbol
	public void setCurrentPrice(double current){//set stock's current price
		if (current >= minval) {//if current is positive
			currentPrice = current;//prior to negatives to speed up program
		}
		else {
			System.out.println("Prices cannot be negative, defaulting to 0.");
			currentPrice = defaultval;
		}
	}//end get current price
	public void setNextPrice(double next){//sets stock's next price
		if (next >= minval) {//if next is positive
			nextPrice = next;//prior to negatives to speed up program
		}
		else {
			System.out.println("Prices cannot be negative, defaulting to 0.");
			nextPrice = defaultval;
		}
	}//end get next price
	public void setPriceChange(double change){//set change in stock's price
		priceChange = change;
	}//end get price change
	public void setPriceChangePercentage(double percentage){//set change in stock price percentage
		priceChangePercentage = percentage;
	}//end get price change percentage
	
	
	//methods:
	public void SimulatePrice(){
		/* FUNCTION: SimulatePrice
		 * PURPOSE: Randomly increases/decreases currentPrice by a value from -10% to 10%
		 * @Parameter	currentPrice			where price starts
		 * 				r						random number
						random					calculates random number 0 to 10 to find a percentage
						sign					calculates 1 or 0 to determine sign of percentage
						priceChangePercentage	holds the percentage of change
						nextPrice				stores new price so difference can be found
						priceChange				stores difference between old and new price
						
		 */
		Random r = new Random(); //gets new random number
		int random = r.nextInt(11); //sets random as a random int 0 to 10
		int sign = r.nextInt(2);//gets random number 0 or 1 to determine sign
		
		currentPrice = nextPrice; //sets current price as next price so the program can run again
		
		if (sign == 0){//if random decides negative
			 priceChangePercentage = -random;
		}
		else {//if random decides positive
			priceChangePercentage = random;
		}
		nextPrice = currentPrice +currentPrice * (priceChangePercentage / 100);
		//above changes next price to current price plus or minus the  decimal percentage of change
		priceChange = nextPrice - currentPrice;//calculates change in price

	}//end simulate price
	
}//end class
