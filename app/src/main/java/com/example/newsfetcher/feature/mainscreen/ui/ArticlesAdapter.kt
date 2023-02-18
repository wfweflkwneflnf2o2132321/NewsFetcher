package com.example.newsfetcher.feature.mainscreen.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.NewsMoreFragment
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout


class ArticlesAdapter(private val onItemClicked: (ArticleModel) ->Unit,
                      private val onIconClicked: (Int) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTitle: TextView
        val tvDescription: TextView
        val tvData: TextView
        val iv: ImageView



        init {

            tvTitle = view.findViewById(R.id.tvTitle)
            tvDescription = view.findViewById(R.id.tvDescription)
            tvData = view.findViewById(R.id.tvData)
            iv = view.findViewById(R.id.iv)



        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.setOnClickListener{
            onItemClicked(articlesData[position])
        }
        viewHolder.iv.setOnClickListener {
            onIconClicked(position)
        }


        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.tvDescription.text = articlesData[position].description
        viewHolder.tvData.text = articlesData[position].publishedAt


        if (articlesData[position].mark) {
            Log.d("MYTAG", "mark = true")
            viewHolder.iv.setImageResource(R.drawable.ic_baseline_bookmark_24)
        } else {
            //Log.d("MYTAG", "mark = false")
            viewHolder.iv.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
        }



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = articlesData.size


    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()

    }

}
