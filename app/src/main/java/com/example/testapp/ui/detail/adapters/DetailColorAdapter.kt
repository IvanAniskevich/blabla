package com.example.testapp.ui.detail.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.DetailColorItemBinding

class DetailColorAdapter(
    private val onColorClick: OnColorClick
) : RecyclerView.Adapter<DetailColorAdapter.DetailColorViewHolder>() {

    private var listOfColor = emptyList<String>()

    private var selectedColor = listOfColor.firstOrNull()

    class DetailColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DetailColorItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailColorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_color_item, parent, false)
        return DetailColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailColorViewHolder, position: Int) {
        val item = listOfColor[position]

        if (item == selectedColor) {
            val border = GradientDrawable()
            border.setStroke(3, Color.BLACK)
            border.setCornerRadius(30f)
            border.setColor(Color.parseColor(item))
            holder.binding.color.background = border
        } else {
            val border = GradientDrawable()
            border.setCornerRadius(30f)
            border.setColor(Color.parseColor(item))
            holder.binding.color.background = border
        }

        holder.itemView.setOnClickListener { onColorClick.onColorClick(item) }
    }

    override fun getItemCount(): Int = listOfColor.size

    interface OnColorClick {
        fun onColorClick(color: String)
    }

    fun setList(list: List<String>) {
        listOfColor = list
        selectedColor = list.first()
        notifyDataSetChanged()
    }

    fun setSelectedColor(color: String) {
        selectedColor = color
        notifyDataSetChanged()
    }
}