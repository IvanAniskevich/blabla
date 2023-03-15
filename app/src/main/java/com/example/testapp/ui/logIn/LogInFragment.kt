package com.example.testapp.ui.logIn

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testapp.R
import com.example.testapp.databinding.LogInFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment : Fragment() {

    private var _binding: LogInFragmentBinding? = null
    private val binding get() = _binding!!

    private val vieModel: LogInViewModel by viewModel<LogInViewModel>()

    var passwordVisibility = true
    private val key = "current user"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LogInFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logIn.setOnClickListener { logIn(binding.email.text.toString()) }
        binding.showHideIcon.setOnClickListener { showHide() }

        vieModel.isLogged.observe(this.viewLifecycleOwner, { value ->
            if (value == true) {
                navigate()
            } else {
                Toast.makeText(requireContext(), "wrong data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showHide() {
        if (passwordVisibility == true) {
            binding.password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.showHideIcon.setImageResource(R.drawable.ic_red_eye_24)
            passwordVisibility = false
        } else {
            binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.showHideIcon.setImageResource(R.drawable.ic_visibility_off_24)
            passwordVisibility = true
        }
        if (binding.password.text?.isEmpty() == false) {
            binding.password.setSelection(binding.password.text!!.length)
        }
    }

    private fun logIn(email: String) {
        vieModel.logIn(email)

    }

    private fun navigate() {
        val edit =  requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
        with(edit) {
            putString(key, binding.email.text.toString())
            apply()
        }

        findNavController().navigate(R.id.action_logInFragment_to_mainFragment)
    }
}