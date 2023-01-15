package com.seekasia.jobseeker.features.job.presentation.jobs

import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.seekasia.jobseeker.core.presentation.base.BaseFragment
import com.seekasia.jobseeker.R
import com.seekasia.jobseeker.features.job.data.model.JobModel
import com.seekasia.jobseeker.databinding.FragmentJobsBinding
import com.seekasia.jobseeker.databinding.JobsListItemBinding
import com.seekasia.jobseeker.core.presentation.base.PagingLoadStateAdapter
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
                swipeRefresh.setOnRefreshListener {
                    refresh()
                    viewModel.refreshJobs()
                }
                jobClickListener = this@JobsFragment
            }
        }
    }

    private fun initObservers() = with(viewModel) {
        launchOnLifecycleScope {
            jobsStateFlow.collectLatest {
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
        binding.jobsRecyclerView.visibility = View.GONE

        binding.swipeRefresh.isRefreshing = false
        with(binding.networkStateLayout) {
            root.visibility = View.VISIBLE
            errorMsg.visibility = View.VISIBLE
            retryButton.visibility = View.VISIBLE
            errorMsg.text = message

            retryButton.setOnClickListener {
                jobsAdapter.refresh()
                viewModel.refreshJobs()
            }
        }
    }

    private suspend fun bindNotLoadingState() {
        binding.jobsRecyclerView.visibility = View.VISIBLE
        binding.swipeRefresh.isRefreshing = false
        with(binding.networkStateLayout) {
            if(jobsAdapter.snapshot().isEmpty()) {
                root.visibility = View.VISIBLE
                errorMsg.visibility = View.VISIBLE
                retryButton.visibility = View.GONE
                errorMsg.text = getString(R.string.no_results_message)
            } else {
                root.visibility = View.GONE
            }
        }
    }

    override fun onJobClicked(binding: JobsListItemBinding, job: JobModel) {
        val extras = FragmentNavigatorExtras(
            binding.positionTitle to "positionTitle_${job.positionTitle}_${job.id}",
        )

        findNavController().navigate(
            JobsFragmentDirections.actionJobsToJobDetails(job),
            extras
        )
    }
}