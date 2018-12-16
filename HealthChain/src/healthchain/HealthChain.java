package healthchain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import healthchain.Block;
import healthchain.StrOpr;
import com.google.gson.GsonBuilder;

import java.util.*;

public class HealthChain {

	public static String time;
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;	//can increase depending on Req.

	/*static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	static LocalDateTime now = LocalDateTime.now();
	
	public static String times()
	{
		time = dtf.format(now);
		return time;
	}*/
	
	public static void main(String[] args) {	
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		//LocalDateTime now = LocalDateTime.now();
		
		//time = dtf.format(now);
		
		//add our blocks to the blockchain ArrayList:
		
		System.out.println("Mining Genesis Block... ");
		addBlock(new Block("Hy, this is Genesis Block!", "0"));
		
		String y = "yes",s,m;
		String no = "no";
		
		String fname,add,hospital,dis,med,bill,fdata;
		
		Scanner sc = new Scanner(System.in);
		int i=1;
		do {
			System.out.println("Enter the Patient's Data..");
			
			System.out.println("Enter Full Name");
			fname= sc.nextLine();
			System.out.println("Enter Address");
			add = sc.nextLine();
			System.out.println("Enter Hospital");
			hospital = sc.nextLine();
			System.out.println("Enter Disease");
			dis = sc.nextLine();
			System.out.println("Enter Medication given");
			med = sc.nextLine();
			System.out.println("Enter Final Bill Amount");
			bill = sc.nextLine();
			i++;
			
			LocalDateTime now = LocalDateTime.now();
			time = dtf.format(now);
			fdata="Full Name: "+fname+"| Address: "+add+"| Hospital: "+hospital+
					"| Disease: "+dis+"| Medication: "+med+"| Final Bill: Rs"+bill;
			
			addBlock(new Block(fdata,blockchain.get(blockchain.size()-1).hash));
			System.out.println("\nBlockchain is Valid: " + isChainValid());
			System.out.println("Do you want to enter more data, yes/no");
			m= sc.nextLine();
		}
		while(m.equalsIgnoreCase(y));
				
		
		//System.out.println("Blockchain is Valid: " + isChainValid());
		
		String blockchainJson = StrOpr.getJson(blockchain);
		System.out.println("\nThe Blockchain: ");
		System.out.println(blockchainJson);
		
		if(m.equalsIgnoreCase(no))
		{
			//System.out.println(" ");
			System.out.println("EXIT");
		}
		
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//Traverse through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}
