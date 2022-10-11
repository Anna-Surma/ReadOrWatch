package com.example.readorwatch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.readorwatch.adapter.MovieListAdapter
import com.example.readorwatch.databinding.FragmentHomeBinding
import com.example.readorwatch.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            if (homeViewModel.popularMoviesList.value != null) {
                adapter = MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
            }
        }

        homeViewModel.popularMoviesList.observe(viewLifecycleOwner) {
            bind.recyclerView.adapter =
                MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
        }

        bind.filterFirst.setOnClickListener{
            homeViewModel.fetchPopular()
        }

        bind.filterSecond.setOnClickListener{
            homeViewModel.fetchGenreMembers(16)
        }

        bind.filterThird.setOnClickListener{
            homeViewModel.fetchGenreMembers(27)
        }

        bind.filterFourth.setOnClickListener{
            homeViewModel.fetchGenreMembers(18)
        }

    }
}