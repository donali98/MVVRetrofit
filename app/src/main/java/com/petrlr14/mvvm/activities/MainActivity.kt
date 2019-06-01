package com.petrlr14.mvvm.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.petrlr14.mvvm.R
import com.petrlr14.mvvm.adapter.ReposAdapter
import com.petrlr14.mvvm.database.viewmodels.GitHubRepoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter:ReposAdapter
    lateinit var viewModel:GitHubRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()
    }

    private fun bind(){
        adapter= ReposAdapter(ArrayList())
        viewModel=ViewModelProviders.of(this).get(GitHubRepoViewModel::class.java)
        rv_repos.apply {
            adapter=this@MainActivity.adapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
        btn_repo.setOnClickListener{
            viewModel.retreiveRepos(et_repo.text.toString())
            viewModel.getAll().observe(this, Observer { it1 ->
                /*it1.forEach {
                    Log.d("CUSTOM",it.name)
                }*/
                adapter.updateList(it1)
            })
            /*viewModel.getAll(et_repo.text.toString()).observe(this, Observer {
                if(it.isEmpty())
                    Toast.makeText(this,"no data found",Toast.LENGTH_SHORT).show()
            })*/
        }
//        btn_repo.setOnClickListener {
//            viewModel.(et_repo.text.toString())
//        }
    }

}
