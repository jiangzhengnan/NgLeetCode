package com.ng.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.BasePracticeClass;

public class PracticeClass extends BasePracticeClass {

    @Override
    public void run() {
        List<String> data = new ArrayList<>();
        data.add("a");
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i< n;i++) {
            if(s.charAt(i) != '0'){
                dp[i] += dp[i - 1];
            }

            if(i > 1 && s.charAt(i - 2) != '0') {
                int temp = (s.charAt(i - 2) - '0') * 10 +
                    (s.charAt(i - 1) - '0');
                if(temp <= 26) {
                    dp[i] += dp[i - 2];
                }

            }

        }
        return dp[n];

    }


}
