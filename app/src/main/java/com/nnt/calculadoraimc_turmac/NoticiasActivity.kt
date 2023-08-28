package com.nnt.calculadoraimc_turmac

import NewsAdapter
import RssParser
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.nnt.calculadoraimc_turmac.databinding.ActivityNoticiasBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NoticiasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticiasBinding
    private val rssUrl = "https://www.uol.com.br/esporte/ultimas/index.xml"
    private val rssParser = RssParser(rssUrl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainScope().launch(Dispatchers.Main) {
            val rssItems = rssParser.fetchRssItems()

            // Configurar o RecyclerView e o adaptador para exibir as notícias
            val newsAdapter = NewsAdapter(rssItems)
            binding.recyclerView.adapter = newsAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this@NoticiasActivity)

        }
    }
}
