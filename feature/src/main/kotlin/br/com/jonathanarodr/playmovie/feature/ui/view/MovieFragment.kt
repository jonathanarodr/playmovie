package br.com.jonathanarodr.playmovie.feature.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.jonathanarodr.playmovie.common.states.observeOnEmpty
import br.com.jonathanarodr.playmovie.common.states.observeOnError
import br.com.jonathanarodr.playmovie.common.states.observeOnLoading
import br.com.jonathanarodr.playmovie.common.states.observeOnSuccess
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.databinding.FragmentMovieBinding
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.ui.view.MovieAdapter.MovieOnClickHandler
import br.com.jonathanarodr.playmovie.feature.ui.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MovieFragment : Fragment(), MovieOnClickHandler, SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: MovieViewModel by viewModel()
    private val args: MovieFragmentArgs by navArgs()
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservables()
        onRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onMovieClickListener(movie: Movie) {
        context?.let {
            val intent = DetailActivity(requireContext(), movie)
            startActivity(intent)
        }
    }

    override fun onRefresh() {
        viewModel.fetchMovies(args.movieType)
    }

    private fun stopRefresh() {
        binding.moviesRefresh.isRefreshing = false
    }

    private fun setupViews() {
        binding.apply {
            moviesRefresh.setOnRefreshListener(this@MovieFragment)
            moviesToolbar.setTitle(args.movieTitle)
            moviesList.adapter = movieAdapter
        }
    }

    private fun setupObservables() {
        viewModel.fetchMovies
            .observeOnSuccess(viewLifecycleOwner, ::onSuccess)
            .observeOnError(viewLifecycleOwner, ::onError)
            .observeOnEmpty(viewLifecycleOwner, ::onEmptyState)
            .observeOnLoading(viewLifecycleOwner, ::onLoading)
    }

    private fun onSuccess(movies: List<Movie>) {
        stopRefresh()
        movieAdapter.movies = movies
    }

    private fun onError(cause: Throwable) {
        stopRefresh()

        Timber.e(cause, "Failure to fetch movies")

        Snackbar.make(
            binding.root,
            R.string.generic_message_ops_try_again,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onEmptyState() {
        stopRefresh()
        movieAdapter.movies = emptyList()
    }

    private fun onLoading() {
        binding.moviesRefresh.isRefreshing = true
    }
}
