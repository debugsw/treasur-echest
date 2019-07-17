package com.liushuai.treasurechest.encryption;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description des加密(已弃用, 只会对旧系统使用)
 * @Author ls
 * @Date 2019/7/16 21:50
 */
public class EncrypDES {

    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator keyGenerator;

    //负责保存对称密钥
    private SecretKey secretKey;

    //负责完成加解密工作
    private Cipher cipher;
    //该字节数组负责保存加密的结果
    private byte[] cipherByte;

    /**
     * 构造函数，完成所需变量的初始化
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public EncrypDES() throws NoSuchAlgorithmException, NoSuchPaddingException {
        keyGenerator = KeyGenerator.getInstance("DES");
        //生成密钥
        secretKey = keyGenerator.generateKey();
        //生成cipher对象,指定其支持的DES算法
        cipher = Cipher.getInstance("DES");
    }

    /**
     * 对字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public byte[] encrytor(String str) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] src = str.getBytes("GBK");
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
        EncrypDES encrypDES = new EncrypDES();
        String msg = "you are alone not.";
        byte[] encrytor = encrypDES.encrytor(msg);
        byte[] decryptor = encrypDES.Decryptor(encrytor);

        System.out.println("原文" + msg);
        System.out.println("密文: " + new String(encrytor));
        System.out.println("解密: " + new String(decryptor));

    }


}
