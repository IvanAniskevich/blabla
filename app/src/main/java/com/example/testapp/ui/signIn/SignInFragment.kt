package com.example.testapp.ui.signIn

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testapp.R
import com.example.testapp.databinding.SignInFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private var _binding: SignInFragmentBinding? = null
    private val binding get() = _binding!!

    private val vieModel: SigtInViewModel by viewModel<SigtInViewModel>()

    private val key = "current user"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignInFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = sharedPref().getString(key, "blablabla")
        email.also { binding.textView2.text = it }
        binding.logIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_logInFragment)
        }
        binding.signIn.setOnClickListener { reg() }
        vieModel.isAdded.observe(this.viewLifecycleOwner, { value ->
            if (value == true) {
                navigateToMain()
            } else {
                Toast.makeText(requireContext(), "account already exist", Toast.LENGTH_SHORT).show()
            }
        })
        binding.googleText.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
        }
        binding.appleText.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
        }
    }

    private fun reg() {
        val emailPattern = vieModel.emailPattern
        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val email = binding.email.text.toString()
        if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(requireContext(), "some field is empty", Toast.LENGTH_SHORT).show()
        } else {
            if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                vieModel.addUser(firstName, lastName, email)
            }
        }
    }

    private fun navigateToMain() {
        val edit = sharedPref().edit()
        with(edit) {
            putString(key, binding.email.text.toString())
            apply()
        }
        findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
    }

    private fun sharedPref() = requireActivity().getPreferences(Context.MODE_PRIVATE)
}