package com.example.adajaapps.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.adajaapps.BR
import java.lang.reflect.TypeVariable

abstract class BaseAdapter<T>(@field:LayoutRes private var  layoutId: Int) :
    RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>(), BindableAdapter<List<T>> {

    private var listData : List<T>? = null

    init {
        this.setHasStableIds(true)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)

        fun onBind(variable: Int?, data: T) : ViewDataBinding? {
            return variable?.takeIf { data != null }?.let {
                binding?.apply {
                    setVariable(it, data)
                    executePendingBindings()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listData?.let {
            val data = it[position]
            val binding = holder.onBind(BR.model, data)
            onBind(binding, data)
            holder.itemView.setOnClickListener { onClick(binding, data) }
        }
    }

    abstract fun onBind(binding: ViewDataBinding?, data: T)

    abstract fun onClick(binding: ViewDataBinding?, data: T)

    override fun getItemCount(): Int {
        return listData?.size ?: 0
    }

    override fun setData(data: List<T>?) {
        listData = data
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}

interface BindableAdapter<T> {
    fun setData(data: T?)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> recyclerViewData(recyclerView: RecyclerView, data: T?) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}