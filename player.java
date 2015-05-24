import java.net.*;
import java.io.*;
import java.util.*;

class player{

	public static void main(String[]args)throws Exception{
		
		Socket cs=new Socket("127.1.1.1",8787);
		ServerSocket ss=new ServerSocket();
		InputStreamReader is=null;
		BufferedReader br=null;
		while(true){
				
			is=new InputStreamReader(cs.getInputStream());
			br=new BufferedReader(is);
			System.out.println(br.readLine());
			System.out.println(br.readLine());


		}

	}
}