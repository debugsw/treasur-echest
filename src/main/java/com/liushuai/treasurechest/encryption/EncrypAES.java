package com.liushuai.treasurechest.encryption;

import com.sun.crypto.provider.SunJCE;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Security;

/**
 * @Description AES加解密
 * @Author ls
 * @Date 2019/7/17 21:44
 */
public class EncrypAES {

    private KeyGenerator keyGenerator;

    private SecretKey secretKey;

    private Cipher cipher;

    private byte[] cipherByte;

    public EncrypAES() throws Exception {
        Security.addProvider(new SunJCE());

        keyGenerator = KeyGenerator.getInstance("AES");
        //生成密钥
        secretKey = keyGenerator.generateKey();

        cipher = Cipher.getInstance("AES");
    }

    public byte[] Encrytor(String str) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] src = str.getBytes();
        // 加密，结果保存进cipherByte
        cipherByte = cipher.doFinal(src);
        return cipherByte;
    }

    /**
     * 对字符串解密
     *
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] Decryptor(byte[] buff) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        cipherByte = cipher.doFinal(buff);
        return cipherByte;
    }

    public static void main(String[] args) throws Exception {
        EncrypAES de1 = new EncrypAES();
        String msg ="You are not alone.";
        byte[] encontent = de1.Encrytor(msg);
        byte[] decontent = de1.Decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + new String(encontent));
        System.out.println("解密后:" + new String(decontent));

    }

}
