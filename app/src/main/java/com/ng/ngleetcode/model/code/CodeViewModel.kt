package com.ng.ngleetcode.model.code

import androidx.lifecycle.MutableLiveData
import com.chad.library.adapter.base.entity.node.BaseNode
import com.ng.base.BaseViewModel
import com.ng.code.util.model.CodeDataModel
import com.ng.ngleetcode.MyApplication

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/12
 *    @description   :
 */
class CodeViewModel : BaseViewModel(MyApplication.instance) {

    /**
     * 题库集合
     */
    val codeLiveData = MutableLiveData<List<BaseNode>>()

    /**
     * 刷新当前进度
     */
    fun refreshData() {
        codeLiveData.value = CodeDataModel.getInstance().getCodeData(getApplication())
    }


}