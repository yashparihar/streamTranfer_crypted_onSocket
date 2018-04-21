package RSACryptChat;


public class rsaCrypt {

    
    public static int gcd(double x , double y){
        
        if (x%y == 0) return (int)y;
        
        return gcd( y , x%y );
        
    }
    
    public static void main(String args[]){
        

    // Two random prime numbers
    double p = 3;
    double q = 7;
 
    // First part of public key:
    double n = p*q;
 
    // Finding other part of public key.
    // e stands for encrypt
    double e = 2;
    double phi = (p-1)*(q-1);
    while (e < phi)
    {
        // e must be co-prime to phi and
        // smaller than phi.
        if (gcd( phi , e)==1)
            break;
        else
            e++;
    }
 
    // Private key (d stands for decrypt)
    // choosing d such that it satisfies
    // d*e = 1 + k * totient
    int k = 2;  // A constant value
    double d = (1 + (k*phi))/e;
 
    // Message to be encrypted
    double msg = 20;
 
    System.out.println("Message data="+msg);
 
    // Encryption c = (msg ^ e) % n
    double c = Math.pow(msg, e);
    c = c- n*(int)c/n; // Math. fmod(c, n);
    System.out.println("\nEncrypted data="+c);
 /*
    // Decryption m = (c ^ d) % n
    double m = pow(c, d);
    m = fmod(m, n);
    printf("\nOriginal Message Sent = %lf", m);
 
    return 0;
*/}
        
    }
    
//}
