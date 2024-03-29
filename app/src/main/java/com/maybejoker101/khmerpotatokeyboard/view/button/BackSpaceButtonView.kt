package com.maybejoker101.khmerpotatokeyboard.view.button

import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.maybejoker101.khmerpotatokeyboard.data.ButtonData
import com.maybejoker101.khmerpotatokeyboard.util.Util

class BackSpaceButtonView(context: Context?, buttonData: ButtonData, rowHeight: Int) : KeyboardButton(context, buttonData) {

    var textColor: Int
    var textSize: Float

    var backSpaceContent: TextView

    init {

        //ID
        id = View.generateViewId()

        //layout Params
        val lp = LinearLayout.LayoutParams(0, Util.getPixelFromDp(rowHeight, context))
        lp.weight = buttonData.weight.toFloat()
        layoutParams = lp


        //Color
        textColor = Color.parseColor(viewConfig!!.buttonMiddleTextColor)
        //text Size
        textSize = viewConfig.buttonMainFontSize.toFloat()

        //View
        backSpaceContent = TextView(context)

        //View Content

        backSpaceContent.text = buttonData.middle

        //Color
        backSpaceContent.setTextColor(textColor)
        //Size
        backSpaceContent.textSize = textSize


        //layout params
        val layoutParams: LayoutParams = generateDefaultLayoutParams()

        layoutParams.bottomToBottom = id
        layoutParams.endToEnd = id
        layoutParams.startToStart = id
        layoutParams.topToTop = id

        backSpaceContent.layoutParams = layoutParams

        addView(backSpaceContent)

        //Background
        setBackgroundColor(backgroundColor)
    }

    class BackSpaceTouchListener : OnLongClickListener, OnTouchListener {

        var longPressed = false

        var onDownOperation: () -> Unit
        var onUpOperation: () -> Unit
        var onLongPressOperation: () -> Unit

        constructor(downOp: () -> Unit, upOp: () -> Unit, longOp: () -> Unit) {
            onDownOperation = downOp
            onUpOperation = upOp
            onLongPressOperation = longOp
        }

        override fun onLongClick(v: View?): Boolean {
            longPressed = true
            onLongPressOperation()
            return true
        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            val view = v as BackSpaceButtonView
            when (event?.actionMasked) {

                MotionEvent.ACTION_DOWN -> {
                    view.changeBackground(true)
                    onDownOperation()
                }
                MotionEvent.ACTION_UP -> {
                    onUpOperation()
                    view.changeBackground(false)


                }
            }


            return false
        }

        private fun reset() {
            longPressed = false
        }


    }
}