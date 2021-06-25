 package ke.co.topup.quotegenerator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ke.co.topup.quotegenerator.databinding.ActivityMainBinding
import ke.co.topup.quotegenerator.utils.Status
import ke.co.topup.quotegenerator.viewmodel.MainViewModel
import kotlinx.coroutines.launch


 @AndroidEntryPoint
 class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
       // setupViewModel()
    }

//     private fun setupViewModel() {
//         mainViewModel = ViewModelProvider(this, ViewModelFactory).get(MainViewModel::class.java)
//
//     }

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