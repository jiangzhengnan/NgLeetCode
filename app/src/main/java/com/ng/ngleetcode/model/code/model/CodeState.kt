package com.ng.ngleetcode.model.code.model

import java.io.Serializable

/**
 * @author :
 * @creation : 2022/03/21
 * @description :
 */
data class CodeState(var name: String, var state: Int) : Serializable {

    constructor() : this("",0)
}