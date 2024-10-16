package com.ng.ngleetcode.ui.page.code.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.ng.ngbaselib.utils.ViewUtils
import com.ng.ngleetcode.ui.page.code.mvi.CodeDrawerViewState
import kotlin.math.min

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/19
 *    @description   :
 *    圆形进度
 */
class CircularProgressView(context: Context) : View(context) {

    private var mEasyPro = 0f
    private var mMidPro = 0f
    private var mHardPro = 0f
    private var mProWidth = 0
    private var mSpace = 0


    private val mEasyBgColor = Color.parseColor("#DCEEE0")
    private val mMidBgColor = Color.parseColor("#F4E7D4")
    private val mHardBgColor = Color.parseColor("#F1DBDA")

    private val mEasyColor = Color.parseColor("#65C467")
    private val mMidColor = Color.parseColor("#F19938")
    private val mHardColor = Color.parseColor("#EB4D3D")

    private var mPaint: Paint = Paint()

    init {
        mProWidth = ViewUtils.dip2px(getContext(), 11.5F)
        mSpace = ViewUtils.dip2px(getContext(), 1F)
        mPaint.apply {
            isAntiAlias = true
            strokeWidth = mProWidth.toFloat()
            flags = Paint.ANTI_ALIAS_FLAG
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }
    }

    fun setValue(easyPro: Float, midPro: Float, hardPro: Float) {
        this.mEasyPro = easyPro * 360F
        this.mMidPro = midPro * 360F
        this.mHardPro = hardPro * 360F
        invalidate()
    }

    fun setValue(viewData: CodeDrawerViewState) {
        if (viewData.easyCount == 0 || viewData.midCount == 0 || viewData.hardCount == 0) {
            return
        }
        this.mEasyPro = viewData.easyRead.toFloat() / viewData.easyCount.toFloat() * 360F
        this.mMidPro = viewData.midRead.toFloat() / viewData.midCount.toFloat() * 360F
        this.mHardPro = viewData.hardRead.toFloat() / viewData.hardCount.toFloat() * 360F
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (measuredHeight <= 0 || measuredWidth <= 0 || canvas == null) {
            return
        }
        val sideLength = min(measuredHeight, measuredWidth).toFloat()
        val circleR = sideLength / 2F - mProWidth / 2F

        drawCircle(canvas, sideLength, mEasyBgColor, mEasyColor, circleR, mEasyPro)
        drawCircle(
            canvas,
            sideLength,
            mMidBgColor,
            mMidColor,
            circleR - mProWidth - mSpace,
            mMidPro
        )
        drawCircle(
            canvas,
            sideLength,
            mHardBgColor,
            mHardColor,
            circleR - mProWidth * 2 - mSpace * 2,
            mHardPro
        )
    }

    private fun drawCircle(
        canvas: Canvas,
        sideLength: Float,
        bgColor: Int,
        proColor: Int,
        circleR: Float,
        proValue: Float
    ) {
        val cX = sideLength / 2F
        val cY = sideLength / 2F

        mPaint.color = bgColor
        canvas.drawCircle(cX, cY, circleR, mPaint)
        mPaint.color = proColor
        canvas.drawArc(
            cX - circleR, cY - circleR, cX + circleR, cY + circleR,
            -90F, proValue, false, mPaint
        )
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        invalidate()
    }

}