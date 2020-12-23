package com.inet.code.utils;

/**
 * 进行无符号的数据进行转化
 *
 * @author HCY
 * @since 2020/12/23 11:14 上午
 */
public class UnitUtil {

    /**
     * 将 short类型 变成 Unit8
     *
     * @author HCY
     * @since 2020/12/23 11:34 上午
     * @param value: 原始数据
     * @return short
    */
    public static short getUnit8(short value){
        return (short) (value & 0x00ff);
    }

    /**
     * 将 int 类型 变成 Unit16
     * @author HCY
     * @since 2020/12/23 11:35 上午
     * @param value: 原始数据
     * @return int
    */
    public static int getUnit16(int value){
        return value & 0x0000ffff;
    }

    /**
     * 将 long类型 变成 Unit32
     * @author HCY
     * @since 2020/12/23 11:35 上午
     * @param value: 原始数据
     * @return long
    */
    public static long getUnit32(long value){
        return value & 0x00000000ffffffff;
    }

    /**
     * 将 short类型数组 转换成为Unit8
     *
     * @author HCY
     * @since 2020/12/23 11:32 上午
     * @param value: 原始数组
     * @return short[]
    */
    public static short[] getUnit8Array(short[] value){
        short[] result = new short[value.length];
        for (int i = 0 ; i < value.length ; i++) {
            result[i] = getUnit8(value[i]);
        }
        return result;
    }

    /**
     * 将 int类型的数组 变换成为Unit16
     *
     * @author HCY
     * @since 2020/12/23 11:31 上午
     * @param value: 原始数组
     * @return int[]
    */
    public static int[] getUnit16Array(int[] value){
        int[] result = new int[value.length];
        for (int i = 0; i < value.length; i++) {
            result[i] = getUnit16(value[i]);
        }
        return result;
    }

    /**
     * 将 long类型的数组 变换成为Unit32
     *
     * @author HCY
     * @since 2020/12/23 11:30 上午
     * @param value: 原始数组
     * @return long[]
    */
    public static long[] getUnit32Array(long[] value){
        long[] result = new long[value.length];
        for (int i = 0 ; i < value.length ; i++){
            result[i] = getUnit32(value[i]);
        }
        return result;
    }
}
