package com.example.testapp.ui.page1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.databinding.Page1FragmentBinding
import com.example.testapp.ui.page1.adapters.CategoriesAdapter
import com.example.testapp.ui.page1.adapters.FlashSaleAdapter
import com.example.testapp.ui.page1.adapters.LatestAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class Page1Fragment : Fragment(), FlashSaleAdapter.OnitemClick {

    private var _binding: Page1FragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: Page1ViewModel by viewModel<Page1ViewModel>()
    private val latestAdapter = LatestAdapter()
    private val flashSaleAdapter = FlashSaleAdapter(this)
    private val categoriesAdapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Page1FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarImg.setImageResource(R.mipmap.ic_launcher)

        binding.recyclerView.adapter = categoriesAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.listOfCategories.observe(this.viewLifecycleOwner, { list ->
            list.let { categoriesAdapter.setList(list) }
        })

        binding.recyclerView2.adapter = latestAdapter
        binding.recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.listOfLatestModel.observe(this.viewLifecycleOwner, { list ->
            list.let { latestAdapter.setList(list) }
        })

        binding.recyclerView3.adapter = flashSaleAdapter
        binding.recyclerView3.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.listOfFlashSaleModel.observe(this.viewLifecycleOwner, { list ->
            list.let { flashSaleAdapter.setList(list) }
        })
    }

    override fun onItemClick() {
        findNavController().navigate(R.id.action_page1Fragment_to_detailFragment)
    }

}