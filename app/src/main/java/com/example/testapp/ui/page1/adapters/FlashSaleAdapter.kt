package com.example.testapp.ui.page1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testapp.R
import com.example.testapp.data.api.FlashSaleModel
import com.example.testapp.databinding.Page1SaleItemBinding

class FlashSaleAdapter(private val onItemClick : OnitemClick) : RecyclerView.Adapter<FlashSaleAdapter.FlashSaleViewHolder>() {

    private var listFlashSale = emptyList<FlashSaleModel>()

    class FlashSaleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = Page1SaleItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.page1_sale_item, parent, false)
        return FlashSaleViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlashSaleViewHolder, position: Int) {
        val item: FlashSaleModel = listFlashSale[position]
        holder.binding.saleImg.setOnClickListener { onItemClick.onItemClick() }
        with(holder.binding) {
            saleImg.load(item.image_url)
            saleCategory.text = item.category
            saleName.text = item.name
            salePrice.text = item.price.toString()
            saleDiscount.text = item.discount.toString()
            saleIconLike.setOnClickListener { }
            saleIconAdd.setOnClickListener { }
        }
    }

    override fun getItemCount(): Int {
        return listFlashSale.size
    }

    fun setList(list: List<FlashSaleModel>) {
        listFlashSale = list
        notifyDataSetChanged()
    }

    interface OnitemClick{
        fun onItemClick()
    }

}