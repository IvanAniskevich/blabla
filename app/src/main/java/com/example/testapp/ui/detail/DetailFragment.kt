package com.example.testapp.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.testapp.data.api.DetailItemModel
import com.example.testapp.databinding.DetailFragmentBinding
import com.example.testapp.ui.detail.adapters.DetailColorAdapter
import com.example.testapp.ui.detail.adapters.DetailImageAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(), DetailImageAdapter.OnImageClick, DetailImageAdapter.GetPxFromDp,
    DetailColorAdapter.OnColorClick {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageAdapter = DetailImageAdapter(this, this)
        binding.rvImageSelection.adapter = imageAdapter
        binding.rvImageSelection.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val colorAdapter = DetailColorAdapter(this)
        binding.colorRv.adapter = colorAdapter
        binding.colorRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.detailItem.observe(this.viewLifecycleOwner, { item ->
            item.let {
                imageAdapter.setList(item.image_urls)
                colorAdapter.setList(item.colors)
                bind(item)
            }
        })
        viewModel.selectedImage.observe(this.viewLifecycleOwner, { img ->
            img.let { imageAdapter.setSelectedImage(img) }
        })
        viewModel.quantityCounter.observe(this.viewLifecycleOwner, { value ->
            value.let { binding.quantity.text = value.toString() }
        })
        viewModel.finalCost.observe(this.viewLifecycleOwner, { value ->
            value.let { binding.finalCost.text = value.toString() }
        })
        viewModel.selectedColor.observe(this.viewLifecycleOwner, { color ->
            color.let { colorAdapter.setSelectedColor(color) }
        })

    }

    private fun bind(item: DetailItemModel) {
        val imageUrls: List<String> = item.image_urls
        with(binding) {
            name.text = item.name
            description.text = item.description
            price.text = "$ " + item.price.toString()
            rating.text = item.rating.toString()
            numberOfReviews.text = "(${item.number_of_reviews})"
            mainImage.load(imageUrls.first())
            finalCost.text = item.price.toString()
            minusButton.setOnClickListener { viewModel.minus() }
            plusButton.setOnClickListener { viewModel.plus() }
        }
    }

    override fun onImageClick(imgUrl: String) {
        viewModel.selectImage(imgUrl)
        binding.mainImage.load(imgUrl)
    }

    private fun pxFromDp(context: Context, dp: Float): Int {
        val px = dp * context.resources.displayMetrics.density
        return px.toInt()
    }

    override fun getPx(dp: Float): Int = pxFromDp(requireContext(), dp)
    override fun onColorClick(color: String) {
        viewModel.setColor(color)
    }
}