package project.gdsc.zealicon22.SearchEvents

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.databinding.ItemSearchEventsBinding
import timber.log.Timber

/**
 * @Author: Karan Verma
 * @Date: 05/04/22
 */
class SearchEventsAdapter(private val searchList: List<String>) : RecyclerView.Adapter<SearchEventsAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemSearchEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        Timber.d(searchList.toString())
        holder.binding.searchTitle.text = searchList[position]
    }

    override fun getItemCount(): Int = searchList.size

    class VH(val binding: ItemSearchEventsBinding) : RecyclerView.ViewHolder(binding.root)
}