
package RSACryptChat;

import socketpack.*;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;


public class server_socket {
    
    
    public static int gcd(int x , int y){
        
        if (x%y==0) return y;
        
        return gcd(y , x%y);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server side");
        server_socket srvr = new server_socket();
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
       
        //BigInteger smallKey = new BigInteger(sc.next());
       BigInteger n = p.multiply(q);
       BigInteger one = new BigInteger("1");
       BigInteger phi = ( p.subtract(one) ).multiply( q.subtract(one) );
       
       
       
               System.out.println("Sending P and q to other person ");
       PrintStream ps=new PrintStream(sock.getOutputStream());
    /*       ps.println(p+","+q);
                  System.out.println("Sended Sucessfully ; Genrating key and Requesting for users KEY..... ");
    */  
                  
         //Genrating E for public key
         BigInteger e = new BigInteger("2");
         
         while(e.compareTo(phi)==1){
          //   int t =0;
             if (phi.gcd(e).equals(one))
                 break;
             else
                 e.add(one);
             
         }
         
                
                  
        //GENRATING private KEY..
        //  BigInteger key = q.modPow(smallKey, p) ;
          
      //            System.out.println("Generated KEY :"+key.toString());
        
        //SENDING PUBLIC KEY E and N to client..
        ps.println(n+","+e);
       // ps.println(e);
        
       //Creating PRIVATE KEY FOR ENCRYPTION
       System.out.println("Generating PRIVATE KEY ; Enter k: ");
       BigInteger k = new BigInteger(sc.next());
       
       BigInteger pkey = phi.multiply(k).add(one).divide(e);
        System.out.println("Generated PRIVATE KEY : "+pkey.toString());
       
        
        
        
        
        
        
        
        
        
       /* 
                  System.out.println("WAITING FOR OTHERS KEY");
        //FETHING OTHER KEY
       BigInteger otherKey= new BigInteger(br.readLine());
      
                  System.out.println("Got others key:"+otherKey.toString());
       
       //GENERTING OUR SECRET KEY..
      BigInteger secKey= otherKey.modPow(smallKey, p) ;
       int seck = secKey.intValue();
                    System.out.println("Secret KEY: "+secKey.toString());
       
       */
         //SENDING MSG..
       System.out.println("READY TO CHAT...");
       
      
       
         Thread send=new Thread(new Runnable(){

                  @Override
                  public void run() {
                   while(true){       
            /*       BigInteger ms = new BigInteger(sc.next());
                   ms = ms.xor(secKey);
                   ps.println(ms);
             */
             
                    String msg =sc.nextLine();
                    int[] darr = new int[msg.length()];
                    StringBuilder encstr = new StringBuilder("");
       
                    for(int i=0;i<msg.length();i++){
             //            encstr.append((char)(seck ^ msg.charAt(i)) );
                     }
          
                        ps.println(encstr);
               
             
                   
                   }      
                  }
              });
         
          Thread rec=new Thread(new Runnable(){

                  @Override
                  public void run() {
                   while(true){     
                       try{
       /*              BigInteger gm = new BigInteger(  br.readLine() );
                     gm = gm.xor(secKey);
                    System.out.println(" FORM REC: "+gm.toString());
         */
                    
                         String encmsg = br.readLine();
                          StringBuilder msg = new StringBuilder("");
       
                                   for(int i=0;i<encmsg.length();i++){
        //                               msg.append((char)(seck ^ encmsg.charAt(i)) );
                                    }
        
                                    System.out.println("      <====c msg="+msg);
                      
                       }catch(Exception ex){}
                   }      
                  }
              });
         
          send.start();
       rec.start();
       
        
    }
    
    
  
    
}
