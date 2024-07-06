package com.ng.code.util;

import com.ng.code.PracticeClass;

public abstract class BasePracticeClass {

    public abstract void run();

    public static void main(String[] args) {
        BasePracticeClass basePracticeClass = new PracticeClass();
        basePracticeClass.run();
    }

}
