package com.aaron.techpost.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaron.techpost.data.domain.Article
import com.aaron.techpost.databinding.ArticleItemBinding

/**
 * The adapter class of the [RecyclerView] in [HomeFragment].
 */
class ArticleAdapter(
    private val clickListener: ArticleListener
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    /**
     * The list of articles that this adapter will show.
     */
    var articles: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * The ViewHolder class for each [Article] item to be shown.
     */
    class ArticleViewHolder(
        private var binding: ArticleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds this view holder to the given article item.
         */
        fun bind(clickListener: ArticleListener, article: Article) {
            binding.clickListener = clickListener
            binding.article = article
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(
            ArticleItemBinding.inflate(layoutInflater, parent, false),
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = articles[position]
        holder.bind(clickListener, current)
    }

    override fun getItemCount(): Int = articles.size
}

/**
 * Class that contains a function that is executed when an [Article] item is pressed in the list.
 */
class ArticleListener(private val clickListener: (Article) -> Unit) {
    fun onClick(article: Article) = clickListener(article)
}