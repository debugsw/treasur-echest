package com.liushuai.treasurechest.cdkey;

import java.util.HashSet;
import java.util.Set;


/**
 * @Description cdkey相关
 * @Author ls
 * @Date 2019/7/20 21:00
 */
public class CdkeyUtils {

    static String stringTable = "ABCDEFGHIJKMNPQRSTUVWXYZ23456789";

    /**
     * 安全码
     */
    final static String password = "dak3le2";

    /**
     * 从byte转为字符表索引所需要的位数
     */
    final static int convertByteCount = 5;

    /**
     * 生成兑换码
     * 这里每一次生成兑换码的最大数量为int的最大值即2147483647
     *
     * @param groupId
     * @param codeCount
     * @param codeLength
     * @param password
     * @return
     */
    public static Set<String> create(byte groupId, int codeCount, int codeLength, String password) {
        //8位的数据总长度
        int fullCodeLength = codeLength * convertByteCount / 8;
        //随机码对时间和id同时做异或处理
        //类型1，id4，随机码n,校验码1
        //随机码有多少个
        int randCount = fullCodeLength - 6;

        //如果随机码小于0 不生成
        if (randCount <= 0) {
            return null;
        }
        Set<String> set = new HashSet<>();
        for (int i = 0; i < codeCount; i++) {
            //这里使用i作为code的id
            //生成n位随机码
            byte[] randBytes = new byte[randCount];
            for (int j = 0; j < randCount; j++) {
                randBytes[j] = (byte) (Math.random() * Byte.MAX_VALUE);
            }

            //存储所有数据
            ByteUtils byteUtils = ByteUtils.createBytes(fullCodeLength);
            byteUtils.appendNumber(groupId).appendNumber(i).appendBytes(randBytes);

            //计算校验码 这里使用所有数据相加的总和与byte.max 取余
            byte verify = (byte) (byteUtils.getSum() % Byte.MAX_VALUE);
            byteUtils.appendNumber(verify);

            //使用随机码与时间和ID进行异或
            for (int j = 0; j < 5; j++) {
                byteUtils.bytes[j] = (byte) (byteUtils.bytes[j] ^ (byteUtils.bytes[5 + j % randCount]));
            }

            //使用密码与所有数据进行异或来加密数据
            byte[] passwordBytes = password.getBytes();
            for (int j = 0; j < byteUtils.bytes.length; j++) {
                byteUtils.bytes[j] = (byte) (byteUtils.bytes[j] ^ passwordBytes[j % passwordBytes.length]);
            }

            //这里存储最终的数据
            byte[] bytes = new byte[codeLength];

            //按6位一组复制给最终数组
            for (int j = 0; j < byteUtils.bytes.length; j++) {
                for (int k = 0; k < 8; k++) {
                    int sourceIndex = j * 8 + k;
                    int targetIndex_x = sourceIndex / convertByteCount;
                    int targetIndex_y = sourceIndex % convertByteCount;
                    byte placeVal = (byte) Math.pow(2, k);
                    byte val = (byte) ((byteUtils.bytes[j] & placeVal) == placeVal ? 1 : 0);
                    //复制每一个bit
                    bytes[targetIndex_x] = (byte) (bytes[targetIndex_x] | (val << targetIndex_y));
                }
            }

            StringBuilder result = new StringBuilder();
            //编辑最终数组生成字符串
            for (int j = 0; j < bytes.length; j++) {
                result.append(stringTable.charAt(bytes[j]));
            }
            set.add(result.toString());
        }
        return set;
    }

    /**
     * 验证兑换码
     *
     * @param code
     */
    public static String verifyCode(String code) {
        byte[] bytes = new byte[code.length()];

        //首先遍历字符串从字符表中获取相应的二进制数据
        for (int i = 0; i < code.length(); i++) {
            byte index = (byte) stringTable.indexOf(code.charAt(i));
            bytes[i] = index;
        }

        //还原数组
        int fullCodeLength = code.length() * convertByteCount / 8;
        //随机码有多少个
        int randCount = fullCodeLength - 6;

        byte[] fullBytes = new byte[fullCodeLength];
        for (int j = 0; j < fullBytes.length; j++) {
            for (int k = 0; k < 8; k++) {
                int sourceIndex = j * 8 + k;
                int targetIndex_x = sourceIndex / convertByteCount;
                int targetIndex_y = sourceIndex % convertByteCount;

                byte placeVal = (byte) Math.pow(2, targetIndex_y);
                byte val = (byte) ((bytes[targetIndex_x] & placeVal) == placeVal ? 1 : 0);

                fullBytes[j] = (byte) (fullBytes[j] | (val << k));
            }
        }

        //解密，使用密码与所有数据进行异或来加密数据
        byte[] passwordBytes = password.getBytes();
        for (int j = 0; j < fullBytes.length; j++) {
            fullBytes[j] = (byte) (fullBytes[j] ^ passwordBytes[j % passwordBytes.length]);
        }

        //使用随机码与时间和ID进行异或
        for (int j = 0; j < 5; j++) {
            fullBytes[j] = (byte) (fullBytes[j] ^ (fullBytes[5 + j % randCount]));
        }

        //获取校验码 计算除校验码位以外所有位的总和
        int sum = 0;
        for (int i = 0; i < fullBytes.length - 1; i++) {
            sum += fullBytes[i];
        }
        byte verify = (byte) (sum % Byte.MAX_VALUE);

        //校验
        if (verify == fullBytes[fullBytes.length - 1]) {
            return "success";
        } else {
            return "failed";
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=======================");
        create((byte) 1, 1000000, 13, password);

        verifyCode("NCUGEQY5N4GKJ");
    }
}
