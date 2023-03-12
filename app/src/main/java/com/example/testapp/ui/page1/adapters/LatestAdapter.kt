package com.example.testapp.ui.page1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testapp.R
import com.example.testapp.data.api.LatestModel
import com.example.testapp.databinding.Page1LatestItemBinding

class LatestAdapter : RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    var listLatest = emptyList<LatestModel>()

    class LatestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = Page1LatestItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.page1_latest_item, parent, false)
        return LatestViewHolder(view)
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val item: LatestModel = listLatest[position]
        with(holder.binding) {
            latestImg.load(item.image_url)
            latestCategory.text = item.category
            latestName.text = item.name
            latestPrice.text = item.price.toString()
        }
    }

    override fun getItemCount(): Int {
        return listLatest.size
    }

    fun setList(list: List<LatestModel>) {
        listLatest = list
        notifyDataSetChanged()
    }

}