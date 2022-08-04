package com.bchmsl.homework14.ui.posts

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bchmsl.homework14.adapters.PostsAdapter
import com.bchmsl.homework14.databinding.FragmentPostsBinding
import com.bchmsl.homework14.model.PostsResponse
import com.bchmsl.homework14.ui.BaseFragment
import com.bchmsl.homework14.utils.ResponseHandler
import kotlinx.coroutines.launch

class PostsFragment : BaseFragment<FragmentPostsBinding>(
    FragmentPostsBinding::inflate
) {
    private val viewModel: PostsViewModel by viewModels()
    private val postsAdapter by lazy { PostsAdapter() }

    override fun init() {
        getData()
        listeners()
    }

    private fun listeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getData()
            binding.swipeRefresh.isRefreshing = false
        }
        postsAdapter.itemClick = {
            findNavController().navigate(PostsFragmentDirections.actionPostsFragmentToPostOpenedFragment(it))
        }
    }

    private fun getData() {
        Log.wtf("TAG", "GetData()")
        viewModel.getData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.response.collect {
                binding.progressBar.isVisible = it.isLoading
                when (it) {
                    is ResponseHandler.Success -> displaySuccess(it.successData)
                    is ResponseHandler.Error -> displayError(it.errorMessage)
                    else -> {}
                }
            }
        }
    }

    private fun displaySuccess(successData: PostsResponse?) {
        binding.rvPosts.adapter = postsAdapter
        postsAdapter.submitList(successData?.content)
    }

    private fun displayError(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }
}