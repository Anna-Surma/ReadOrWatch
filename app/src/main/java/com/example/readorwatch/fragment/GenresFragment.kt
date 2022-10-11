package com.example.readorwatch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.readorwatch.adapter.GenresListAdapter
import com.example.readorwatch.databinding.FragmentGenresBinding
import com.example.readorwatch.viewmodel.HomeViewModel

class GenresFragment : Fragment() {

    private lateinit var bind: FragmentGenresBinding
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentGenresBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.genresList.observe(viewLifecycleOwner) {
            if (it != null) {
                bind.genresRecycler?.adapter = GenresListAdapter(viewModel.genresList.value!!, viewModel, activity)
            }
        }

        viewModel.fetchGenres()

        bind.genresRecycler?.apply {
            if (viewModel.genresList.value != null) {
                adapter = GenresListAdapter(viewModel.genresList.value!!, viewModel, activity)
            }
        }
    }

}