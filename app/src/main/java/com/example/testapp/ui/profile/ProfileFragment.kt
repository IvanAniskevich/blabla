package com.example.testapp.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.databinding.ProfileFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(), ProfileAdapter.OnRvItemClick {
    private val key = "current user"

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!
    val viewModel: ProfileViewModel by viewModel<ProfileViewModel>()

    private val profileAdapter = ProfileAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAppbar()
        val email = sharedPref().getString(key, "blablabla")
        email.also { viewModel.getUser(it!!) }
        viewModel.currentUser.observe(this.viewLifecycleOwner, { user ->
            user.let { binding.name.text = user.firstName }
        })
        binding.image.setImageResource(R.mipmap.ic_launcher)
        binding.rv.adapter = profileAdapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listOfData.observe(this.viewLifecycleOwner, { list ->
            list.let { profileAdapter.setList(list) }
        })
        binding.button.setOnClickListener {
        }
    }

    private fun setAppbar() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.home -> TODO()
                else -> TODO()
            }
        }
    }

    override fun onClick(item: ProfileDataModel) {
        when (item.text) {
            "Lod out" -> logOut()
        }
    }

    private fun logOut() {
        val edit = sharedPref().edit()
        with(edit) {
            clear()
            apply()
        }
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val graph = navController.navInflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.signInFragment
        navController.graph = graph
    }

    private fun sharedPref() = requireActivity().getPreferences(Context.MODE_PRIVATE)
}
