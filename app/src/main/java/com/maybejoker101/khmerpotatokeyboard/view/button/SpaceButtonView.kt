package com.maybejoker101.khmerpotatokeyboard.view.button

import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.maybejoker101.khmerpotatokeyboard.data.ButtonData
import com.maybejoker101.khmerpotatokeyboard.util.Util

class SpaceButtonView(context: Context?, buttonData: ButtonData, rowHeight: Int) : KeyboardButton(context, buttonData) {

    var textColor: Int
    var textSize: Float

    var spaceText: TextView

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
        spaceText = TextView(context)

        //View Content

        spaceText.text = buttonData.middle

        //Color
        spaceText.setTextColor(textColor)
        //Size
        spaceText.textSize = textSize


        //layout params
        val layoutParams: LayoutParams = generateDefaultLayoutParams()

        layoutParams.bottomToBottom = id
        layoutParams.endToEnd = id
        layoutParams.startToStart = id
        layoutParams.topToTop = id

        spaceText.layoutParams = layoutParams

        addView(spaceText)

        //Background
        setBackgroundColor(backgroundColor)

    }

    class SpaceButtonTouchListener : OnLongClickListener, OnTouchListener {

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
            val view = v as SpaceButtonView
            when (event?.actionMasked) {

                MotionEvent.ACTION_DOWN -> {
                    view.changeBackground(true)
                    onDownOperation()
                }
                MotionEvent.ACTION_UP -> {
                    onUpOperation()
                    view.changeBackground(false)
                    reset()

                }
            }


            return false
        }

        private fun reset() {
            longPressed = false
        }


    }


}
