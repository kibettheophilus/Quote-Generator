 package ke.co.topup.quotegenerator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import ke.co.topup.quotegenerator.databinding.ActivityMainBinding
import ke.co.topup.quotegenerator.network.RetrofitBuilder
import ke.co.topup.quotegenerator.utils.Status
import ke.co.topup.quotegenerator.viewmodel.MainViewModel
import ke.co.topup.quotegenerator.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

 class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupViewModel()
    }

     private fun setupViewModel() {
         mainViewModel = ViewModelProvider(this, ViewModelFactory(
             RetrofitBuilder.apiClient
             )
         ).get(MainViewModel::class.java)

     }

     fun setupObservers(){

         binding.button.setOnClickListener {

             mainViewModel.getRandomQuote().observe(this, Observer {
                     response ->

                 response?.let { resource ->

                     when (resource.status) {
                         Status.SUCCESS -> {
                             lifecycleScope.launch {

                                 binding.tvQuote.text = response.data?.quote

                                 binding.authorName.text = response.data?.author


                             }

                         }
                         Status.LOADING -> {
                             Toast.makeText(this, "Loading ... ", Toast.LENGTH_SHORT).show()
                         }
                         Status.ERROR -> {
                             Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                         }
                     }
                 }
             })
         }
     }

 }