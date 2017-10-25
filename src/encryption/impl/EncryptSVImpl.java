package encryption.impl;

import encryption.IEncryptSV;
import exception.SecretkeyInitException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;

public class EncryptSVImpl implements IEncryptSV {
    private Key publicKey;
    private Key privateKey;
    private static final String publicFile = "publickey.keystore";
    private static final String privateFile = "privatekey.keystore";
    private static final String ALGORITHM = "RSA";
    public EncryptSVImpl() throws SecretkeyInitException {
        init();
    }

    private void init() throws SecretkeyInitException {
        try(ObjectInputStream ois1 = new ObjectInputStream(this.getClass().getResourceAsStream(publicFile));
            ObjectInputStream ois2 = new ObjectInputStream(this.getClass().getResourceAsStream(privateFile))) {
            publicKey = (Key)ois1.readObject();
            privateKey = (Key)ois2.readObject();
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(publicKey == null || privateKey == null)
            throw new SecretkeyInitException();
    }

    public Key getPublicKey() {
        return publicKey;
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    @Override
    public String encrypt(String source) throws Exception {
        if(source == null || source.length() < 1)
            return null;
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        byte[] encryB = cipher.doFinal(b);
        return new BASE64Encoder().encode(encryB);
    }

    @Override
    public String decrypt(String cryptograph) throws Exception {
        if(cryptograph == null || cryptograph.length() < 1)
            return null;
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(cryptograph);
        return new String(cipher.doFinal(b));
    }
}
