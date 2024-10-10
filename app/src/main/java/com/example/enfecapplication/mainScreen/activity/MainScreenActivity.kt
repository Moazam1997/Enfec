package com.example.enfecapplication.mainScreen.activity

import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enfecapplication.databinding.ActivityMainScreenBinding
import com.example.enfecapplication.mainScreen.adapter.PostAdapter
import com.example.enfecapplication.mainScreen.model.PostData
import com.example.enfecapplication.mainScreen.model.UsersData
import com.example.enfecapplication.mainScreen.viewmodel.MainScreenViewModel
import com.example.enfecapplication.ui.base.BaseActivity
import com.example.enfecapplication.ui.base.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenActivity : BaseActivity<MainScreenViewModel, ActivityMainScreenBinding>() {


    private var adapterYoga: PostAdapter? = null

    val postItemsOriginal = mutableListOf <PostData>()
    val Ids = mutableListOf <UsersData>()

    override fun setUpViewModel(): MainScreenViewModel {
        viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]
        return viewModel
    }

    override fun getViewBinding(): ActivityMainScreenBinding {
        return ActivityMainScreenBinding.inflate(layoutInflater)
    }

    override fun setupUI() {


        viewModel.getUserData()

    }

    override fun setupObserver() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiStateUserData.collect {
                    when (it) {
                        is UiState.Success -> {
                            Log.e("DATAAPI", "${it}")
                            renderPostList(it.data)
                        }

                        is UiState.Error -> {
                            Log.e("DATAAPI", "Error collecting user: ${it.message}")
                        }

                        is UiState.Loading -> {
                            Log.e("DATAAPI", "Loading collecting user: ")
                        }
                    }

                }
            }
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiStatePostData.collect {
                    when (it) {
                        is UiState.Success -> {
                            Log.e("DATAAPI", "post ${it}")

                            renderList(it.data)
                        }

                        is UiState.Error -> {
                            Log.e("DATAAPI", "Error collecting pilates: ${it.message}")
                        }

                        is UiState.Loading -> {
                            Log.e("DATAAPI", "Loading collecting pilates: ")
                        }
                    }

                }
            }
        }

    }

    override fun getIntentAndFetchData() {

    }



    fun renderPostList(userItems : List<UsersData>)
    {
        Log.e("DATAAPI", "renderPost")
        for(item in userItems)
        {
            postItemsOriginal.add(PostData(uID = item.id, geo = item.address!!.geo, company = item.company))
            viewModel.getPostData(item.id!!.toInt())
        }

    }

    fun renderList(postItems : List<PostData>)
    {

        postItems.forEach { postItem ->
            // Find the index of the existing item with the same userId
            val matchingItemIndex = postItemsOriginal.indexOfFirst { it.uID == postItem.userId }

            if (matchingItemIndex != -1) {
                // If found, update the existing item's title and body
                postItemsOriginal[matchingItemIndex] = postItemsOriginal[matchingItemIndex].copy(
                    title = postItem.title,
                    body = postItem.body
                )
            } else {
                // If not found, add a new item to the list
                postItemsOriginal.add(
                    PostData(
                        title = postItem.title,
                        body = postItem.body,
                        userId = postItem.userId,
                        company = postItem.company,
                        geo = postItem.geo
                    )
                )
            }
        }

        Log.e("DATAAPI", "Final post items: $postItemsOriginal")

        // Update RecyclerView
        adapterYoga = PostAdapter(postItemsOriginal)
        binding.recyclerList.layoutManager = LinearLayoutManager(this)
        binding.recyclerList.adapter = adapterYoga

    }



}