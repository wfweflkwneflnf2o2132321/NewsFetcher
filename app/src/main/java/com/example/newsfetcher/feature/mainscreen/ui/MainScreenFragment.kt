package com.example.newsfetcher.feature.mainscreen.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainScreenFragment: Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val ivSearch: ImageView by lazy { requireActivity().findViewById(R.id.ivSearch) }
    private val tvTitle: TextView by lazy { requireActivity().findViewById(R.id.tvTitle) }
    private val etSearch: EditText by lazy { requireActivity().findViewById(R.id.etSearch) }


    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter{ index ->
            viewModel.processUIEvent(UiEvent.OnArticleClicked(index))
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner,::render)
        recyclerView.adapter = adapter


        ivSearch.setOnClickListener{
            viewModel.processUIEvent(UiEvent.OnSearchButtonClicked)
        }

        etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.processUIEvent(UiEvent.OnSearchEdit(text.toString()))
            }

        })

    }



    private fun render(viewState: ViewState){
        tvTitle.isVisible = !viewState.isSearchEnable
        etSearch.isVisible = viewState.isSearchEnable
        adapter.setData(viewState.articlesShown)

    }
}