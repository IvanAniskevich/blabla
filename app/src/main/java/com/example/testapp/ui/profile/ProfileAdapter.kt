package com.example.testapp.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.ProfileRvItemBinding

class ProfileAdapter(private val onRvItemClick : OnRvItemClick) : RecyclerView.Adapter<ProfileAdapter.ProfileVieHolder>() {

    var itemList = emptyList<ProfileDataModel>()

    class ProfileVieHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ProfileRvItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileVieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_rv_item, parent, false)
        return ProfileVieHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileVieHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.setOnClickListener { onRvItemClick.onClick(item) }
        with(holder.binding) {
            imageView.setImageResource(item.icon)
            textView3.text = item.text
        }
        if (item.text2 !== null) {
            holder.binding.textView4.text = item.text2
        } else {
            holder.binding.textView4.visibility = View.GONE
        }

        if (item.ic2 !== null) {
            holder.binding.nextIcon.setImageResource(item.ic2)
        } else {
            holder.binding.nextIcon.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun setList(list: List<ProfileDataModel>) {
        itemList = list
        notifyDataSetChanged()
    }

    interface OnRvItemClick{
        fun onClick(item: ProfileDataModel)
    }


}