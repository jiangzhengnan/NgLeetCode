package com.ng.code.util.model;

import java.io.Serializable;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/03/21
 * @description :
 */
public class CodeState implements Serializable {
    public int id;
    public int state;

    public CodeState() {
    }

    public CodeState(int id, int state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", state=" + state +
                '}';
    }
}
