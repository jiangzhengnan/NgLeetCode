package com.ng.ngleetcode.old.model.code.adapter.provider

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.alibaba.fastjson.JSON
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ng.base.utils.SPreference
import com.ng.ngleetcode.R
import com.ng.ngleetcode.old.constants.SpConstants
import com.ng.ngleetcode.old.model.code.data.CodeState
import com.ng.ngleetcode.old.model.code.data.ProblemRepository

class HeadProvider : BaseNodeProvider() {
    override val itemViewType = 3
    override val layoutId = R.layout.item_node_first
    private var mSpCodeStateListJsonStr: String by SPreference(SpConstants.CODE_STATE, "")

    /**
     * 题库阅读状态
     */
    private var mCodeStateList: MutableList<CodeState> = ArrayList()

    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        //helper.setText(R.id.navHeaderMainDesc, ProblemAndroidUtil.getNowProgressAndroid(context))

        if (mSpCodeStateListJsonStr.isNotEmpty()) {
            mCodeStateList = JSON.parseArray(mSpCodeStateListJsonStr, CodeState::class.java)
        }
        val codeList = ProblemRepository.getAssetsJavaCodeList(context)
        var easyCount = 0
        var easyRead = 0

        var midCount = 0
        var midRead = 0

        var hardCount = 0
        var hardRead = 0

        for (codeStr in codeList) {
            if (codeStr.contains("Ⅰ_")) {
                easyCount++
                if (hadRead(codeStr)) {
                    easyRead++
                }
            } else if (codeStr.contains("Ⅱ_")) {
                midCount++
                if (hadRead(codeStr)) {
                    midRead++
                }
            } else if ((codeStr.contains("Ⅲ_"))) {
                hardCount++
                if (hadRead(codeStr)) {
                    hardRead++
                }
            }
        }
//
//        helper.getView<CircularProgressView>(R.id.progress_circular).setValue(
//            (easyRead / easyCount.toFloat()),
//            (midRead / midCount.toFloat()),
//            (hardRead / hardCount.toFloat())
//        )

        helper.getView<TextView>(R.id.progress_easy).text = "$easyRead/$easyCount"
        helper.getView<TextView>(R.id.progress_mid).text = "$midRead/$midCount"
        helper.getView<TextView>(R.id.progress_hard).text = "$hardRead/$hardCount"

    }

    private fun hadRead(codeStr: String): Boolean {
        for (i in mCodeStateList.indices) {
            if (codeStr.contains(mCodeStateList[i].name) || mCodeStateList[i].name.contains(codeStr)) {
                //MLog.d("getCodeState:$title $mCodeStateList[i].state")
                return mCodeStateList[i].state == 1
            }
        }
        return false
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        //getAdapter().expandOrCollapse(position, true, true, NodeTreeAdapter.EXPAND_COLLAPSE_PAYLOAD);
    }
}