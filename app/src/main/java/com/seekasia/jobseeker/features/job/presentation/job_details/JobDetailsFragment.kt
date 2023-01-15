package com.seekasia.jobseeker.features.job.presentation.job_details

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.seekasia.jobseeker.R
import com.seekasia.jobseeker.core.presentation.base.BaseFragment
import com.seekasia.jobseeker.databinding.FragmentJobDetailsBinding

class JobDetailsFragment : BaseFragment<FragmentJobDetailsBinding, JobDetailsViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_job_details

    private val args: JobDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun initFragment() {
        bindInitialState()
    }

    private fun bindInitialState() {
        ViewCompat.setTransitionName(binding.positionTitle, "positionTitle_${args.job.positionTitle}_${args.job.id}")
        binding.job = args.job
    }

}