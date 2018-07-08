package com.brian.springboot.example;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static int lengthOfLongestSubstring(String s) {
        Set<String> set = new HashSet<>();
        int maxLen = 0;
        int i = 0;

        start: while(i<s.length()){
            set.add(String.valueOf(s.charAt(i)));
            for(int j=i+1;j<s.length();j++){
                if(set.add(String.valueOf(s.charAt(j)))){
                    maxLen = Math.max(j-i+1,maxLen);
                }else{
                    i=j;
                    set.clear();
                    continue start;
                }
            }
            i++;
        }

        return maxLen;
    }

    public static void main(String[] args){
//        System.out.println(nextGreatestLetter(new char[]{'c','f','g'},'c'));
        System.out.println(findComplement(5));
    }

    private static final char NOT_FOUND= (char)0;

    public static char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length-1;
        if(target > letters[end]){
            return letters[start];
        }

        char result = findIt(letters,start,end,target);
        return result==NOT_FOUND?letters[start]:result;
    }

    private static char findIt(char[] letters, int start, int end, char target){
        if(start > end){
            return NOT_FOUND;
        }
        if(letters[start] > target){
            return letters[start];
        }
        int middle = (end-start)/2 + start;
        if(target < letters[middle]){
            return findIt(letters, middle, end,target);
        }else{
            return NOT_FOUND;
        }

    }

    public static int findComplement(int num) {
        int mask = 1,temp = num;
        while(temp>0){
            mask = mask << 1;
            temp = temp>>1;
        }
        return num^(mask-1);
    }
}
