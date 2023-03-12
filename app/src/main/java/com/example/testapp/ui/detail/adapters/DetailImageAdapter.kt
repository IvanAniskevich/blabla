package com.example.testapp.ui.detail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testapp.R
import com.example.testapp.databinding.DetailImageItemBinding

class DetailImageAdapter(
    private val onImageClick: OnImageClick,
    private val getPxFromDp: GetPxFromDp,
) :
    RecyclerView.Adapter<DetailImageAdapter.DetailImageViewHolder>() {

    private var listOfImageUrl = emptyList<String>()

    private var selectedImage = listOfImageUrl.firstOrNull()

    private val unselectedWidth = getPxFromDp.getPx(dp = 80f)
    private val unselectedHeight = getPxFromDp.getPx(dp = 50f)
    private val selectedWidth = getPxFromDp.getPx(dp = 100f)
    private val selectedHeight = getPxFromDp.getPx(dp = 70f)

    class DetailImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DetailImageItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_image_item, parent, false)
        return DetailImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailImageViewHolder, position: Int) {
        val item = listOfImageUrl[position]
        if (item == selectedImage){
            with(holder.binding.imageView){
                layoutParams.width = selectedWidth
                layoutParams.height = selectedHeight
                alpha = 1f
            }
        }else{
            with(holder.binding.imageView){
                layoutParams.width = unselectedWidth
                layoutParams.height = unselectedHeight
                alpha = 0.5f
            }
        }
        holder.binding.imageView.load(item)
        holder.itemView.setOnClickListener { onImageClick.onImageClick(item) }

    }
    
    override fun getItemCount(): Int = listOfImageUrl.size

    fun setList(list: List<String>) {
        listOfImageUrl = list
        selectedImage = list.first()
        notifyDataSetChanged()
    }

    fun setSelectedImage(img: String) {
        selectedImage = img
        notifyDataSetChanged()
    }

    interface OnImageClick {
        fun onImageClick(imgUrl: String)
    }

    interface GetPxFromDp{
        fun getPx(dp: Float): Int
    }

}