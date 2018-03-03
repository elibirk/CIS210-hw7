package hw7_perry_leah;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;



public class StockList {
	/*PROGRAM: StockPriceSimulator / Assignment 7 
	 * AUTHOR: Leah Perry
	 * Due Date: October 29, 2015
	 * SUMMARY: Finds sum, average, highest, and lowest prices of several stocks from an imput file
	 */
	public static void main(String[]args) throws IOException, FileNotFoundException{		
	Scanner keyboard = new Scanner(System.in);
	
	String inputname;//string to store name of input file
	System.out.println("Please enter the name of the input file: ");//requests name of file
	inputname = keyboard.next();//scans in name of file
	Scanner scanner = new Scanner(new File(inputname));//opens file
	Scanner inputfile = scanner.useDelimiter(":|\\n");//delimiter to ensure it works properly
	double tempPrice;//temporary price from stocks to calculate total average
	double sum = 0;//sum of stock prices
	double avg = 0;//average of stock prices
	double lowestPrice; //lowest price
	double highestPrice = 0;//highest price
	int highestPlace = 0;//place in array with highest price
	int lowestPlace = 0;//place in array with lowest price
	
	
	ArrayList <Stock> stocks = CreateStockArray(inputfile);//creates array
	
	lowestPrice = stocks.get(0).getCurrentPrice();//sets lowest to first value
	for(int i = 0; i < stocks.size(); i++){//for every stock in the array...
		tempPrice = stocks.get(i).getCurrentPrice();//get price
		sum = sum + tempPrice; //adds price to total sum
		if (tempPrice > highestPrice) {
			highestPlace = i;//stores location of stock in array to allow us to get info later
		}//end highest if
		if (tempPrice < lowestPrice) {
			lowestPlace = i;//stores location of stock in array to allow us to get info later
		}//end lowest if
	}//end sum for
	avg = sum / stocks.size(); //divides added price by # of stocks to get average
	
	OutputStockInfo(keyboard, sum, avg,	stocks, highestPlace, lowestPlace);//outputs info
	
	System.out.println("Done. Please check your file for the information.");
	
	//close files to prevent memory leaks:
	keyboard.close();
	scanner.close();
	inputfile.close();
	}//end main
	
	//methods:
	public static ArrayList<Stock> CreateStockArray (Scanner inputfile){
		/* FUNCTION: CreateStockArray
		 * PURPOSE: Creates an array of stocks from a file
		 * @Parameter	inputfile				input file to get info from
		 * 				stockTemp				string with a stock's info from  file
		 * 				arrayStock				the array to store in
		 *				stockInfo				info from a single stock split into many strings
		 *				toDouble				double to store current price
		 *				toDouble2				double to store next price
		 */
		String stockTemp = inputfile.nextLine();//reads the first line to get rid of header 
		
		ArrayList <Stock> arrayStock = new ArrayList<Stock>();
		
		while (inputfile.hasNext()) {//while the file isn't empty, fill the array
			stockTemp = inputfile.nextLine();//get next stock info
			String [] stockInfo = stockTemp.split(",");//splits the line into many strings
			Double toDouble = Double.parseDouble(stockInfo[2]);
			Double toDouble2 = Double.parseDouble(stockInfo[3]);
			Stock temp = new Stock(stockInfo[0], stockInfo[1], toDouble, toDouble2); 
			arrayStock.add(temp); //adds temp to Stock array
		}//end array-fill while
		return arrayStock;
	}//end CreateStockArray
	
	public static void OutputStockInfo (Scanner keyboard, double sum, double avg, ArrayList<Stock>
	stocks, int highestPlace, int lowestPlace) throws IOException, FileNotFoundException{
		/* FUNCTION: OutputStockInfo
		 * PURPOSE: Outputs information to a file
		 * @Parameter	keyboard		scanner for user input
		 * 				sum				sum of stock prices
		 * 				avg				average of all stock prices
		 * 				stocks			array of stock information
		 * 				highestPlace	place in array of stock with highest price
		 * 				lowestPlace		place in array of stock with lowest price
		 */
		String outputname; //name of output file
		
		System.out.println("Please enter the name of the desired output file: ");
		outputname = keyboard.next();//scans in name of outputfile
		
		PrintWriter outputfile = new PrintWriter(outputname);//creates output file
		
		outputfile.printf("Sum of all stock proces: %.2f%n", sum);//prints sum
		outputfile.printf("Average of all stock proces: %.2f%n", avg);//prints avg
		outputfile.printf("Stock with the highest price: %s with a price of $%.2f%n",
				stocks.get(highestPlace).getName(), stocks.get(highestPlace).getCurrentPrice());
		outputfile.printf("Stock with the lowest price: %s with a price of $%.2f%n",
				stocks.get(lowestPlace).getName(), stocks.get(lowestPlace).getCurrentPrice());
		
		//closing files to prevent memory leaks:
		outputfile.close();
	}//end OutputStockInfo
	
}//end class