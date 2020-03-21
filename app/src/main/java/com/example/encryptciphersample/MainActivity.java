package com.example.encryptciphersample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EncryptCipherModel encryptCipherModel = new EncryptCipherModel();
        String decoded = "";
        try {
            encryptCipherModel = RSAEncrypt("Ali Doran");
        }catch (Exception e){}

        try {
            decoded = RSADecrypt(encryptCipherModel.getEncryptedBytes(), encryptCipherModel.getPrivateKey());
        }catch (Exception e){}

        Toast.makeText(this, decoded , Toast.LENGTH_LONG).show();

    }
    public EncryptCipherModel RSAEncrypt(final String plain) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.genKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
        EncryptCipherModel encryptCipherModel = new EncryptCipherModel();
        encryptCipherModel.setEncryptCipher(publicKey , privateKey , encryptedBytes);
        return encryptCipherModel;
    }

    public String RSADecrypt(final byte[] encryptedBytes, PrivateKey privateKey) throws Exception {
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher1.doFinal(encryptedBytes);
        String decrypted = new String(decryptedBytes);
        System.out.println("DDecrypted?????" + decrypted);
        return decrypted;
    }
}
