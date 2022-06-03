package com.mostpopular.nytimes.ui.main


import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mostpopular.nytimes.R
import com.mostpopular.nytimes.databinding.FragmentArticleListBinding
import com.mostpopular.nytimes.model.Article
import com.mostpopular.nytimes.ui.main.adapter.ArticleListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleListFragment : Fragment() {
    private lateinit var binding: FragmentArticleListBinding
    lateinit var articleListAdapter: ArticleListAdapter
    private val articleListViewModel: ArticleListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.articleViewModel = articleListViewModel
        configureRecyclerView(binding.rvArticleList)
        articleListViewModel.networkErrorState.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.rvArticleList.visibility = View.GONE
                    binding.llNetworkError.visibility = View.VISIBLE
                } else {
                    binding.rvArticleList.visibility = View.VISIBLE
                    binding.llNetworkError.visibility = View.GONE
                }
            }
        }
        articleListViewModel.articleList().observe(viewLifecycleOwner) {
            articleListAdapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.options_menu, menu)
        val searchView = SearchView(requireContext())
        searchView.isIconified = false
        menu.findItem(R.id.search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                var searchText = newText
                searchText = "%$searchText%"
                articleListViewModel.articleList(searchText)
                    .observe(viewLifecycleOwner) {
                        it?.let { articleListAdapter.submitList(it) }
                    }
                return false
            }
        })
    }

    private fun configureRecyclerView(recyclerView: RecyclerView) {
        articleListAdapter = ArticleListAdapter(object : ArticleListAdapter.OnClickListener {
            override fun onClick(article: Article) {
                findNavController().navigate(
                    ArticleListFragmentDirections.actionArticleListFragmentToDetailFragment(article)
                )
            }
        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articleListAdapter
        }
    }

}