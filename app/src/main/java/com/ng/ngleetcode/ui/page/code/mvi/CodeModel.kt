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
import java.util.*

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

  /**
   * 过滤后的题库集合
   */
  private var targetCodeList: MutableList<CodeNode> = ArrayList()

  /**
   * 当前显示的题 index
   */
  private var nowCodeIndex: Int = 0

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

  fun getRandomCode(): CodeNode {
    //区分目录，得到题目
    val codeList: MutableList<CodeNode> = ArrayList()
    mLocalCodeData.forEach { menuNode ->
      menuNode.childNode.filterIsInstance<CodeNode>().forEach {
        codeList.add(it)
      }
    }
    //得到未读的
    val unReadList: MutableList<CodeNode> = ArrayList()
    codeList.toList().forEach {
      val state = getCodeState(it.title)
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
    randomCode.state = getCodeState(randomCode.title)
    randomCode.content =
      ProblemRepository.readAssets(MyApp.CONTEXT, randomCode.contentPath).toString()
    MLog.d("得到随机题 : $randomCode")
    return randomCode
  }

  fun getCode(codeBean: CodeNode?): CodeNode? {
    if (codeBean == null || codeBean.title.isEmpty()) {
      return null
    }
    val title = codeBean.title
    for (i in targetCodeList.indices) {
      if (title.contains(targetCodeList[i].title) || targetCodeList[i].title.contains(title)) {
        nowCodeIndex = i
      }
    }
    codeBean.content =
      ProblemRepository.readAssets(MyApp.CONTEXT, codeBean.contentPath).toString()
    MLog.d("得到某个题 : $codeBean")
    return codeBean
  }

  fun getLeftCode(): CodeNode? {
    val targetIndex = nowCodeIndex - 1
    if (targetIndex < 0) {
      return null
    }
    nowCodeIndex--
    MLog.d("showLeftCode : $nowCodeIndex")
    return targetCodeList[nowCodeIndex].apply {
      content = ProblemRepository.readAssets(MyApp.CONTEXT, contentPath).toString()
    }
  }

  fun getRightCode(): CodeNode? {
    val targetIndex = nowCodeIndex + 1
    if (targetIndex >= targetCodeList.size) {
      return null
    }
    nowCodeIndex++
    MLog.d("showRightCode : $nowCodeIndex")
    return targetCodeList[nowCodeIndex].apply {
      content = ProblemRepository.readAssets(MyApp.CONTEXT, contentPath).toString()
    }
  }

  fun toggleState(nowCodeData: CodeNode?): CodeNode? {
    if (nowCodeData == null) {
      return null
    }
    val state = if (nowCodeData.state == 1) 0 else 1
    nowCodeData.state = state
    //刷新内存数据
    targetCodeList[nowCodeIndex] = nowCodeData
    //刷新持久层数据
    saveToLocalState(nowCodeData.title, state)
    return nowCodeData
  }
}