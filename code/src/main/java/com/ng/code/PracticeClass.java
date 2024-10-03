package com.ng.code;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.base.utils.TreeNode;
import com.ng.code.util.BasePracticeClass;

public class PracticeClass extends BasePracticeClass {

    @Override
    public void run() {
        LogUtil.print(findNthDigit(10));
    }

    public int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }

        int result = 0;
        String numStr = "";
        for (int i = 1; i <= ((n - 10) / 2 + 10); i++) {
            numStr += i;
        }
        result = Integer.parseInt(numStr.charAt(n - 1) + "");

        return result;
    }


}
