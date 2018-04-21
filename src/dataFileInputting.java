
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


class perDetails{
    
    String custId;
    String name;
    String totamt;
    
    perDetails(String cid , String n , String t){
        custId = encrypt(cid);
        name = encrypt(n);
        totamt = encrypt(t);
    }
    
    
    
    // encrypted using key...
    static String encrypt(String str){
        String newStr="";
        
        for(int i=0;i<str.length();i++){
            newStr+=(char)(str.charAt(i)^12); 
        }
        return newStr;
    }
    
    
    
    
    // INPUTTING DATA INTO FILE
    static void insertIntoFile(perDetails details[]){
        
      try{
         //    OutputStream out =new FileOutputStream(file);
         
              BufferedWriter out = new BufferedWriter(new FileWriter("input.txt"));
            //  out.write("aString\nthis is a\nttest");  //Replace with the string 

             for(int i=0;i<details.length ; i++){  
                    String data = details[i].custId+" "+details[i].name+" "+details[i].totamt+" " ;
                    
                     out.write(data);
             }
            out.close();
      
     }catch(Exception ex){}
        
    }


    
    //reading DATA FROM FILE
   static void gettingFromFile(){
       

         Scanner sc2 = null;
         try {
                 sc2 = new Scanner(new File("input.txt"));
              } catch (FileNotFoundException e) {
                    e.printStackTrace();  
                    }
         
         
        while (sc2.hasNextLine()) {
             Scanner s2 = new Scanner(sc2.nextLine());
             int cnt=1;
              while (s2.hasNext()) {
                  
                    String s =  encrypt(s2.next());
                    
                    if (cnt%3==1) System.out.println("");
                    System.out.print(s+" ");
                    
                    cnt++;
 
                }
        }

       
   }  
   
   
   
   //CHECKING PASSWORD
   static boolean checkPassword(String pass){
       String realPass="";
        Scanner sc2 = null;
         try {
                 sc2 = new Scanner(new File("password.txt"));
         
         
         
                while (sc2.hasNextLine()) {
                  Scanner s2 = new Scanner(sc2.nextLine());
                   int cnt=1;
                   while (s2.hasNext()) {
                  
                    String s =  s2.next();
                    
                   realPass+=(encrypt(s));
                    
                    cnt++;
 
                }
             }
          
          } catch (FileNotFoundException e) {
                    System.out.println("File doesnt Exist ");
                    }
        
       // System.out.println("pass="+pass+" and realpass="+realPass );
        
        if (realPass.equals(pass))
            return true;
        else
            return false;
        
   
   }
   
   
   
   //SETTING PASSWORD
   static boolean setPassword(String pass) throws IOException{
   
                 try{
                      BufferedWriter out = new BufferedWriter(new FileWriter("password.txt"));
                      String msg =  encrypt(pass) ;
                      out.write(msg );
                     out.close();
                 
         
      
                  }catch(Exception ex){}
       
         return true;
       
            
      //  return false;

   }
       
  

}




public class dataFileInputting {
    
    public static void main(String args[]) throws IOException{
        
        Scanner sc=new Scanner(System.in);
        

        System.out.println("Enter password to enter:- ");
        
    //    if ( perDetails.checkPassword(sc.next()) )
        {
            System.out.println("Authenticated......");
            
            System.out.println("1. Write Customer info");
            System.out.println("2. Read Customer info");
            System.out.println("3. Change Password");
            
            
            do{
                 System.out.println("Choose Action:-");
            
                  switch(sc.nextInt()){
                     case 1:
                         
                         System.out.println("Enter tot customer to be inserted:");
                         int n=sc.nextInt();
                            perDetails p[]=new perDetails[n];
                         
                            for(int i=0;i<n;i++){
                                  System.out.println("ENTER "+(i+1)+" CUSTOMER DETAILS:id,name,amt");
                                  p[i] = new perDetails(sc.next() , sc.next() , sc.next());
                              }
                            
                            perDetails.insertIntoFile(p);
                    
                         break;
                    
                    case 2:
                            perDetails.gettingFromFile();
                         break;
                   
                    case 3:
                        System.out.println("Enter new Password");
                         perDetails.setPassword(sc.next() );
                        break;
                    
                }
            System.out.println("press 1 to continue");
           }while((sc.nextInt()==1));  
        
        
        }
   //     else
     //       System.out.println("Wrong passsword.. Not Authenticated");
            
        

    }
    
    
    
}
