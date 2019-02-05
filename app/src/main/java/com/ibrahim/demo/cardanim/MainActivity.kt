package com.ibrahim.demo.cardanim

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.demo.cardanim.databinding.ActivityMainBinding
import com.ibrahim.demo.cardanim.model.Person
import com.ibrahim.demo.cardanim.model.RetrofitFactory
import com.ibrahim.demo.cardanim.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast
import retrofit2.HttpException

class MainActivity : FragmentActivity() {


    lateinit var binding: ActivityMainBinding
    private val personAdapter : PersonRecyclerviewAdapter = PersonRecyclerviewAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = personAdapter
        val service = RetrofitFactory.makeRetrofitService()
        GlobalScope.launch (Dispatchers.Main){
            val request = service.randomUser();
            try {
                val response = request.await()
                if (response.isSuccessful){
                    val result = response.body()
                    /*viewModel.personList.observe(this@MainActivity,
                            ) = result?.results?.toMutableList()*/
                    viewModel.personList.value = result?.results
                    viewModel.personList.observe(this@MainActivity,
                        Observer<List<Person>> { it?.let{ personAdapter.replaceData(it)} })

                }
                Log.d("RETRO", response.body().toString())
            }
            catch (e: HttpException){
                toast(e.code())
            } catch (e: Throwable){
                toast("Ooops: Something else went wrong")
            }
            textView.text = "I have changed after 10 seconds"
        }
    }
}
