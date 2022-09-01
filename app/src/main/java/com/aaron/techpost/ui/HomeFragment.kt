package com.aaron.techpost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.aaron.techpost.databinding.FragmentHomeBinding
import com.aaron.techpost.ui.viewmodel.TechpostViewModel
import com.aaron.techpost.util.formatDate
import java.text.SimpleDateFormat
import java.util.*

/**
 * Shows a list of tech news to the user.
 */
class HomeFragment : Fragment() {

    // the view model that is shared between fragments
    private val viewModel: TechpostViewModel by activityViewModels()

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
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // show the current date
        viewModel.date.observe(viewLifecycleOwner) { date ->
            date?.let {
                binding.date.text = formatDate("EEEE, dd MMM", it)
            }
        }

        // TODO: implement the recycler view adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}