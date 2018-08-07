package com.juslt.juslttools.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.juslt.juslttools.R
import com.juslt.juslttools.utils.SensitiveWord

class WordFilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_filter)

        val startNumer = System.currentTimeMillis()
        val sw = SensitiveWord("CensorWords.txt")
        sw.InitializationWork(this)
        //System.out.println("敏感词的数量：" + arrayList.size());
        var str = ("太多的伤yuming感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20哀2015/4/16 20152015/4/16乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "关, 人, 流, 电, 发, 情, 太, 限, 法轮功, 个人, 经, 色, 许, 公, 动, 地, 方, 基, 在, 上, 红, 强, 自杀指南, 制, 卡, 三级片, 一, 夜, 多, 手机, 于, 自，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。")
        println("被检测字符串长度:" + str.length)
        str = sw.filterInfo(str)
        val endNumber = System.currentTimeMillis()
        //System.out.println("语句中包含敏感词的个数为：" + sensitiveWordSet.size() + "。包含：" + sensitiveWordSet);
        //System.out.println("语句中包含敏感词的个数为：" + sensitiveWordList.size() + "。包含：" + sensitiveWordList);
        println("总共耗时:" + (endNumber - startNumer) + "ms")
        println("替换后的字符串为:\n" + str)

    }
}
