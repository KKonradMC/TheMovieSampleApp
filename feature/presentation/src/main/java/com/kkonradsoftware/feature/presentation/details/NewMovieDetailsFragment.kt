package com.kkonradsoftware.feature.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kkonradsoftware.feature.presentation.BR
import com.kkonradsoftware.feature.presentation.databinding.FragmentNewMovieDetailsBinding
import com.kkonradsoftware.feature.presentation.details.model.NewMovieDetailsModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewMovieDetailsFragment : Fragment() {

    private val viewModel by viewModels<NewMovieDetailsViewModel>()
    private lateinit var binding: FragmentNewMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentNewMovieDetailsBinding.inflate(inflater, container, false).apply {
            binding = this
            setVariable(BR.vm, viewModel)
            lifecycleOwner = this@NewMovieDetailsFragment
        }.root
    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: NewMovieDetailsModel? = arguments?.getParcelable(DETAILS_KEY)
        data?.let(viewModel::setData)
    }

    companion object {
        fun newInstance(data: NewMovieDetailsModel) = NewMovieDetailsFragment().apply{
            arguments = Bundle().apply { putParcelable(DETAILS_KEY, data) }
        }
    }
}
private const val DETAILS_KEY = "details"