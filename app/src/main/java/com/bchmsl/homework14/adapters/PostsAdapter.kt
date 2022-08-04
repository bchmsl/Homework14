package com.bchmsl.homework14.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bchmsl.homework14.databinding.LayoutPostBinding
import com.bchmsl.homework14.extensions.setImage
import com.bchmsl.homework14.extensions.toDate
import com.bchmsl.homework14.model.PostsResponse

class PostsAdapter : ListAdapter<PostsResponse.Post, PostsAdapter.PostsViewHolder>(PostCallback()) {

    var itemClick: ((PostsResponse.Post) -> Unit)? = null

    inner class PostsViewHolder(private val binding: LayoutPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = currentList[adapterPosition]
            binding.apply {
                tvTitle.text = currentItem.titleKA
                tvDate.text = currentItem.publishDate.toDate()
                tvDescription.text = currentItem.descriptionKA
                currentItem.cover?.let { ivCover.setImage(it) }
                root.setOnClickListener{itemClick?.invoke(currentItem)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostsViewHolder(
            LayoutPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind()
    }

    class PostCallback : DiffUtil.ItemCallback<PostsResponse.Post>() {
        override fun areItemsTheSame(
            oldItem: PostsResponse.Post,
            newItem: PostsResponse.Post
        ): Boolean = oldItem.id == newItem.id


        override fun areContentsTheSame(
            oldItem: PostsResponse.Post,
            newItem: PostsResponse.Post
        ): Boolean = oldItem == newItem


    }
}