package com.example.readorwatch

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.readorwatch.databinding.ActivityDetailsBinding
import com.example.readorwatch.viewmodel.DetailsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var bind: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        bind = ActivityDetailsBinding.inflate(layoutInflater)
        val view: View = bind.root
        setContentView(view)

        bind.watchButton.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("movieTitle", detailsViewModel.movieDetails.value?.title)
            startActivity(intent)
        }

        val movieId = bundle?.getInt("movieId")
        detailsViewModel.getMovieDetails(movieId!!)

        detailsViewModel.movieDetails.observe(this) {
            if (detailsViewModel.movieDetails.value != null) {
                val details = detailsViewModel.movieDetails.value!!

                Picasso.get().load(details.posterOriginalUrl).into(bind.detailsPhoto,
                    object: Callback{
                        override fun onSuccess(){
                        }

                        override fun onError(e: Exception) {
                            detailsViewModel.setApiError(e.message!!)
                        }
                    })

                bind.detailsTitle.text = details.title
                bind.detailsDescriptionText.text = details.overview
                bind.detailsYearText.text = details.releaseDate.substringBefore("-", "N/A")
                bind.detailsPGText.text = details.voteAverage.toString()
                bind.detailsSeasonsText.text = getString(R.string.movie)
            }
        }

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}