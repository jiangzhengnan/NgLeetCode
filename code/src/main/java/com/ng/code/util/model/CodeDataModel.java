package com.ng.code.util.model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ng.base.LogUtil;
import com.ng.code.util.ProblemAndroidUtil;
import com.ng.code.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 
 * @creation : 2022/03/21
 * @description :
 */
public class CodeDataModel {
    public final static String CODE_STATE = "code_state";

    @NonNull
    public List<BaseNode> mCodeData;

    @NonNull
    public List<CodeState> mCodeStateList = new ArrayList<>();

    //每次赋值以后要设置为true
    private boolean needRefresh = false;

    private static CodeDataModel sInstance = null;

    public static CodeDataModel getInstance() {
        if (sInstance == null) {
            sInstance = new CodeDataModel();
        }
        return sInstance;
    }


    private CodeDataModel() {
        init();
    }

    private void init() {
        mCodeData = new ArrayList<>();
    }

    /**
     * 先从内存取
     * 取不到从本地目录读
     */
    @NonNull
    public List<BaseNode> getCodeData(Context context) {
        //memory
        if (mCodeData.size() > 0) {
            return mCodeData;
        }
        //sp
        mCodeData = ProblemAndroidUtil.getJavaCodeList(context);
        refreshLocalCodeStateList(context);
        return mCodeData;
    }

    public int loopCodeState(@NonNull Context context, String title, int state) {
        for (int i = 0; i < mCodeStateList.size(); i++) {
            if (title.contains(mCodeStateList.get(i).name) || mCodeStateList.get(i).name.contains(title)) {
                if (state != -1) {
                    mCodeStateList.get(i).state = state;
                    saveLocalCodeStateList(context);
                    LogUtil.d("刷新成功:" + title + " " + state);
                }
                return mCodeStateList.get(i).state;
            }
        }
        LogUtil.d("刷新失败:" + title + " " + state);
        return -1;
    }

    private void refreshLocalCodeStateList(@NonNull Context context) {
        if (mCodeStateList.size() == 0 || needRefresh) {
            String codeStateListJsonStr = SharedPreferencesUtils.getString(context, CODE_STATE, "");
            mCodeStateList = JSON.parseArray(codeStateListJsonStr, CodeState.class);
        }
    }

    public boolean isLocalCodeStateListIsNull(Context context) {
        return SharedPreferencesUtils.getString(context, CODE_STATE, "").equals("");
    }

    public void saveLocalCodeStateList(@NonNull Context context) {
        needRefresh = true;
        String codeStateListJsonStr = JSON.toJSONString(mCodeStateList);
        SharedPreferencesUtils.saveString(context, CODE_STATE, codeStateListJsonStr);
        refreshLocalCodeStateList(context);
    }

}
