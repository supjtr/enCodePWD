package encryption.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;

/** 
* EncryptSVImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>Ê®ÔÂ 25, 2017</pre> 
* @version 1.0 
*/ 
public class EncryptSVImplTest {
    private EncryptSVImpl encSVImpl;

@Before
public void before() throws Exception {
    encSVImpl = new EncryptSVImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: encrypt(String source) 
* 
*/ 
@Test
public void testEncrypt() throws Exception { 
    String sourcePWD = "123456";
    String encryPWD = null;
    encryPWD = encSVImpl.encrypt(sourcePWD);
    Assert.assertNotNull(encryPWD);
    System.out.println(encryPWD);
} 

/** 
* 
* Method: decrypt(String cryptograph) 
* 
*/ 
@Test
public void testDecrypt() throws Exception { 
    String goalPWD = "123456";
    String source = "Jdc16M7Tb7gQfOpz5YssKIZtPqHe59DdHWLiAoUB3RKFsKr5vBSRyb7LafnF6STabUKtwDFHbNZJ\n" +
            "3DuKP+u4c471bDUHPKdkmeu4k2ouIVmr2BUbUWzuxiiQyCf4qCCr2vXJIQ1+wQrs2t7bGV25tPFz\n" +
            "ThnHeTgbCM+WL6bfDNA=";
    Assert.assertEquals(goalPWD, encSVImpl.decrypt(source));
    System.out.println(encSVImpl.decrypt(source));
} 

    @Test
    public void testInit() {
        Key publicKey = encSVImpl.getPublicKey();
        Key privateKey = encSVImpl.getPrivateKey();
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
    }

} 
