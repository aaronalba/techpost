package com.aaron.techpost.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaron.techpost.data.domain.Article
import com.aaron.techpost.databinding.FragmentHomeBinding
import com.aaron.techpost.ui.adapter.ArticleAdapter
import com.aaron.techpost.ui.adapter.ArticleListener
import com.aaron.techpost.ui.viewmodel.HomeViewModel
import com.aaron.techpost.ui.viewmodel.MainViewModel
import com.aaron.techpost.util.formatDate
import java.util.*

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
     * RecyclerView Adapter for converting a list of Articles into cards.
     */
    private var articleAdapter: ArticleAdapter? = null

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

        // initialize recycler view adapter
        articleAdapter = ArticleAdapter(ArticleListener { })

        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.articles.observe(viewLifecycleOwner) { articles ->
            // update the adapter's article list when new data arrives
            articles?.let {
                Log.d(TAG, "received ${it.size} articles")
                articleAdapter?.articles = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}