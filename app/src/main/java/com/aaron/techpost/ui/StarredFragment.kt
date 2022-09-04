package com.aaron.techpost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aaron.techpost.databinding.FragmentStarredBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class StarredFragment : Fragment() {

    /**
     * View binding property to access the views. Valid only between
     * onCreateView() and onDestroyView().
     */
    private var _binding: FragmentStarredBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStarredBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Not yet implemented")
            .setMessage("Sorry! This feature is not yet implemented.")
            .setPositiveButton("Ok") {_,_->}
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}