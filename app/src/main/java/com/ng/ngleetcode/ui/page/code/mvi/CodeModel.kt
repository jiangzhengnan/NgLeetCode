package com.ng.ngleetcode.ui.page.code.mvi

import android.content.Context
import com.alibaba.fastjson.JSON
import com.chad.library.adapter.base.entity.node.BaseNode
import com.ng.base.utils.MLog
import com.ng.base.utils.SPreference
import com.ng.code.util.IdGenerator
import com.ng.ngleetcode.MyApp
import com.ng.ngleetcode.old.constants.SpConstants
import com.ng.ngleetcode.old.model.code.bean.CodeDirNode
import com.ng.ngleetcode.old.model.code.bean.CodeNode
import com.ng.ngleetcode.old.model.code.data.CodeState
import com.ng.ngleetcode.old.model.code.data.ProblemRepository
import java.io.IOException

// todo jzn 待重构，目前逻辑很混乱，可以通过协程优化
class CodeModel {

  //状态sp
  private var mSpCodeStateListJsonStr: String by SPreference(SpConstants.CODE_STATE, "")

  /**
   * 本地题库内存缓存
   */
  private var mLocalCodeData: List<CodeDirNode> = ArrayList()

  /**
   * 题库阅读状态
   */
  private var mCodeStateList: MutableList<CodeState> = ArrayList()

  //题库阅读状态, 每次赋值以后要设置为true
  private var needRefresh = false


  fun getProgressData(): Array<IntArray> {
    val codeList = ProblemRepository.getAssetsJavaCodeList(MyApp.CONTEXT)
    var easyCount = 0
    var easyRead = 0
    var midCount = 0
    var midRead = 0
    var hardCount = 0
    var hardRead = 0

    var nowPro = 0
    var allPro = 1


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
    for ((_, state) in mCodeStateList) {
      if (state == 1) {
        nowPro++
      }
    }
    allPro = 1.coerceAtLeast(easyCount + midCount + hardCount)
    return arrayOf(
      intArrayOf(easyRead, easyCount),
      intArrayOf(midRead, midCount),
      intArrayOf(hardRead, hardCount),
      intArrayOf(nowPro, allPro),
    )
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

  /**
   * 题库列表数据
   */
  fun getDataList(): List<CodeDirNode> {
    val context: Context = MyApp.CONTEXT
    //获得状态二级缓存
    refreshLocalCodeStateList()
    //题库 内存缓存
    if (mLocalCodeData.isEmpty()) {
      val dirList: MutableList<CodeDirNode> = ArrayList()
      if (mSpCodeStateListJsonStr.isEmpty()) {
        mCodeStateList.clear()
      }
      try {
        val files = context.assets.list("")
        for (temp in files!!) {
          val codeList: MutableList<BaseNode> = ArrayList()
          var needAdd = false
          for (subTemp in context.assets.list(temp)!!) {
            val realFilePath = "$temp/$subTemp"
            if (realFilePath.endsWith(".java")) {
              val id = IdGenerator.generateID()
              val name = subTemp.replace(".java", "")
              if (mSpCodeStateListJsonStr.isEmpty()) {
                mCodeStateList.add(CodeState(name, -1))
              }
              val node = CodeNode(name, id, realFilePath, "")
              node.state = getCodeState(name)
              codeList.add(node)
              needAdd = true
            }
          }
          if (needAdd) {
            val dirNode = CodeDirNode(codeList, temp)
            dirList.add(dirNode)
          }
        }
      } catch (e: IOException) {
        e.printStackTrace()
      }
      if (mSpCodeStateListJsonStr.isEmpty()) {
        saveLocalCodeStateList()
      }
      mLocalCodeData = dirList
    }
    return mLocalCodeData
  }

  private fun refreshLocalCodeStateList() {
    if (mSpCodeStateListJsonStr.isEmpty()) {
      return
    }
    if (mCodeStateList.isEmpty() || needRefresh) {
      mCodeStateList = JSON.parseArray(mSpCodeStateListJsonStr, CodeState::class.java)
    }
  }

  /**
   * 保存至持久层数据
   */
  private fun saveToLocalState(title: String?, state: Int) {
    if (title == null || title.isEmpty()) {
      return
    }
    for (i in mCodeStateList.indices) {
      if (title.contains(mCodeStateList[i].name) || mCodeStateList[i].name.contains(title)) {
        mCodeStateList[i].state = state
      }
    }
    saveLocalCodeStateList()
  }

  private fun getCodeState(title: String?): Int {
    if (title.isNullOrEmpty()) {
      return -1
    }
    for (i in mCodeStateList.indices) {
      if (title!!.contains(mCodeStateList[i].name) || mCodeStateList[i].name.contains(title)) {
        //MLog.d("getCodeState:$title $mCodeStateList[i].state")
        return mCodeStateList[i].state
      }
    }
    MLog.d("getCodeState 失败:$title")
    return -1
  }

  private fun saveLocalCodeStateList() {
    if (mCodeStateList.isEmpty()) {
      MLog.d("saveLocalCodeStateList 刷新失败, mCodeStateList为空")
    }
    needRefresh = true
    mSpCodeStateListJsonStr = JSON.toJSONString(mCodeStateList)
  }
}