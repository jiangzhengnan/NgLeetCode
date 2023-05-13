package com.ng.code.work.train.monitor;

import androidx.annotation.NonNull;

import com.ng.code.work.train.monitor.utils.NodeUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 
 * @creation : 2022/12/23
 * @description :
 * 维护内存态耗时关系 (竞价模块需要动态添加)
 */
public class CtBuilder {

    @NonNull
    public static Map<String, CtNode> generateCtMap() {
        Map<String, CtNode> ctMap = new ConcurrentHashMap<>();
        CtNode root = new CtNode(CtType.root);

        //媒体侧广告耗时模块
        CtNode appAdCtNode = new CtNode(CtType.appAdCt);
        appAdCtNode.addChild(new CtNode(CtType.readCms));

        //NoahSdk初始化模块
        CtNode noahSdkInitNode = new CtNode(CtType.noahSdkInit);
        noahSdkInitNode.addChild(new CtNode(CtType.initConfigModel));
        noahSdkInitNode.addChild(new CtNode(CtType.initCommonParamsModel));
        noahSdkInitNode.addChild(new CtNode(CtType.preInitUCPangolinSdk));

        //插件化耗时模块
        CtNode plugInitNode = new CtNode(CtType.plugInit);
        plugInitNode.addChild(new CtNode(CtType.plugInstall));

        //ssp模块
        CtNode sspNode = new CtNode(CtType.ssp);
        sspNode.addChild(new CtNode(CtType.fetchHttpSsp));
        sspNode.addChild(new CtNode(CtType.fetchHttpSspRespParse));
        sspNode.addChild(new CtNode(CtType.saveHttpSspResp));

        //广告生命周期
        CtNode adLifeCycleNode = new CtNode(CtType.adLifeCycle);
        adLifeCycleNode.addChild(new CtNode(CtType.loadToLoaded));

        //竞价模块需要动态添加
        CtNode fetchAdNode = new CtNode(CtType.fetchAd);

        //所有节点添加到query映射表
        root.addChild(appAdCtNode);
        root.addChild(noahSdkInitNode);
        root.addChild(plugInitNode);
        root.addChild(sspNode);
        root.addChild(adLifeCycleNode);
        root.addChild(fetchAdNode);
        NodeUtil.addNodeToMap(root, ctMap);
        return ctMap;
    }

    public static void main(String[] args) {
        String log = NodeUtil.transformLog(generateCtMap());
        System.out.println(log);
    }
}
