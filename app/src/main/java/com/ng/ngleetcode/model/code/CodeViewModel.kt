package com.ng.ngleetcode.model.code

import androidx.lifecycle.MutableLiveData
import com.chad.library.adapter.base.entity.node.BaseNode
import com.ng.base.BaseViewModel
import com.ng.ngleetcode.app.MyApplication
import com.ng.ngleetcode.model.code.bean.CodeDirNode
import com.ng.ngleetcode.model.code.bean.CodeNode
import com.ng.ngleetcode.model.code.bean.CodeProgress
import com.ng.ngleetcode.model.code.data.CodeDataModel
import com.ng.ngleetcode.model.code.data.ProblemAndroidUtil
import java.util.*

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/12
 *    @description   :
 */
class CodeViewModel : BaseViewModel(MyApplication.instance) {

    /**
     * 题库集合
     */
    val menuLiveData = MutableLiveData<List<BaseNode>>()

    /**
     * 当前显示题
     */
    val codeLiveData = MutableLiveData<CodeNode>()

    /**
     * 当前进度
     */
    val progressLiveData = MutableLiveData<CodeProgress>()

    /**
     * 刷新左侧题库
     */
    fun refreshDataList() {
        menuLiveData.value = CodeDataModel.getInstance().getCodeData(getApplication())
    }

    var targetCodeList: MutableList<CodeNode> = ArrayList()

    var nowCodeIndex: Int = 0

    /**
     * 得到一个随机的题
     */
    fun refreshRandomCode() {
        //区分目录，得到题目
        val codeList: MutableList<CodeNode> = ArrayList()
        val menuList = menuLiveData.value ?: return

        menuList.filter { menuNode ->
            menuNode is CodeDirNode && menuNode.childNode != null
        }.forEach { menuNode ->
            menuNode.childNode!!.filterIsInstance<CodeNode>().forEach {
                codeList.add(it)
            }
        }

        //得到未读的
        val unReadList: MutableList<CodeNode> = ArrayList()
        codeList.toList().forEach {
            val state = CodeDataModel.getInstance().loopCodeState(getApplication(), it.title, -1)
            if (state == -1) {
                unReadList.add(it)
            }
        }
        //最终题目
        if (unReadList.size > 1) {
            nowCodeIndex = Random().nextInt(unReadList.size - 1)
            targetCodeList = unReadList
        } else {
            nowCodeIndex = Random().nextInt(codeList.size - 1)
            targetCodeList = codeList
        }
        val randomCode = targetCodeList[nowCodeIndex]
        //设置状态(已读未读)
        randomCode.state =
            CodeDataModel.getInstance().loopCodeState(getApplication(), randomCode.title, -1)
        codeLiveData.value = randomCode
    }

    fun refreshLeftCode(): Boolean {
        val targetIndex = nowCodeIndex - 1
        if (targetIndex < 0) {
            return false
        }
        nowCodeIndex--
        codeLiveData.value = targetCodeList[nowCodeIndex]
        return true
    }

    fun refreshRightCode(): Boolean {
        val targetIndex = nowCodeIndex + 1
        if (targetIndex >= targetCodeList.size) {
            return false
        }
        nowCodeIndex--
        codeLiveData.value = targetCodeList[nowCodeIndex]
        return true
    }

    fun refreshCode(codeBean: CodeNode?) {
        if (codeBean == null) {
            return
        }
        codeLiveData.value = codeBean
    }

    /**
     * 刷新进度
     */
    fun refreshProgress() {
        ProblemAndroidUtil.getNowProgressAndroid(getApplication())
        val allPro = ProblemAndroidUtil.codeNum
        val nowPro = ProblemAndroidUtil.readNum
        progressLiveData.value = CodeProgress(allPro, nowPro)
    }

    fun toggleState() {
        val nowCodeData = codeLiveData.value ?: return
        val state = if (nowCodeData.state == 1) 0 else 1
        CodeDataModel.getInstance()
            .toggleCodeState(getApplication(), nowCodeData, state)
        nowCodeData.state = state
        codeLiveData.value = nowCodeData
        refreshProgress()
    }


}