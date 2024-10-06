package com.ng.ngleetcode.old.model.code

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.chad.library.adapter.base.entity.node.BaseNode
import com.ng.base.BaseViewModel
import com.ng.base.utils.MLog
import com.ng.base.utils.SPreference
import com.ng.code.util.IdGenerator
import com.ng.ngleetcode.old.app.MyApplication
import com.ng.ngleetcode.old.constants.SpConstants
import com.ng.ngleetcode.old.model.code.bean.CodeDirNode
import com.ng.ngleetcode.old.model.code.bean.CodeNode
import com.ng.ngleetcode.old.model.code.bean.CodeProgress
import com.ng.ngleetcode.old.model.code.bean.CodeProgressNode
import com.ng.ngleetcode.old.model.code.data.CodeState
import com.ng.ngleetcode.old.model.code.data.ProblemRepository
import java.io.IOException
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
    val menuLiveData: LiveData<List<BaseNode>> get() = _menuLiveData
    private val _menuLiveData = MutableLiveData<List<BaseNode>>()

    /**
     * 当前显示题
     */
    val codeLiveData = MutableLiveData<CodeNode>()

    /**
     * 当前进度
     */
    val progressLiveData = MutableLiveData<CodeProgress>()


    /**
     * 过滤后的题库集合
     */
    private var targetCodeList: MutableList<CodeNode> = ArrayList()

    /**
     * 当前显示的题 index
     */
    private var nowCodeIndex: Int = 0

    /**
     * 本地题库内存缓存
     */
    private var mLocalCodeData: List<BaseNode> = ArrayList()

    //状态sp
    private var mSpCodeStateListJsonStr: String by SPreference(SpConstants.CODE_STATE, "")

    /**
     * 题库阅读状态
     */
    private var mCodeStateList: MutableList<CodeState> = ArrayList()

    //题库阅读状态, 每次赋值以后要设置为true
    private var needRefresh = false

    /**
     * 刷新左侧题库
     */
    fun refreshDataList() {
        val context: Context = getApplication()
        //获得状态二级缓存
        refreshLocalCodeStateList()
        //题库 内存缓存
        if (mLocalCodeData.isEmpty()) {
            val dirList: MutableList<BaseNode> = ArrayList()
            dirList.add(CodeProgressNode())
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
        _menuLiveData.value = mLocalCodeData
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
     * 得到一个随机的题
     */
    fun refreshRandomCode() {
        //区分目录，得到题目
        val codeList: MutableList<CodeNode> = ArrayList()
        val menuList = menuLiveData.value ?: return

        menuList.filterIsInstance<CodeDirNode>().forEach { menuNode ->
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
        MLog.d("得到随机题 : $nowCodeIndex")
        codeLiveData.value = randomCode
    }

    fun showLeftCode(): Boolean {
        val targetIndex = nowCodeIndex - 1
        if (targetIndex < 0) {
            return false
        }
        nowCodeIndex--
        MLog.d("showLeftCode : $nowCodeIndex")
        codeLiveData.value = targetCodeList[nowCodeIndex]
        return true
    }

    fun showRightCode(): Boolean {
        val targetIndex = nowCodeIndex + 1
        if (targetIndex >= targetCodeList.size) {
            return false
        }
        nowCodeIndex++
        MLog.d("showRightCode : $nowCodeIndex")
        codeLiveData.value = targetCodeList[nowCodeIndex]
        return true
    }

    fun refreshCode(codeBean: CodeNode?) {
        if (codeBean == null || codeBean.title.isEmpty()) {
            return
        }
        val title = codeBean.title
        for (i in targetCodeList.indices) {
            if (title.contains(targetCodeList[i].title) || targetCodeList[i].title.contains(title)) {
                nowCodeIndex = i
            }
        }
        MLog.d("refreshCode : $nowCodeIndex")
        codeLiveData.value = codeBean!!
    }

    /**
     * 刷新进度
     */
    fun refreshProgress() {
        val codeList = ProblemRepository.getAssetsJavaCodeList(getApplication())
        var easyCount = 0
        var hardCount = 0
        var readCount = 0
        for (codeStr in codeList) {
            //@Solution(easy = 0, hard = 0)
            val content = ProblemRepository.readAssets(getApplication(), codeStr)
            if (content!!.contains("@Solution")) {
                val easyIndex = content.indexOf("easy = ")
                if (content.substring(easyIndex + 7, easyIndex + 8).toInt() > 0) {
                    easyCount++
                }
                val hardIndex = content.indexOf("hard = ")
                if (content.substring(hardIndex + 7, hardIndex + 8).toInt() > 0) {
                    hardCount++
                }
            }
        }
        for ((_, state) in mCodeStateList) {
            if (state == 1) {
                readCount++
            }
        }
        val allPro = codeList.size
        val nowPro = readCount
        progressLiveData.value = CodeProgress(allPro, nowPro)
    }

    fun toggleState() {
        val nowCodeData = codeLiveData.value ?: return
        val state = if (nowCodeData.state == 1) 0 else 1
        nowCodeData.state = state
        //刷新内存数据
        targetCodeList[nowCodeIndex] = nowCodeData
        //ui响应
        codeLiveData.value = nowCodeData
        //刷新持久层数据
        saveToLocalState(nowCodeData.title, state)
        refreshProgress()
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