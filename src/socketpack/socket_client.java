
package socketpack;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;

public class socket_client {

    public static void main(String[] args) throws Exception {
        System.out.println("Client side");
        socket_client clnt =new socket_client();
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
          
       //SENDING MSG..
       System.out.println("READY TO CHAT...");
       
      Thread send=new Thread(new Runnable(){

                  @Override
                  public void run() {
                   while(true){       
    /*               BigInteger ms = new BigInteger(sc.next());
                   ms = ms.xor(secKey);
                  ps.println(ms);
      */
    
                        String msg =sc.nextLine();
                       StringBuilder encstr = new StringBuilder("");
       
                    for(int i=0;i<msg.length();i++){
                         encstr.append((char)(seck ^ msg.charAt(i)) );
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
              
              /*    BigInteger gm = new BigInteger(  br.readLine() );
                 gm = gm.xor(secKey);
                 
                   System.out.println(" FORM REC: "+gm.toString());
                */
                          
                          
                          String encmsg = br.readLine();
                          StringBuilder msg = new StringBuilder("");
       
                                   for(int i=0;i<encmsg.length();i++){
                                      //  darr[i] = 12 ^ encstr.charAt(i);
                                       msg.append((char)(seck ^ encmsg.charAt(i)) );
                                    }
        
                                    System.out.println("           <== s msg="+msg);
        
                          
                          
                          
                          }catch(Exception ex){}
                   
                   }      
                  }
              });
       
       send.start();
       rec.start();
       
     
    }
    
    
}
