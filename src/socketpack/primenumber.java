/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketpack;

/**
 *
 * @author Yash
 */
import java.util.*;

public class primenumber {
    
    
    public static void main(String args[]){
        
        Scanner sc=new Scanner(System.in) ;
        int s = sc.nextInt();
        int e= sc.nextInt();
        
        
        
        for(int i=s;i<=e;i++){
            
            if (isPrime(i))
                System.out.println(i);
            
        }
    }

    private static boolean isPrime(int n) {
    
       boolean flg=true;
        
        for(int i=2;i<=Math.sqrt(n) ; i++){
            if (n%i==0){
                flg=false;
                break;
            }
        }
        
        return flg;
    }
}
