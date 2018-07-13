package com.juslt.juslttools.adapter

import android.view.ViewGroup
import com.juslt.common.rv.BaseRvFooterAdapter
import com.juslt.common.rv.BaseViewHolder
import com.juslt.common.rv.UIEventInterface
import com.juslt.juslttools.holder.CountryHolder
import com.juslt.juslttools.model.Country
import java.util.ArrayList

/**
 * Created by juslt on 2018/6/27.
 */
class CountryAdapter(uiEventInterface: UIEventInterface,loadMore: LoadMore ?=null):BaseRvFooterAdapter(uiEventInterface,loadMore){
    override fun update(obj: Any?, hasMore: Boolean) {
        dataList.clear()
        dataList.addAll(obj as Collection<*>)
        notifyByFooter(hasMore)
    }

    override fun onCreateViewHolderAfterFooter(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return CountryHolder.create(parent!!,this)
    }
    //获得该分组第一项的position
     fun getPositionForSection(sectionForPosition: Char): Int {
        dataList.forEachIndexed { index, country ->
            country as Country
            if (country.firstLetter[0] == sectionForPosition) {
                return index
            }
        }
        return -1
    }
    //获得当前position是属于哪个分组
     fun getSectionForPosition(position: Int): Char {
        return (dataList[position] as Country).firstLetter[0]
    }
}