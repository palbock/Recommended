package com.example.recommended

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CategoryAdapter (private var activity: Activity, private var items: ArrayList<Categories>) :  BaseAdapter(){
    private class ViewHolder(row: View?) {
        var lblName: TextView? = null
        var imgCategory: ImageView? = null

        init {
            this.lblName = row?.findViewById<TextView>(R.id.lbl_name)
            this.imgCategory = row?.findViewById<ImageView>(R.id.img_category)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.category_list, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var company = items[position]
        viewHolder.lblName?.text = company.category_name

        viewHolder.imgCategory?.setImageResource(company.category_photo!!)

        return view as View
    }
    override fun getItem(i: Int): Categories {
        return items[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    override fun getCount(): Int {
        return items.size
    }
}