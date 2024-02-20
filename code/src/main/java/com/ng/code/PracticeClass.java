package com.ng.code;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ng.base.utils.LogUtil;

public class PracticeClass {

    private void test() {
        LogUtil.print(permute(new int[]{
            1, 2, 3
        }).toString());
    }

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> now = new LinkedList<>();
        track(nums, now);
        return result;
    }

    private void track(int[] nums, LinkedList<Integer> now) {
        if (now.size() == nums.length) {
            result.add(new ArrayList<>(now));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (now.contains(nums[i])) {
                continue;
            }
            now.add(nums[i]);
            track(nums, now);
            now.removeLast();
        }

    }

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}




