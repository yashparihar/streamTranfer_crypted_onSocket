
package encryptedFiletransfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Yash
 */
public class clientside {
    

    public static void main(String[] args) throws Exception {
        System.out.println("Client side");
        clientside clnt =new clientside();
        clnt.run();
    }
    
    
    
    public void run() throws Exception{
        
            Socket sock=new Socket("localhost",232);
        Scanner sc=new Scanner(System.in);
        
        BufferedReader br=new BufferedReader( new InputStreamReader(sock.getInputStream()));
        PrintStream ps=new PrintStream(sock.getOutputStream());
        
        
        
        
        
        //FETCHING P AND Q
        String msg[]= br.readLine().split(",");
        
        BigInteger p = new BigInteger(msg[0]);
        BigInteger q = new BigInteger(msg[1]);
        
        
        System.out.println("Got p and Q as: "+p.toString()+" , "+q+" succesfully ");
        
        
        
        
        //ENTER UR SMALL KEY..
        System.out.println("Enter ur Small key: ");
        BigInteger smallKey = new BigInteger(sc.next());
       
        
        
        
        
        //GENERATING KEY
       BigInteger key = q.modPow(smallKey, p) ;
          
       
       
        System.out.println("Generated KEY :"+key);
        
        
        
        // SENDING OUT KEY TO OTHER   
             ps.println(key);                              //SENDING ITS KEY
       
             
             
             
        //FETCHING OTHERS KEY   
           System.out.println("WAITING FOR OTHERS KEY");
          BigInteger otherKey= new BigInteger(br.readLine());
      
          System.out.println("Other key: "+otherKey);
         
          
          
          
          
           //GENERTING OUR SECRET KEY..
       BigInteger secKey= otherKey.modPow(smallKey, p) ;
        int seck = secKey.intValue();
       
       
      System.out.println("Secret KEY: "+secKey);
          
    
      
      
      
     
       InputStream in=null;
      OutputStream out=null;
      OutputStream enOut=null;
      
      
      
      System.out.println("Enter file where data need to be written :");
      String fname=(new Scanner(System.in)).next();
      
      try {
              File file=new File(fname);
             File encfile= new File("enc"+fname);
      
              in = sock.getInputStream();          //READING FILE DATA FROM SOCKET
              out =new FileOutputStream(file);     // PUTTING DATA INTO FILE
              enOut =new FileOutputStream(encfile);    // putting encrpted data 
      
      }
      catch(Exception ex){
          System.out.println("Error: "+ex);
      }
      
      
      
          byte b[]=new byte[20*1024];
          int cnt;
          String dd="dfdfdf";
          
          while((cnt=in.read(b))!=-1){
             
                //storing encrpted data into file simply
                      enOut.write(b,0,cnt);  
               
                  //....DECRYPT FROM INCOMING FILE....
                     for(int i=0;i<cnt;i++)
                        b[i] = (byte) (b[i] ^ (byte)seck);
            
               
                     out.write(b, 0, cnt);
            }
     
             in.close();
            out.close();
            sock.close();
    
    
    
}

}