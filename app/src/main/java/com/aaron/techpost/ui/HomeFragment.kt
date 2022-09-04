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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaron.techpost.BaseApplication
import com.aaron.techpost.R
import com.aaron.techpost.data.DataStatus
import com.aaron.techpost.data.domain.Article
import com.aaron.techpost.databinding.FragmentHomeBinding
import com.aaron.techpost.ui.adapter.ArticleAdapter
import com.aaron.techpost.ui.adapter.ArticleListener
import com.aaron.techpost.ui.viewmodel.HomeViewModel
import com.aaron.techpost.ui.viewmodel.MainViewModel
import com.aaron.techpost.ui.viewmodel.MainViewModelFactory
import com.aaron.techpost.util.formatDate
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "HomeFragment"

/**
 * Shows a list of tech news to the user.
 */
class HomeFragment : Fragment() {

    // the view model that is shared between fragments
    private val sharedViewModel: MainViewModel by activityViewModels() {
        MainViewModelFactory(
            (activity?.application as BaseApplication).database.articleDao
        )
    }

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

        /**
         * Observer that waits for the list of articles from the data layer of the app.
         */
        lifecycleScope.launch {
            sharedViewModel.articles.collect {
                sharedViewModel.updateDatabaseStatus(DataStatus.DONE)   // update database status
                articleAdapter?.articles = it   // submit received articles to the adapter
            }
        }

        /**
         * Notify the user that updated data from the network was received.
         */
        notifyForNetworkDataRefresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Displays a snackbar notification based on the result of the network request.
     */
    private fun notifyForNetworkDataRefresh() {
        sharedViewModel.networkStatus.observe(viewLifecycleOwner) { status ->
            status?.let {
                when(it) {
                    DataStatus.DONE ->
                        showSnackBar(getString(R.string.articles_loaded_from_the_network))
                    DataStatus.ERROR ->
                        showSnackBar(
                            getString(R.string.error_loading_articles_from_the_network),
                            Snackbar.LENGTH_LONG
                        )
                    DataStatus.LOADING -> {}
                }
            }
        }
    }

    /**
     * Shows a message to the user. Should only be called when the binding property is in valid state.
     */
    private fun showSnackBar(message: String, length: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(binding.root, message, length).show()
    }
}