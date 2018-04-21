
package encryptedFiletransfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Yash
 */
public class serverside {
    

    public static void main(String[] args) throws Exception {
        System.out.println("Server side");
        serverside srvr = new serverside();
        srvr.run();
    }
    
    public void run() throws Exception {
        ServerSocket srvrsoc=new ServerSocket(232);
        Socket sock=srvrsoc.accept();
        Scanner sc=new Scanner(System.in);
        BufferedReader br=new BufferedReader(  new InputStreamReader( sock.getInputStream()  )  ) ;
 
        
       System.out.println("Connected ");
       
      
       
       //ENTER P Q  AND SENDING TO OTHER AND ENTER SMALL KEY
       System.out.println("Enter P and Q and smallKey:");
       BigInteger p = new BigInteger(sc.next());
        BigInteger q = new BigInteger(sc.next());
       
         BigInteger smallKey = new BigInteger(sc.next());
       
       
         
         
                   System.out.println("Sending P and q to other person ");
       PrintStream ps=new PrintStream(sock.getOutputStream());
       ps.println(p+","+q);
                  System.out.println("Sended Sucessfully ; Genrating key and Requesting for users KEY..... ");
      
                  
                  
        //GENRATING KEY..
          BigInteger key = q.modPow(smallKey, p) ;
          
                  System.out.println("Generated KEY :"+key.toString());
       
                  
                  
        //SENDING KEY..
        ps.println(key);
        
        
        
        
                  System.out.println("WAITING FOR OTHERS KEY");
        //FETHING OTHER KEY
       BigInteger otherKey= new BigInteger(br.readLine());
      
                  System.out.println("Got others key:"+otherKey.toString());
       
                  
                  
                  
       //GENERTING OUR SECRET KEY..
      BigInteger secKey= otherKey.modPow(smallKey, p) ;
       int seck = secKey.intValue();
                    System.out.println("Secret KEY: "+secKey.toString());
       
       
                    
                    
         //SENDING MSG..
       System.out.println("READY TO SEND File...........");
       
      
       
        InputStream in=null;
         OutputStream out;
        // Scanner sc=new Scanner(System.in);
         
        
        
         System.out.println("Enter File name to send: ");
         String fname=sc.next();
         
         
         
         try{
                 File file=new File(fname);
                 in=new FileInputStream(file);  //GET DATA FROM FILE         
             }
            catch (Exception err){
              System.out.println("File not found: "+err);
              return;
         }
         
                       System.out.println("Sending file "+fname);
                       
                       
        
         out=sock.getOutputStream();       //SEND DATA TO CLIENT
         
         byte[] b=new byte[20*1024];
       int cnt;
       
       //in.re
       
         while( (cnt=in.read(b) )!=-1 ){
             //......ENCRYPTION.......       
           for(int i=0;i<cnt;i++)
                b[i] = (byte) (b[i] ^ (byte)seck);
           
           out.write(b, 0, cnt);            
         }
         
         
              System.out.println("Successfully sended file");
      
       
       
       
          in.close();
         out.close();
         sock.close();
         
         
        
    }
    
    
  
    
}
