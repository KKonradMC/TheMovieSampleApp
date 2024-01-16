package com.kkonradsoftware.feature.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import com.kkonradsoftware.feature.presentation.BR
import com.kkonradsoftware.feature.presentation.databinding.FragmentNewMovieListBinding
import com.kkonradsoftware.feature.presentation.details.NewMovieDetailsFragment
import com.kkonradsoftware.feature.presentation.details.model.NewMovieDetailsModel
import com.kkonradsoftware.feature.presentation.list.adapter.NewMovieListAdapter
import com.kkonradsoftware.feature.presentation.list.adapter.SearchAdapter
import com.kkonradsoftware.feature.presentation.list.model.MovieListItemData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NewMovieListFragment : Fragment() {

    @Inject
    lateinit var adapter: NewMovieListAdapter

    @Inject
    lateinit var searchAdapter: SearchAdapter
    private val viewModel by viewModels<NewMovieListViewModel>()
    private lateinit var binding: FragmentNewMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNewMovieListBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@NewMovieListFragment
            setVariable(BR.vm, viewModel)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            movies.adapter = adapter
            searchView.setAdapter(searchAdapter)
        }
        viewModel.run {
            data.observe(viewLifecycleOwner, ::submitAdapterList)
            searchAdapterData.observe(viewLifecycleOwner, ::setSearchAdapterData)
            openDetailsSingleEvent.observe(viewLifecycleOwner, ::showDetails)
            refresh()
        }
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)

    }

    private fun submitAdapterList(pagingData: PagingData<MovieListItemData>) {
        adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
    }

    private fun setSearchAdapterData(searchItems: List<String>?) {
        searchAdapter.clear()
        searchItems?.let(searchAdapter::addAll)
    }

    private fun showDetails(data: NewMovieDetailsModel?) {
        data ?: return
        val ft = parentFragmentManager.beginTransaction()
        ft.replace(
            com.google.android.material.R.id.container,
            NewMovieDetailsFragment.newInstance(data = data),
            NewMovieDetailsFragment::class.java.simpleName,
        ).addToBackStack(NewMovieDetailsFragment::class.java.simpleName).commit()
    }

}

