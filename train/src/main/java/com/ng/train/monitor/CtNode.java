package com.ng.train.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author : 
 * @creation : 2022/12/07
 * @description :
 * 耗时结点
 * + executeTime:long  执行时间
 * + limitTime:long  超时时间
 * + state:int  状态码
 * + type:int  耗时节点类型
 * + extraMap:Map<String, Object> 额外字段信息
 * + pre:CtNode 前一个节点
 * + next:CtNode 后一个节点
 * + parent:CtNode 父节点
 * + childList:List<CtNode> 子节点集合
 */
public class CtNode {

    /**
     * 开始时间
     */
    public long startTime;

    /**
     * 结束时间
     */
    public long endTime;

    /**
     * 类型
     */
    @NonNull
    public CtType type;

    /**
     * 额外字段信息
     */
    @Nullable
    public Map<String, String> extraMap;

    @Nullable
    public CtNode parent;
    @NonNull
    public List<CtNode> childList = new LinkedList<>();

    public CtNode(@NonNull final CtType type) {
        this.type = type;
    }

    public long getCostTime() {
        long costTime = endTime - startTime;
        if (costTime < 0) {
            return 0;
        }
        if (costTime > 20 * 1000) {
            return 0;
        }
        return costTime;
    }

    public void addChild(@NonNull CtNode node) {
        childList.add(node);
    }


    @Override
    public String toString() {
        return "CtNode{" +
               "startTime=" + startTime +
               ", endTime=" + endTime +
               ", type=" + type +
               ", extraMap=" + extraMap +
               '}';
    }
}
