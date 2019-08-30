package com.twy.input.utils;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/8/22.
 * PS: Not easy to write code, please indicate.
 */
public class AscllUtils {

    ////ascii转换为string
    public static String ASCIIToStr(int asciiCode)
    {
        return Character.toString((char)asciiCode);
    }

    //字符串转换为ascii
    public static String StringToA(String content){
        String result = "";
        int max = content.length();
        for (int i=0; i<max; i++){
            char c = content.charAt(i);
            int b = (int)c;
            result = result + b;
        }
        return result;
    }

}
