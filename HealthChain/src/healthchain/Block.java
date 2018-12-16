package healthchain;

import java.util.Date;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class Block {

	public String hash; //Hash generated from the mined block
	private String data; //All the Patient data
	private String timeStamp; //The timestamp at which transcation takes place
	public String previousHash; //The hash of previous block
	private int nonce; //To find a integer that satisfies the given difficulty in concensus algo

	public Block(String data,String previousHash )
	{
		//THe Block constructor
		this.data = data;
		this.timeStamp = HealthChain.time;
		this.previousHash = previousHash;		
		this.hash = calHash(); //fuction that calculate hash.
	}
	
	
	public String calHash() {
		//Hash Calculation for new block
		String calhash = StrOpr.applySha256( 
				previousHash +
				timeStamp +
				Integer.toString(nonce) + 
				data 
				);
		return calhash;
	}
	
	
	public void mineBlock(int difficulty) {
		//Mine operation, find the value that matches the req. hash output
		String difstr = StrOpr.getDificultyString(difficulty); //Generate a diff. string
		while(!hash.substring( 0, difficulty).equals(difstr)) {
			nonce ++;
			hash = calHash();
		}
		System.out.println("Block Mined Successfully! Hash: " + hash);
	}
	

}
