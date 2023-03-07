package com.ng.train.monitor;

/**
 * @author : 
 * @creation : 2022/11/24
 * @description :
 */
public enum CtType {
    root("root", "根节点"),

    //媒体侧广告耗时
    appAdCt("app_ad_ct", "媒体侧广告模块耗时"),
    readCms("read_cms", "读取cms配置"),

    //NoahSdk初始化
    noahSdkInit("noah_sdk_init", "sdk初始化模块耗时"),
    initConfigModel("init_config_model", "初始化ConfigModel"),
    initCommonParamsModel("init_common_model", "初始化CommonParamsModel"),
    preInitUCPangolinSdk("pre_init_pangolin", "穿山甲预加载"),

    //插件化耗时
    plugInit("plug", "qigsaw初始化模块耗时"),
    plugInstall("plug_install", "插件加载"),//需要判断是否oat

    //ssp模块
    ssp("ssp", "ssp模块耗时"),
    fetchHttpSsp("m_fl_res", "ssp请求"),
    fetchHttpSspRespParse("m_fl_parse_res", "spp返回解析"),
    saveHttpSspResp("m_save", "ssp存储"),

    //广告生命周期
    adLifeCycle("ad_life_cycle", "广告生命周期模块耗时"),
    loadToLoaded("load_loaded", "广告load-loaded"),

    //竞价模块
    fetchAd("ct_fetch_ad", "广告请求总耗时"),
    areaBid("ct_area_bid", "广告所在域竞价耗时"),
    levelBid("ct_level_bid", "广告所在层竞价耗时"),
    adRequest("ct_ad_request", "adn请求耗时 "),
    adnInit("ct_adn_init", "adn初始化耗时"),
    adnLoad("ct_adn_load", "adn加载耗时"),

    ;

    public String type;

    public String desc;

    CtType(final String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CtType{" +
               "type='" + type + '\'' +
               ", desc='" + desc + '\'' +
               '}';
    }
}
