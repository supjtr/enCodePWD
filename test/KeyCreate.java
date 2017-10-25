
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;


public class KeyCreate {
    private static final int KEYSIZE =1024;
    private static final String ALGORITHM = "RSA";
    private static void generateKeyPair() throws NoSuchAlgorithmException {
       SecureRandom sr = new SecureRandom();
       KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
       kpg.initialize(KEYSIZE, sr);
       KeyPair kp = kpg.generateKeyPair();
       Key publicKey = kp.getPublic();
       Key privateKey = kp.getPrivate();
       try(ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("publickey.keystore"));
           ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("privatekey.keystore"))) {
           oos1.writeObject(publicKey);
           oos2.writeObject(privateKey);
       }catch (IOException e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) throws Exception {
        KeyCreate.generateKeyPair();
    }
}
