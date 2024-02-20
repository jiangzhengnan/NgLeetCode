package com.cj.design.uc.monitor;

import androidx.annotation.NonNull;

/**
 * @author : 
 * @creation : 2022/12/08
 * @description :
 * 全局耗时统计模型管理器
 */
public enum GlobalCtManager {
    INSTANCE;

    private static final CtMonitor M_GLOBAL_CT_MONITOR = new CtMonitor();

    public static boolean mEnable = true;

    @NonNull
    public CtMonitor getMonitor() {
        return M_GLOBAL_CT_MONITOR;
    }
}
