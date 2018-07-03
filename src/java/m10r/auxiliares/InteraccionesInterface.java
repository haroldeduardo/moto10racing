
package m10r.auxiliares;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author CSR
 */
public class InteraccionesInterface {
    
    public static String sha512 (String passEncript){
        StringBuilder sb = new StringBuilder();
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(passEncript.getBytes());
            byte[] mb = md.digest();
            
            for (int i = 0 ; i < mb.length; i++){
                sb.append(Integer.toString((mb[i] & 0xff)+ 0x100, 16).substring(1));
            }
            
        } catch (NoSuchAlgorithmException ex) {
        }
        return sb.toString();
    }
}
