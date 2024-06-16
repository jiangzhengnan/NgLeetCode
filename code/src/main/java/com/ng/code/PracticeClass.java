package com.ng.code;

import com.ng.base.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class PracticeClass {

    private void test() {
        LogUtil.print('a');

        LogUtil.print(canConstruct("aa", "ab"));
    }

    //判断 ransomNote 能不能由 magazine 里面的字符构成。
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> data = new HashMap<>();
        char temp;
        for (int i = 0; i < ransomNote.length(); i++) {
            temp = ransomNote.charAt(i);
            data.put(temp, data.getOrDefault(temp, 0) + 1);
        }

        for (int i = 0; i < magazine.length(); i++) {
            temp = magazine.charAt(i);
            if (data.containsKey(temp)) {
                int now = data.get(temp);
                if (now <= 1) {
                    data.remove(temp);
                } else {
                    data.put(temp, now - 1);
                }
            }
        }
        return data.isEmpty();
    }

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}

/*


 */
