import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class SP2024_SaleProductApplication_Jung {
	private static final String SALES_PRODUCT="SP2024_SaleProductSP23_Application_Jung.java";
	private static final String TODAY_STRING="Today: 03/05/2023";
	
	static final float MODELA_PRICE = 11.39f;
	static final float MODELB_PRICE = 12.59f;
	static final float MODELC_PRICE = 13.79f;
	static final float MODELD_PRICE = 14.99f;
	static float[] price = {MODELA_PRICE, MODELB_PRICE, MODELC_PRICE, MODELD_PRICE};
	static final float TAX = 0.0825f;
	static int tran=1;
	int [] UnitsModel=new int[4];
	
	public static void main(String[] args) throws Exception {
		SP2024_SaleProductApplication_Jung app = new SP2024_SaleProductApplication_Jung();
		app.go();
	}
	
	void go() {
		int mainChoice =getMainChoice();
		if(mainChoice == 0) {
			System.exit(0);
		}else if(mainChoice ==1) {
			Scanner sc = new Scanner(System.in); // Create a Scanner object
			String saleProductDisplay=SALES_PRODUCT+"\n"
					+"PRODUCT MODELS MENU - LUIS MARTINEZ\n"
					+TODAY_STRING
					+"\n------------------------------\n"
					+"SALE PRODUCT:\n"
					+"1.Model A - $11.39\n"
					+"2.Model B - $12.59\n"
					+"3.Model C - $13.79\n"
					+"4.Model D - $14.99\n"
					+"0.Exit\n"
					+"Enter 1, 2, 3, 4 to select the product model or 0 to Exit:\n";
			System.out.println(saleProductDisplay);
			int productChoice=-1;
			while(sc.hasNext()) {
				productChoice = Integer.parseInt(sc.nextLine());
				if(productChoice ==0) {
					SP2024_Product_Jung product = new SP2024_Product_Jung(UnitsModel,price);
					try {
				      BufferedWriter bw = new BufferedWriter(new FileWriter("dayFile_03052024.txt"));
				      printReport(1,product);
				      bw.write(tran+" "+product);
				      bw.flush();
				      bw.close();
					}catch(Exception ee) {
						ee.printStackTrace();
					}
					break;
				}else {
					int arrIndex=productChoice-1;
					int quantity=getProductQuantityChoice(productChoice);
					UnitsModel[arrIndex]=quantity+UnitsModel[arrIndex];
				}
				System.out.println(saleProductDisplay);
			}
			go();
		}else if(mainChoice ==2 || mainChoice ==3 || mainChoice ==4) {
			
			Scanner sc=new Scanner(System.in);
			String reportType="";
			if(mainChoice ==2)
				reportType="Day Report";
			else if(mainChoice ==3)
				reportType="Month Report";
			else if(mainChoice ==4)
				reportType="Year Report";
			System.out.print("You chose "+reportType+". Please enter file name:");
			String fileName=sc.nextLine();
			writeToFile(mainChoice,reportType,fileName);
			
		}
		
	}
	void writeToFile(int choice,String reportType,String fileName) {
		String outputFileName="";
		String prefix="";
		int []unitModel=new int[4];
		
		try {
			File file=new File(fileName);
			
			if(file.exists()) {
				if(choice ==2 || choice ==3 || choice ==4) { //Day Report
					if(choice ==2) {
						outputFileName="monthFile_"+fileName.substring(8, 10)+fileName.substring(12, 16)+".txt";
						prefix=fileName.substring(8, 10);
					}else if(choice ==3) {
						outputFileName="yearFile_"+fileName.substring(12, 16)+".txt";
						prefix=fileName.substring(10, 12);
					}
					
					BufferedReader brd = new BufferedReader(new FileReader(file));
					String line="";
					while ((line=brd.readLine())!=null) {
			        	String [] fields = line.split(" ");
			        	for(int i=0;i<4;i++) {
			        		unitModel[i] += Integer.parseInt(fields[i+1]);
			        	}
			        }   
			        brd.close();
			        
			      SP2024_Product_Jung product = new SP2024_Product_Jung(unitModel,price);
			      printReport(choice,product);
			      if(choice ==2 || choice ==3) {
			    	  BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
				      
				      bw.write(prefix+" "+product);
				      bw.flush();
				      bw.close();  
			      }
			      
				}
			}else {
				System.out.println("File Not FOUND");
			}
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	void printReport(int choice,SP2024_Product_Jung product) {
		System.out.println("######## This is report part##########\n");
		
		String lastPartOfheader="";
		if(choice ==1) {
			lastPartOfheader="Day Receipt:           03/05/2024\n";
		}else if(choice ==2) {
			lastPartOfheader="Day Report:           03/05/2024\n";
		}else if(choice ==3) {
			lastPartOfheader="Month Report:         03/05/2024\n";
		}else if(choice ==4) {
			lastPartOfheader="Year Report:          03/05/2024\n";
		}
		String header="File SP2024_SaleProductApplication_Jung.java\n"
				+"Sale Product Receipt - LUIS MARTINEZ\n"
				+lastPartOfheader;
		
		System.out.print(header);
		System.out.println(product.printReceipt());
		System.out.println("######## End of report ##########");
	}
	int getMainChoice() {
		Scanner sc = new Scanner(System.in); // Create a Scanner object
		String mainDisplay=SALES_PRODUCT+"\n"
				+"SP23 COMPANY MENU - LUIS MARTINEZ\n"
				+TODAY_STRING
				+"\n------------------------------\n"		 
				+"1.Sale SP24 Product\n"
				+"2.Print Day Sale Report\n"
				+"3.Print Month Sale Report\n"
				+"4.Print Year Sale Report\n"
				+"0.Exit\n"
				+"Enter a number 1 to 4 to select a task or 0 to exit:\n";
		System.out.println(mainDisplay);
		return Integer.parseInt(sc.nextLine()); // Read user input;
	}
	int getProductQuantityChoice(int productIdx) {
		Scanner sc = new Scanner(System.in); // Create a Scanner object
		int modelCount=0;
		String modelName="";
		if(productIdx ==1)modelName="Model A";
		else if(productIdx ==2)modelName="Model B";
		else if(productIdx ==3)modelName="Model C";
		else if(productIdx ==4)modelName="Model D";
		
		System.out.print("Please enter number for "+modelName+":");
		modelCount = Integer.parseInt(sc.nextLine());
		return modelCount;
	}
}
