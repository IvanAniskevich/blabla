package com.example.testapp.ui.page1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.api.CategoriesModel
import com.example.testapp.data.api.LatestModel
import com.example.testapp.databinding.Page1CategoriesItemBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var categoriesList = emptyList<CategoriesModel>()

    class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = Page1CategoriesItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.page1_categories_item, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = categoriesList[position]
        with(holder.binding) {
            imageView10.setImageResource(R.drawable.ic_baseline_done_24)
            textView21.text = item.title
        }

    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun setList(list: List<CategoriesModel>) {
        categoriesList = list
        notifyDataSetChanged()
    }
}