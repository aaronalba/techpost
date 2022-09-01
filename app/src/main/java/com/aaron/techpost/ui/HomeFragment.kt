package com.aaron.techpost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aaron.techpost.databinding.FragmentHomeBinding

/**
 * Shows a list of tech news to the user.
 */
class HomeFragment : Fragment() {

    /**
     * View binding property used to access the views. Valid only between
     * onCreateView() and onDestoryView()
     */
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}