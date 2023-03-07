package com.ng.train.monitor;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ng.train.monitor.utils.AdUtil;
import com.ng.train.monitor.utils.NodeUtil;
import com.ng.train.monitor.utils.StringUtils;
import com.ng.train.monitor.utils.TimeLog;
import org.json.JSONObject;

/**
 * @author :
 * @creation : 2022/12/16
 * @description :
 * 内存态时间节点监测记录
 */
public class CtMonitor {
    @NonNull
    private final Map<String, CtNode> nodeMap = CtBuilder.generateCtMap();

    @NonNull
    public Map<String, CtNode> getNodeMap() {
        return nodeMap;
    }

    /**
     * 清空数据
     */
    public void reset() {
        nodeMap.clear();
    }

    public void start(@NonNull CtType type) {
        start(type, null, null);
    }

    public void end(@Nullable String key) {
        end(key, null);
    }


    public void start(@NonNull CtType type,
                      @Nullable String key,
                      @Nullable Map<String, String> extraMap) {
        if (!GlobalCtManager.mEnable) {
            return;
        }
        if (StringUtils.isEmpty(key)) {
            key = type.type;
        }
        CtNode node = nodeMap.get(key);
        if (node == null) {
            node = new CtNode(type);
        }
        if (extraMap != null) {
            node.extraMap = extraMap;
        }
        node.startTime = System.currentTimeMillis();
        nodeMap.put(key, node);
        TimeLog.print("record: " + type + key);
    }


    public void end(@Nullable String key, @Nullable Map<String, String> extraMap) {
        if (!GlobalCtManager.mEnable) {
            return;
        }
        CtNode nowNode = nodeMap.get(key);
        if (nowNode == null) {
            return;
        }
        if (extraMap != null) {
            nowNode.extraMap = extraMap;
        }
        nowNode.endTime = System.currentTimeMillis();
        TimeLog.print("addTask: " + key);
    }

    public void endAsChild(@Nullable String parentKey,
                           @Nullable String key) {
        endAsChild(parentKey, key, null);
    }

    public void endAsChild(@Nullable String parentKey,
                           @Nullable String key,
                           @Nullable Map<String, String> extraMap) {
        if (!GlobalCtManager.mEnable) {
            return;
        }
        CtNode parentNode = nodeMap.get(parentKey);
        CtNode nowNode = nodeMap.get(key);
        if (parentNode == null || nowNode == null) {
            TimeLog.print("addChildTask, fail " + (parentNode == null) + " " + (nowNode == null));

            return;
        }
        nowNode.endTime = System.currentTimeMillis();
        if (extraMap != null) {
            nowNode.extraMap = extraMap;
        }
        nowNode.parent = parentNode;
        parentNode.childList.add(nowNode);
        TimeLog.print("addChildTask:" + key + " to:" + parentKey);
    }

    /**
     * 填充广告竞价耗时信息
     * 同层下不同adn等待耗时可以通过:层耗时-ad耗时计算得出
     * 同域下不同层等待耗时可以通过:域耗时-层耗时计算得出
     */
    @NonNull

    public HashMap getAdnCostTimeInfo(final int adnId, @NonNull final String placementId) {
        HashMap ctJson = new HashMap<String, String>();
        if (!GlobalCtManager.mEnable) {
            return ctJson;
        }
        try {

            Map<String, CtNode> tempNodeMap = new HashMap<>(nodeMap);
            for (String key : tempNodeMap.keySet()) {
                CtNode node = tempNodeMap.get(key);
                if (node == null || node.endTime == 0) {
                    continue;
                }
                ctJson.put(node.type.type, node.getCostTime() + "");

            }
        } catch (Exception e) {
            TimeLog.print(e.getMessage());
            e.printStackTrace();
        }
        NodeUtil.mergeCtMap(nodeMap, GlobalCtManager.INSTANCE.getMonitor().getNodeMap());
        TimeLog.print("填充广告竞价耗时信息: adnId:" + adnId + " placementId:" + placementId + " ctJson:" + ctJson);
        TimeLog.print(NodeUtil.transformLog(nodeMap));

        return ctJson;
    }

    /**
     * 填充广告返回耗时信息
     *
     * @param adnId 如果没有竞价胜出为-1
     */

    @Nullable
    public Map<String, String> getRespCostTimeInfo(final int adnId) {
        if (!GlobalCtManager.mEnable) {
            return null;
        }
        Map<String, String> respCtInfo = new HashMap<>();
        try {
            //1.全局耗时统计
            NodeUtil.mergeCtMap(nodeMap, GlobalCtManager.INSTANCE.getMonitor().getNodeMap());

            //2.mediation模块与广告生命周期耗时统计
            Map<String, CtNode> ctMap = new HashMap<>(nodeMap);
            for (String key : ctMap.keySet()) {
                CtNode node = ctMap.get(key);
                if (node == null || node.endTime == 0 || node.type == CtType.adnLoad) {
                    continue;
                }
                //过滤竞价部分节点
                if (node.type == CtType.fetchAd ||
                    node.type == CtType.areaBid ||
                    node.type == CtType.levelBid ||
                    node.type == CtType.adRequest ||
                    node.type == CtType.adnInit) {
                    continue;
                }
                //加载插件耗时需要匹配adnId
                if (node.type == CtType.plugInstall) {
                    if (node.extraMap != null &&
                        AdUtil.isContainsInModules(adnId, node.extraMap.get(CtConstant.Key.INSTALL_MODULES))) {
                        respCtInfo.put(node.type.type, node.getCostTime() + "");
                        String isOat = node.extraMap.get(CtConstant.Key.IS_OAT);
                        respCtInfo.put(CtConstant.Key.IS_OAT, isOat == null ? "null" : isOat);
                    }
                    continue;
                }
                respCtInfo.put(node.type.type, node.getCostTime() + "");
            }

            if (respCtInfo.size() > 0) {
                TimeLog.print("填充广告返回耗时信息: adnId:" + adnId + " getRespCostTimeInfo:" + respCtInfo);
                TimeLog.print(NodeUtil.transformLog(nodeMap));
                return respCtInfo;
            }
        } catch (Exception e) {
            TimeLog.print(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
