package com.juslt.juslttools.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.juslt.common.utils.SizeUtil

/**
 * Created by juslt on 2018/6/27.
 */
class LetterIndexView @JvmOverloads constructor(context:Context,attributeSet: AttributeSet?=null,defAttr:Int =0):View(context,attributeSet,defAttr){
    var choosedPosition =-1    //当前手指滑动到的位置
    val paint:Paint by lazy { Paint() }    //画文字的画笔
    private val letters = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#")    //右边的所有文字
    private  var updateListView:UpdateListView ?=null
    init {
        paint.isAntiAlias =true
        paint.textSize=SizeUtil.dip2px(context,15f).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val perTextHeight = height/letters.size
        letters.forEachIndexed { index, s ->
            if(index == choosedPosition){
                paint.color = Color.RED
            }else{
                paint.color = Color.BLACK
            }
        canvas!!.drawText(letters[index],(width-paint.measureText(letters[index]))/2,
                ((index+1)*perTextHeight).toFloat(),paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val perTextHeight = height / letters.size
        val y = event!!.y
        val currentPosition =(y/perTextHeight).toInt()
        //排除下标越界异常
        if(currentPosition>letters.size-1||currentPosition<0){
            return true
        }
        val letter = letters[currentPosition]
        when(event.action){
            MotionEvent.ACTION_UP->{
                setBackgroundColor(Color.TRANSPARENT)
                updateListView!!.updateListView("")
            }
            else->{
                setBackgroundColor(Color.parseColor("#cccccc"))
                if (currentPosition>-1&&currentPosition<letters.size){
                    choosedPosition =currentPosition
                    updateListView!!.updateListView(letters[choosedPosition])
                }
            }
        }
        invalidate()
        return true
    }

    fun setUpdateListView(updateListView:UpdateListView){
        this.updateListView = updateListView
    }
    interface UpdateListView{
        fun updateListView(currentChat:String)

    }
}