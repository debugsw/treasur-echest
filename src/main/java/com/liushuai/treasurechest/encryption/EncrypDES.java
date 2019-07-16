package com.liushuai.treasurechest.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * @Description des加密(已弃用,只会对旧系统使用)
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

    public EncrypDES() throws NoSuchAlgorithmException, NoSuchPaddingException {
        keyGenerator =KeyGenerator.getInstance("DES");
        //生成密钥
        secretKey = keyGenerator.generateKey();
        //生成cipher对象,指定其支持的DES算法
        cipher = Cipher.getInstance("DES");
    }

    
}
