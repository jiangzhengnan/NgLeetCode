package com.ng.code.util.model;

import java.io.Serializable;

/**
 * @author : 
 * @creation : 2022/03/21
 * @description :
 */
public class CodeState implements Serializable {
    public String name;
    public int state;

    public CodeState() {
    }

    public CodeState(String name, int state) {
        this.name = name;
        this.state = state;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CodeState{" +
                "name=" + name +
                ", state=" + state +
                '}';
    }
}
