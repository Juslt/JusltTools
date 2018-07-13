package com.juslt.juslttools.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.juslt.common.rv.UIEventInterface
import com.juslt.juslttools.R
import com.juslt.juslttools.adapter.CountryAdapter
import com.juslt.juslttools.model.Country
import com.juslt.juslttools.utils.HanziToPinyin
import com.juslt.juslttools.utils.TextPinyinUtil
import com.juslt.juslttools.view.LetterIndexView
import kotlinx.android.synthetic.main.activity_contract_side_bar.*
import java.util.*
import java.util.regex.Pattern
import kotlin.Comparator

class ContractSideBarActivity : AppCompatActivity() {
    val list = ArrayList<Country>()
    var adapter: CountryAdapter? = null
    lateinit var lineraLayoutManager: LinearLayoutManager
    var move = false
    var mIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract_side_bar)
        initData()

        adapter = CountryAdapter(UIEventInterface { event, index -> })
        lineraLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.layoutManager = lineraLayoutManager
        recycler_view.adapter = adapter
        adapter!!.update(list)
        letter_index_view.setUpdateListView(object : LetterIndexView.UpdateListView {
            override fun updateListView(currentChat: String) {
                if(currentChat.isNotEmpty()){
                    val positionForSection = adapter!!.getPositionForSection(currentChat[0])
                    moveToPosition(positionForSection)
                    tv_show_letter.visibility = View.VISIBLE
                    tv_show_letter.text = currentChat
                }else{
                    tv_show_letter.visibility = View.GONE
                }
            }
        })


        recycler_view.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //在这里进行第二次滚动（最后的距离）
                if (move) {
                    move = false
                    //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                    val n = mIndex - lineraLayoutManager.findFirstVisibleItemPosition()
                    if (0 <= n && n < recycler_view.childCount) {
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        val top = recycler_view.getChildAt(n).top
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        recycler_view.scrollBy(0, top)
                    }
                }
            }
        })
    }

    private fun initData() {
        val allCountryName = resources.getStringArray(R.array.arrContactName)

        allCountryName.forEach {
            val country = Country()
            country.name = it
            val pinyin = TextPinyinUtil.getInstance().getPinyin(it).toUpperCase()
            Log.e("===", pinyin)
            country.pinyin = pinyin

            val firstLetter = pinyin.substring(0, 1)
            val p = Pattern.compile("[A-Z]")
            val m = p.matcher(firstLetter)
            if (m.matches()) {
                country.firstLetter = firstLetter
            } else {
                country.firstLetter = "#"
            }
            list.add(country)
        }

        Collections.sort(list) { country1, country2 ->
            when {
                country1!!.firstLetter.contains("#") -> 1
                country2!!.firstLetter.contains("#") -> -1
                else -> country1.firstLetter.compareTo(country2.firstLetter)
            }
        }
    }

    fun moveToPosition(index: Int) {
        mIndex = index
        val firstItem = lineraLayoutManager.findFirstVisibleItemPosition()
        val lastItem = lineraLayoutManager.findLastVisibleItemPosition()
        if (index <= firstItem) {
            recycler_view.scrollToPosition(index)
        } else if (index <= lastItem) {
            //当要置顶的项已经在屏幕上显示时，计算它离屏幕原点的距离
            val top = recycler_view.getChildAt(index - firstItem).top
            recycler_view.scrollBy(0, top)
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recycler_view.scrollToPosition(index);
            //记录当前需要在RecyclerView滚动监听里面继续第二次滚动
            move = true;
        }
    }
}