package com.lgdev.kotlindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lgdev.kotlindemo.adapter.PostsAdapter
import com.lgdev.kotlindemo.model.Post
import com.lgdev.kotlindemo.retrofit.IMyApi
import com.lgdev.kotlindemo.retrofit.RetrofitClient
import com.lgdev.kotlindemo.utils.CustomItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var jsonApi: IMyApi
    internal var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init API
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(IMyApi::class.java)

        //val compositeDisposable = CompositeDisposable()

        //View
        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)
        val itemDecor: CustomItemDecoration = CustomItemDecoration(24, RecyclerView.VERTICAL)
        recycler_posts.addItemDecoration(itemDecor)
//        val snapHelper: SnapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(recycler_posts)

        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(
            jsonApi.posts
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { posts -> displayData(posts) }
        )
    }

    private fun displayData(posts: List<Post>?) {
        val adapter = PostsAdapter(this, posts!!)
        recycler_posts.adapter = adapter
    }
}
