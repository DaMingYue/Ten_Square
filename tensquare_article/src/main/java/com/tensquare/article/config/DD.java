package com.tensquare.article.config;

import org.springframework.util.StringUtils;

import java.util.HashMap;

public class DD {
    public static void main(String[] args) {
        /**
         * 无重复字符的最长子串
         *
         * 给定一个字符串，请找出其中不含有重复字符的最长子串的长度
         */
//        String s = "abcabcbb";
//        String s = "pwwkewwfgh";
//        String s = "pwwkew你好";
//        int i = getSubstring_2(s);
//        System.out.println(i);
        int i =reserve(-1234567890);
        System.out.println(i);

    }


    public static int len(String s){
        //记录字符上一次出现的位置
        int []last = new int [128];
//        for (int i=0;i<128;i++){
//            last[i]=-1;
//        }
        int n = s.length();
        int res = 0;
        int start = 0;
        for (int i=0;i<n;i++){
            int index = s.charAt(i);
            start = Math.max(start,last[index]+1);
            res = Math.max(res,i-start+1);
            last[index] = i;
        }
        return res;
    }
    /*
     * 获取最长子串
     * @param str
     * @return
      */
    public static int getSubstring(String str){
        int []last = new int[Character.MAX_CODE_POINT];
//        for (int i=0;i<128;i++){
//            last[i]=-1;
//        }
        System.out.println(last.length);
        int start = 0;
        int res = 0;
        int n = str.length();
        for (int i=0;i<n;i++){
            int index = str.charAt(i);
            start = Math.max(start,last[index]);
            res = Math.max(res,i-start+1);
            last[index] = i+1;
        }
        return res;
    }


    public static int getSubstring_2(String s){
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    public static int reserve(int i){
        int res = i;
        if (i<0){
            res=0-i;
        }
        String str = Integer.toString(res);
        char []cs = str.toCharArray();
        char []cs2 = new char[cs.length];
        for (int j=0;j<cs.length;j++){
            cs2[j]=cs[cs.length-1-j];
            if (cs2[j]=='0'){
                cs2[j]=' ';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j=0;j<cs2.length;j++){
            sb.append(cs2[j]);
        }
        try{
            res=Integer.parseInt(sb.toString().trim());
        }catch (NumberFormatException e){
            return 0;
        }
//        System.out.println(sb.toString().trim());
        if (i<0){
            return -res;
        }
        return res;
    }
    public static int reserve_2(int i){
        int res = 0;
        for (;i!=0;){
            int pos = i % 10;
            if(res > Integer.MAX_VALUE || (res == Integer.MAX_VALUE && pos >7)) {
                return 0;
            }
            if(res < Integer.MIN_VALUE || (res == Integer.MIN_VALUE && pos < -8))
                return 0;
            res = res*10+pos;
            i /= 10;
        }
        return res;
    }
}
