package com.ng.train.monitor.utils;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ng.train.monitor.CtNode;
import com.ng.train.monitor.CtType;

/**
 * @author : 
 * @creation : 2022/12/14
 * @description :
 */
public class NodeUtil {

    public static void addNodeToMap(@Nullable final CtNode node, @NonNull final Map<String, CtNode> ctMap) {
        if (node == null) {
            return;
        }
        ctMap.put(node.type.type, node);
        for (CtNode temp : node.childList) {
            addNodeToMap(temp, ctMap);
        }
    }

    @NonNull
    public static String transformLog(@NonNull Map<String, CtNode> ctMap) {
        //找到根节点
        CtNode rootNode = ctMap.get(CtType.root.type);
        if (rootNode == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        try {
            transformLogLoop(rootNode, res, 0, true);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }

    private static void transformLogLoop(@Nullable final CtNode node, @NonNull final StringBuilder res, int interval, boolean isRoot) {
        if (node == null) {
            return;
        }
        interval++;
        StringBuilder intervalStr = new StringBuilder();
        for (int i = 0; i < interval; i++) {
            intervalStr.append(" ");
        }
        if (!isRoot) {
            intervalStr.append("↳ ");
        }

        StringBuilder desc = new StringBuilder(node.type.desc);
        if (node.type == CtType.areaBid ||
            node.type == CtType.levelBid ||
            node.type == CtType.adRequest) {
            Map<String, String> extra = node.extraMap;
            if (extra != null) {
                for (String key : extra.keySet()) {
                    desc.append(" ").append(key).append(":").append(extra.get(key));
                }
            }
        }

        res.append("\n")
           .append(intervalStr)
           .append(node.type.type).append(" (").append(desc).append(")");
        if (!isRoot) {
            res.append(":")
               .append(node.getCostTime()).append("ms");
        }

        for (CtNode child : node.childList) {
            transformLogLoop(child, res, node.type.type.length() + interval, false);
        }
    }

    /**
     * 补充耗时信息
     */
    public static void mergeCtMap(@NonNull final Map<String, CtNode> ctMap1, @NonNull final Map<String, CtNode> ctMap2) {
        for (String key : ctMap1.keySet()) {
            CtNode node1 = ctMap1.get(key);
            CtNode node2 = ctMap2.get(key);
            if (node1 == null || node2 == null) {
                continue;
            }
            if (node1.getCostTime() == 0 && node2.getCostTime() > 0) {
                node1.startTime = node2.startTime;
                node1.endTime = node2.endTime;
            }

        }
    }
}
