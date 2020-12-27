package com.example.adajaapps.ui.news

import android.content.Context
import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.adajaapps.R
import com.example.adajaapps.data.model.News
import com.example.adajaapps.databinding.ItemNewsBinding
import com.example.adajaapps.ui.base.BaseAdapter

class NewsAdapter(val context: Context) : BaseAdapter<News>(R.layout.item_news) {
    override fun onBind(binding: ViewDataBinding?, data: News) {
        val mBinding = binding as ItemNewsBinding
        Glide.with(context).load(data.thumbnail).into(mBinding.itemThumbnail)
    }

    override fun onClick(binding: ViewDataBinding?, data: News) {
        val intent = Intent(context, NewsActivity::class.java)
        intent.putExtra(NewsActivity.OPEN_NEWS,data)
        context.startActivity(intent)
    }
}