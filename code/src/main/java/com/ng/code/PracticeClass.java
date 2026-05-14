package com.ng.code;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.BasePracticeClass;

public class PracticeClass extends BasePracticeClass {

    @Override
    public void run() {
        int[] array = new int[]{9, 7, 6, 5, 3, 1, 10, 4, 9, 8, -1};
        sort(array);
        LogUtil.print(array);
    }

    private void sort(int[] array) {
    }


}
