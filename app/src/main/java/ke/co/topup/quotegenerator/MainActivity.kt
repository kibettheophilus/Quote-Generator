 package ke.co.topup.quotegenerator

import android.content.Intent
import android.os.Bundle
import android.view.View
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

        binding.toolbar.setOnClickListener {
            Toast.makeText(this,"Good things on the way",Toast.LENGTH_LONG).show()
        }

        binding.share.setOnClickListener {
            shareQuote()
        }
    }


     fun setupObservers(){
         val progressbar = binding.progressBar

         binding.button.setOnClickListener {

             mainViewModel.getRandomQuote().observe(this, Observer {
                     response ->

                 response?.let { resource ->

                     when (resource.status) {
                         Status.SUCCESS -> {
                             progressbar.visibility = View.GONE
                             lifecycleScope.launch {
                                 binding.tvQuote.text = response.data?.quote
                                 binding.authorName.text = response.data?.author
                             }

                         }
                         Status.LOADING -> {
                             progressbar.visibility = View.VISIBLE
                            // Toast.makeText(this, "Loading ... ", Toast.LENGTH_SHORT).show()
                         }
                         Status.ERROR -> {
                             progressbar.visibility = View.GONE
                             Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                         }
                     }
                 }
             })
         }
     }


     private fun shareQuote() {
         val quote = binding.tvQuote.text.toString()
         val intent = Intent(Intent.ACTION_SEND)
         intent.type = "text/plain"
         intent.putExtra(Intent.EXTRA_TEXT, "$quote  Download Quote Generator https://play.google.com/store/apps/details?id=ke.co.topup.quotegenerator")
         startActivity(Intent.createChooser(intent, "Share Via:"))
     }


 }