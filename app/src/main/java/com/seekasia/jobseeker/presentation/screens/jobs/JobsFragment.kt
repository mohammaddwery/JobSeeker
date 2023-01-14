package com.seekasia.jobseeker.presentation.screens.jobs

import android.view.View
import android.widget.Toast
import androidx.paging.LoadState
import androidx.paging.map
import com.seekasia.jobseeker.presentation.screens.base.BaseFragment
import com.seekasia.jobseeker.R
import com.seekasia.jobseeker.data.model.JobModel
import com.seekasia.jobseeker.databinding.FragmentJobsBinding
import com.seekasia.jobseeker.databinding.JobsListItemBinding
import com.seekasia.jobseeker.presentation.screens.base.PagingLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel

class JobsFragment : BaseFragment<FragmentJobsBinding, JobsFragmentViewModel>(),
    JobsRecyclerViewAdapter.JobClickListener {

    private val viewModel: JobsFragmentViewModel by viewModel()
    private val jobsAdapter by lazy { JobsRecyclerViewAdapter() }
    private val itemDecorator by lazy {
        JobsMarginDecoration(
            requireContext(),
            R.dimen.dp_16
        )
    }

    override fun layoutId(): Int = R.layout.fragment_jobs

    override fun initFragment() {
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        with(binding) {
            with(jobsAdapter) {
                jobsRecyclerView.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                    addItemDecoration(itemDecorator)
                    adapter = withLoadStateHeaderAndFooter(
                        header = PagingLoadStateAdapter(jobsAdapter),
                        footer = PagingLoadStateAdapter(jobsAdapter)
                    )
                }
                swipeRefresh.setOnRefreshListener { refresh() }
                jobClickListener = this@JobsFragment
            }
        }
    }

    private fun initObservers() = with(viewModel) {
        launchOnLifecycleScope {
            jobsFlow.collectLatest {
                jobsAdapter.submitData(it)
            }
        }

        launchOnLifecycleScope {
            jobsAdapter.loadStateFlow.map { it.refresh }.collectLatest {
                when (it) {
                    is LoadState.Loading -> bindLoadingState()
                    is LoadState.Error -> bindFailureState(it.error.message?:"")
                    is LoadState.NotLoading -> bindNotLoadingState()
                }
            }
        }
    }

    private fun bindLoadingState() {
        binding.swipeRefresh.isRefreshing = true

        with(binding.networkStateLayout) {
            root.visibility = View.VISIBLE
            errorMsg.visibility = View.GONE
            retryButton.visibility = View.GONE
        }
    }

    private fun bindFailureState(message: String) {
        binding.swipeRefresh.isRefreshing = false

        with(binding.networkStateLayout) {
            root.visibility = View.VISIBLE
            errorMsg.visibility = View.VISIBLE
            retryButton.visibility = View.VISIBLE
            errorMsg.text = message

            retryButton.setOnClickListener {
                jobsAdapter.refresh()
            }
        }
    }

    private fun bindNotLoadingState() {
        binding.swipeRefresh.isRefreshing = false

        with(binding.networkStateLayout) {
            root.visibility = View.GONE
        }
    }

    override fun onJobClicked(binding: JobsListItemBinding, job: JobModel) {
        Toast.makeText(context, job.positionTitle, Toast.LENGTH_SHORT).show()
    }
}