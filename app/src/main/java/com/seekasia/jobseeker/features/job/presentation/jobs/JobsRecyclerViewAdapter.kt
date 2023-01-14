package com.seekasia.jobseeker.features.job.presentation.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.seekasia.jobseeker.features.job.data.model.JobModel
import com.seekasia.jobseeker.databinding.JobsListItemBinding

class JobsRecyclerViewAdapter:
    PagingDataAdapter<JobModel, JobsRecyclerViewAdapter.JobViewHolder>(JobComparator) {
    var jobClickListener: JobClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        JobViewHolder(
            JobsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class JobViewHolder(private val binding: JobsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                jobClickListener?.onJobClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as JobModel
                )
            }
        }

        fun bind(item: JobModel) = with(binding) {
            ViewCompat.setTransitionName(binding.positionTitle, "positionTitle_${item.id}")
            job = item
        }
    }

    object JobComparator : DiffUtil.ItemCallback<JobModel>() {
        override fun areItemsTheSame(oldItem: JobModel, newItem: JobModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: JobModel, newItem: JobModel) =
            oldItem == newItem
    }


    interface JobClickListener {
        fun onJobClicked(binding: JobsListItemBinding, job: JobModel)
    }
}