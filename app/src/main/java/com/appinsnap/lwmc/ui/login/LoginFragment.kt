package com.appinsnap.lwmc.ui.login

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.appinsnap.lwmc.R
import com.appinsnap.lwmc.databinding.FragmentLoginBinding
import com.appinsnap.lwmc.dataclasses.model.request.LoginDataModel
import com.appinsnap.lwmc.dataclasses.observerhandler.EventObserver
import com.appinsnap.lwmc.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
        observerListener()
    }

    private fun observerListener() {
        viewModel.error.observe(viewLifecycleOwner, EventObserver {

        })
        viewModel.loginResponse.observe(viewLifecycleOwner, EventObserver {
            val bundle = Bundle()
            bundle.putSerializable("response", it)
         //   navigateFragment(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })
    }

    private fun clickListener() {
        binding.loginBtn.setOnClickListener {
            requireContext().requestPermissions(
                listOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                ), object : PermissionListener {
                    override fun areAllPermissionsGranted() {
                        if (requireActivity().isNetworkAvailable()) {
                            val loginDataModel = LoginDataModel(binding.userNameET.text.toString(),
                                binding.passwordET.text.toString())
                            viewModel.callLoginApi(loginDataModel)
                        } else {
                            Toast.makeText(context, "no internet", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun isAnyPermissionPermanentlyDenied() {
                        requireContext().showPermissionDeniedDialog(Constants.PHONE_PERMISSION_MSG,false)
                    }
                })
        }
    }
}