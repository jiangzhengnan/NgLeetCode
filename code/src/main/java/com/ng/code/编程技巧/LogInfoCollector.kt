package com.ng.code.编程技巧


class LogInfoCollector {

  val params = LinkedHashMap<String, Any?>()

  private var clueIndex = 0

  fun addClue(msg: Any) {
    params["clue_$clueIndex"] = msg
    clueIndex++
  }

  fun addClue(key: String, value: Any?) {
    params[key] = value
  }

  companion object {
    fun <T> runWithLog(logTag: String, desc: String, block: LogInfoCollector.() -> T): T {

      val collector = LogInfoCollector()
      val result = block(collector)

      collector.addClue("result", result)

      //LiveDebugLoggerV2.i(logTag, desc, "params", collector.params)

      return result
    }
  }
}
//
//  用到的地方
// fun isScopedStrategyEnabled(photo: LiveStreamFeedWrapper?, key: String): Boolean =
//  LogInfoCollector.runWithLog(LiveLogTag.LIVE_AUDIENCE_BOTTOM_BAR, "isScopedStrategyEnabled") {
//
//    addClue("switchKey", key)
//
//    val (mainTypeList, enable) = getScopedEnableStrategy(key)
//
//    addClue("switch.mainType", mainTypeList)
//    addClue("switch.enable", enable)
//
//    if (!enable) {
//      return@runWithLog false
//    }
//
//    if (mainTypeList == null || mainTypeList.isEmpty()) {
//      // 没法下代表 全局生效
//      addClue("global enable")
//      return@runWithLog true
//    }
//
//    val feedMainType: Int? = getFeedMainType(photo)
//    addClue("feed.mainType", feedMainType)
//    if (feedMainType == null) {
//      // feed 没下发type, 无法匹配
//      return@runWithLog false
//    }
//
//    return@runWithLog mainTypeList.contains(feedMainType)
//  }