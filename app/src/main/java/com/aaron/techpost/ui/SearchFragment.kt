package com.aaron.techpost.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aaron.techpost.databinding.FragmentSearchBinding
import com.aaron.techpost.ui.viewmodel.SearchViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SearchFragment : Fragment() {

    // the view model for this fragment
    private val viewModel: SearchViewModel by viewModels()

    /**
     * View binding property to access the views. Valid only between
     * onCreateView() and onDestroyView().
     */
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hasResult.observe(viewLifecycleOwner) {
            it?.let { hasResult ->
                // hide the no result message if there is a received search result
                makeNoResultVisible(!hasResult)

                // show the recyclerview that will display the search result
                makeRecyclerViewVisible(hasResult)
            }
        }

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

    /**
     * Toggles the visibility of the no result message.
     */
    private fun makeNoResultVisible(b: Boolean) {
        val visible = if (b) {
            View.VISIBLE
        } else {
            View.GONE
        }
        binding.noResultImage.visibility = visible
        binding.noResultHeader.visibility = visible
        binding.noResultSubheader.visibility = visible
    }

    /**
     * Toggles the visibility of the list of results.
     */
    private fun makeRecyclerViewVisible(b: Boolean) {
        val visible = if (b) {
            View.VISIBLE
        } else {
            View.GONE
        }
        binding.searchRecyclerView.visibility = visible
    }
}