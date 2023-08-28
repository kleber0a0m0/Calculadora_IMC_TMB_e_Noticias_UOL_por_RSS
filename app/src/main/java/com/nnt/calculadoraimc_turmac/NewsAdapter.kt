import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.nnt.calculadoraimc_turmac.R.layout.item_news

import com.nnt.calculadoraimc_turmac.R

fun extractDomainFromUrl(url: String): String {
    val regex = Regex("(https?://)([^/]+)")
    val matchResult = regex.find(url)
    return matchResult?.groupValues?.getOrNull(2) ?: ""
}

class NewsAdapter(private val newsList: List<RssParser.RssItem>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtLink: TextView = itemView.findViewById(R.id.txtLink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.txtTitulo.text = newsItem.title.trim()
        //log doi titulo
        Log.d("Titulo", "|"+newsItem.title.trim()+"|")
        holder.txtLink.text = extractDomainFromUrl(newsItem.link).trim()


        holder.itemView.setOnClickListener {
            val url = newsItem.link.trim()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            val context = holder.itemView.context

            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "Nenhum navegador compatível encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
