package com.aaron.techpost.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.aaron.techpost.databinding.FragmentHomeBinding
import com.aaron.techpost.ui.viewmodel.HomeViewModel
import com.aaron.techpost.ui.viewmodel.MainViewModel
import com.aaron.techpost.util.formatDate

private const val TAG = "HomeFragment"

/**
 * Shows a list of tech news to the user.
 */
class HomeFragment : Fragment() {

    // the view model that is shared between fragments
    private val sharedViewModel: MainViewModel by activityViewModels()

    // this fragment's own view model
    private val viewModel: HomeViewModel by viewModels()

    /**
     * View binding property used to access the views. Valid only between
     * onCreateView() and onDestroyView()
     */
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        // initialize the data binding
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.sharedViewModel = sharedViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.articles.observe(viewLifecycleOwner) {
            Log.d(TAG, "Articles received ${sharedViewModel.articles.value?.take(2)}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}