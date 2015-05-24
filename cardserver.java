import java.io.*;
import java.net.*;
import java.util.*;

public class cardserver{

	public static void main(String[]args){
		ServerSocket ss=null;
		Socket cs=null;
		String[][]playdeck=new String[4][13];
		String[][]playdeck2=new String[4][13];
		playgame ggmod=null;
		int giverow=0,givecol=0;
		ArrayList <Integer>taglist=new ArrayList<Integer>();
		try{
			ss=new ServerSocket(8787);
			playdeck2=ggmod.deck(playdeck);
			int tag=0;
			for(int i=0;i<4;i++)
				for(int j=0;j<13;j++)
					System.out.print(playdeck2[i][j]+"\t");
			while(true){
				tag++;
				if(givecol==12){
					giverow++;
					givecol=0;
					}
					if(giverow==3){
					giverow=0;
					}
					//System.out.println(givecol);
				
				//System.out.println(temptag+"\n"+tag);
				cs=ss.accept();
				ggmod=new playgame(cs,playdeck2,tag,giverow,givecol);
				System.out.println("玩家 "+tag+"已就緒");
				ggmod.start();
				givecol+=2;
				
				
			}
			
		}catch(Exception ex){
			
			System.out.println(ex.toString());
		}
		
	}
	
		
	
}
class playgame extends Thread{
	
	Socket cs=null;
	String[][]playdeck=new String[4][13];
	PrintWriter pw=null;
	InputStreamReader is =null;
	BufferedReader bis=null;
	int tag,temptag;
	int times=0;
	int status=0;
	//ArrayList<Integer>taglist=new ArrayList<Integer>();
	int giverow=0,givecol=0;
	playgame(Socket cs,String[][] playdeck2,int tag,int giverow,int givecol){
		this.cs=cs;
		playdeck=playdeck2;
		this.tag=tag;
		this.giverow=giverow;
		this.givecol=givecol;
		//this.taglist=taglist;
		/*for(int i=0;i<4;i++)
				for(int j=0;j<13;j++)
					System.out.println(playdeck[i][j]);*/
	}
	
	
	public void run(){
		try{
			
			while(true){
				is=new InputStreamReader(cs.getInputStream());
				bis=new BufferedReader(is);
				pw=new PrintWriter(cs.getOutputStream());
				/*for(int i=0;i<4;i++){
					for(int j=0;j<13;j++){
					System.out.print(playdeck[i][j]+"\t");
					}*/
				
			if(tag>0){			
				for(int k=0;k<2;k++){		
					System.out.println("向玩家"+tag+"發出了"+playdeck[giverow][givecol]);
					pw.write(playdeck[giverow][givecol]+"\n");
					givecol++;
					pw.flush();
					/*if(givecol==12){
					giverow++;
					givecol=0;
					}
					if(giverow==3){
					giverow=0;
					}
					System.out.println(givecol);
					givecol++;*/
					//System.out.println("k:"+k);
				}
				
			}			
			tag=0;		
					
						
					
					
			}
			
				
			}
			
			
		catch(Exception ex){
			System.out.println(ex.toString());
		}
		
		
		
	}
	
	public static String[][] deck(String playdeck[][]){
		Random rd = new Random();
		String sign[] = new String[] { "紅心", "梅花", "方塊", "黑桃" };
		String deck[][] = new String[4][13];
		int i=0,j=0;
		String numlist[] = new String[] { "A", "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "J", "Q", "K" };
		for (i = 0; i < 4; i++) {

			for (j = 0; j < 13; j++) {

				deck[i][j] = sign[i] + numlist[j];
				//System.out.print(" " + deck[i][j] + " ");

			}
			//System.out.println("");
		}
		int a = 0, b = 0;
		String temp = "";
		for (i = 0; i < 4; i++) {

			for (j = 0; j < 13; j++) {
				a = rd.nextInt(4);
				b = rd.nextInt(13);
				temp = deck[i][j];
				deck[i][j] = deck[a][b];
				deck[a][b] = temp;

			}
		}

		/*System.out.println("洗牌後\n");
		for (i = 0; i < 4; i++) {

			for (j = 0; j < 13; j++) {
				System.out.print(" " + deck[i][j] + " ");

			}

			System.out.println("");
		}*/
		return deck;
	}
	
	
	
	
}