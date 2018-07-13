package com.juslt.juslttools.holder

import android.view.View
import android.view.ViewGroup
import com.juslt.common.rv.BaseRvFooterAdapter
import com.juslt.common.rv.BaseViewHolder
import com.juslt.juslttools.R
import com.juslt.juslttools.adapter.CountryAdapter
import com.juslt.juslttools.model.Country
import kotlinx.android.synthetic.main.item_country.view.*
import java.util.ArrayList

/**
 * Created by juslt on 2018/6/27.
 */
class CountryHolder(view: View, adapter: BaseRvFooterAdapter) : BaseViewHolder(view, adapter) {
    companion object {
        var adapter:CountryAdapter ?=null
        fun create(parent: ViewGroup, adapter: BaseRvFooterAdapter): CountryHolder {
            this.adapter = adapter as CountryAdapter
            return CountryHolder(inflateItemView(R.layout.item_country, parent), adapter)
        }
    }

    override fun update(obj: Any?, position: Int) {
        val countryInfo = obj as Country
        itemView.tv_country_name.text = countryInfo.name
        val sectionForPosition = adapter!!.getSectionForPosition(position)
        val positionForSection = adapter!!.getPositionForSection(sectionForPosition)
        if(position==positionForSection){
            itemView.tv_show_letter.visibility = View.VISIBLE
            itemView.tv_show_letter.text = countryInfo.firstLetter
        }else{
            itemView.tv_show_letter.visibility = View.GONE
        }
    }

    override fun reset() {
    }

}