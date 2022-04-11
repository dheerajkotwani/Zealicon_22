package project.gdsc.zealicon22.search_events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.databinding.ItemSearchEventsBinding
import project.gdsc.zealicon22.dayWiseEvent.EventsModel
import project.gdsc.zealicon22.models.Events
import timber.log.Timber

/**
 * @Author: Karan Verma
 * @Date: 05/04/22
 * @Updated: Anuraj Jain on 08/04/22
 */
class SearchEventsAdapter() : RecyclerView.Adapter<SearchEventsAdapter.VH>() {

    private var list: List<Events> = arrayListOf()

    fun setList(data: List<Events>){
        list = data
        notifyDataSetChanged()
    }

    var onItemClick: ((Events) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemSearchEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val comic = list[position]
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(comic)
        }
        holder.onBind(comic)
    }

    override fun getItemCount(): Int = list.size

    class VH(val binding: ItemSearchEventsBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(comic: Events){
            binding.eventTitle.text = comic.name
            binding.eventDateCategory.text = "${comic.day}"
        }
    }
}