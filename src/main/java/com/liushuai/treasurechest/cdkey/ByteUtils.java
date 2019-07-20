package com.liushuai.treasurechest.cdkey;


/**
 * @Description cdkey相关
 * @Author ls
 * @Date 2019/7/20 21:00
 */
public class ByteUtils {
    //原始数组
    byte[] bytes;
    //记录当前写入到多少位
    int index;

    private ByteUtils(int capacity) {
        bytes = new byte[capacity];
        index = 0;
    }

    public static ByteUtils createBytes(int capacity) {
        ByteUtils byteUtils = new ByteUtils(capacity);
        return byteUtils;
    }

    //向数组中追加内容
    public ByteUtils appendNumber(long val) {
        byte[] arr = number2byte(val);
        appendBytes(arr);
        return this;
    }

    public ByteUtils appendNumber(int val) {
        byte[] arr = number2byte(val);
        appendBytes(arr);
        return this;
    }

    public ByteUtils appendNumber(short val) {
        byte[] arr = number2byte(val);
        appendBytes(arr);
        return this;
    }

    public ByteUtils appendNumber(byte val) {
        byte[] arr = new byte[]{val};
        appendBytes(arr);
        return this;
    }

    /**
     * 获取数据的总和
     *
     * @return
     */
    public int getSum() {
        int ret = 0;
        for (int i = 0; i < bytes.length; i++) {
            ret += bytes[i];
        }
        return ret;
    }

    //追加byte数组
    public ByteUtils appendBytes(byte[] arr) {

        for (byte i = 0; i < arr.length; i++) {
            bytes[index + i] = arr[i];
        }

        index += arr.length;
        return this;
    }

    /**
     * 将数字转换为byte数组
     */
    public static byte[] number2byte(long val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 56) & 0xFF),
                (byte) ((val >> 48) & 0xFF),
                (byte) ((val >> 40) & 0xFF),
                (byte) ((val >> 32) & 0xFF),
                (byte) ((val >> 24) & 0xFF),
                (byte) ((val >> 16) & 0xFF),
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;
    }

    public static byte[] number2byte(int val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 24) & 0xFF),
                (byte) ((val >> 16) & 0xFF),
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;
    }

    public static byte[] number2byte(short val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;

    }
}
