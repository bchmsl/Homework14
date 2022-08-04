package com.bchmsl.homework14.ui.post_opened

import android.util.Log
import androidx.navigation.fragment.navArgs
import com.bchmsl.homework14.databinding.FragmentPostOpenedBinding
import com.bchmsl.homework14.extensions.setImage
import com.bchmsl.homework14.extensions.toDate
import com.bchmsl.homework14.model.PostsResponse
import com.bchmsl.homework14.ui.BaseFragment

class PostOpenedFragment :
    BaseFragment<FragmentPostOpenedBinding>(FragmentPostOpenedBinding::inflate) {

    private val args: PostOpenedFragmentArgs by navArgs()

    override fun init() {
        val post = args.post as PostsResponse.Post
        setData(post)
    }

    private fun setData(post: PostsResponse.Post) {
        binding.apply {
            tvDate.text = post.publishDate.toDate()
            tvTitle.text = post.titleKA
            tvDescription.text = post.descriptionKA
            post.cover?.let { ivCover.setImage(it) }
        }
    }

}