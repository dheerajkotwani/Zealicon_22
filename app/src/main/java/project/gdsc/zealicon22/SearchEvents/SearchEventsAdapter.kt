package project.gdsc.zealicon22.SearchEvents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.databinding.ItemSearchEventsBinding
import project.gdsc.zealicon22.dayWiseEvent.EventsModel
import timber.log.Timber

/**
 * @Author: Karan Verma
 * @Date: 05/04/22
 * @Updated: Anuraj Jain on 08/04/22
 */
class SearchEventsAdapter() : RecyclerView.Adapter<SearchEventsAdapter.VH>() {

    private var list: List<EventsModel> = arrayListOf()

    fun setList(data: List<EventsModel>){
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemSearchEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        Timber.d(list.toString())
        val comic = list[position]
        holder.onBind(comic)
    }

    override fun getItemCount(): Int = list.size

    class VH(val binding: ItemSearchEventsBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(comic: EventsModel){
            binding.eventTitle.text = comic.title
            binding.eventDateCategory.text = "${comic.date} . ${comic.category}"
        }
    }
}