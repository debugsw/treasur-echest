//package com.liushuai.treasurechest.cdkey;
//
///**
// * @Description TODO
// * @Author ls
// * @Date 2019/7/21 21:16
// */
//public class Cdkey {
//
//    private static final char[] r = new char[] {'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p',
//            '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h'};
//
//    //abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXZY0123456789
//
//    /**
//     * 生成算法
//     * @param couponSchemeId
//     * @param redeemSerialNum
//     * @return
//     */
//    public static long enRedeemNum(long couponSchemeId, long redeemSerialNum) {
//        redeemSerialNum = redeemSerialNum << REDEEM_SERIAL_NUM_LS;
//        long r = couponSchemeId | redeemSerialNum;
//        long n = numOfOne(r);
//        long re = r % DIVISOR;
//        r = (r << NUMBER_OF_ONE_LS) | n;
//        r = (r << REMAINDER_LS) | re;
//        return r;
//    }
//
//    /**
//     * 编码算法
//     * @param redeemNum
//     * @return
//     */
//    public static long [] deRedeemNum(long redeemNum) {
//        long couponSchemeId = redeemNum & COUPON_SCHEME_ID_MASK;
//        long redeemSerialNum = redeemNum & REDEEM_SERIAL_NUM_MASK;
//        couponSchemeId = couponSchemeId >> COUPON_SCHEME_ID_RS;
//        redeemSerialNum = redeemSerialNum >> REDEEM_SERIAL_NUM_RS;
//        return new long[] { couponSchemeId, redeemSerialNum };
//    }
//
//    /**
//     * 校验算法
//     * @param redeemNum
//     * @return
//     */
//    public static boolean checkVaild(long redeemNum) {
//        if (redeemNum > 0) {
//            long checkSum = redeemNum & REMAINDER_MASK;
//            long n = (redeemNum & NUMBER_OF_ONE_MASK) >> NUMBER_OF_ONE_RS;
//            long r = (redeemNum & SUM_MASK) >> SUM_RS;
//            if (numOfOne(r) == n) {
//                if (r % DIVISOR == checkSum) {
//                    return Boolean.TRUE;
//                }
//            }
//        }
//        return Boolean.FALSE;
//    }
//
//    /**
//     * n进制换算以及映射算法
//     * @param redeemNum
//     * @return
//     */
//    public static String enRedeemCode(long redeemNum) {
//        char[] buf = new char[32];
//        int charPos = 32;
//        while ((redeemNum / l) > 0) {
//            int ind = (int) (redeemNum % l);
//            buf[--charPos] = r[ind];
//            redeemNum /= l;
//        }
//        buf[--charPos] = r[(int) (redeemNum % l)];
//        String str = new String(buf, charPos, (32 - charPos));
//        return str;
//    }
//
//    /**
//     * 兑换码解码
//     * @param redeemCode
//     * @return
//     */
//    public static long deRedeemCode(String redeemCode) {
//        char chs[] = redeemCode.toCharArray();
//        long res = 0L;
//        for (int i = 0; i < chs.length; i++) {
//            int ind = -1;
//            for (int j = 0; j < l; j++) {
//                if (chs[i] == r[j]) {
//                    ind = j;
//                    break;
//                }
//            }
//            if (ind == -1) {
//                return -1;
//            }
//            if (i > 0) {
//                res = res * l + ind;
//            } else {
//                res = ind;
//            }
//        }
//        return res;
//    }
//
//
//
//}
