package com.medicus.app.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T : Any, VB : ViewDataBinding> :
    RecyclerView.Adapter<BaseRecyclerAdapter.Companion.BaseViewHolder<VB>>() {

    abstract fun DIFF_CALLBACK(): DiffUtil.ItemCallback<T>

    val differ = AsyncListDiffer(this, DIFF_CALLBACK())

    fun setList(items: List<T>) = differ.submitList(items)

    override fun getItemCount() = differ.currentList.size

    abstract fun getLayout(): Int

    var listener: ((item: T, binding: VB) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<VB>(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayout(),
            parent,
            false
        )
    )

    companion object {
        class BaseViewHolder<VB : ViewDataBinding>(var binding: VB) : RecyclerView.ViewHolder(binding.root)
    }

}