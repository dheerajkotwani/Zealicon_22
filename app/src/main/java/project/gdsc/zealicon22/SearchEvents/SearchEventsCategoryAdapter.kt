package project.gdsc.zealicon22.SearchEvents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.databinding.ItemCategoriesBinding

/**
 * @Author: Karan Verma
 * @Date: 05/04/22
 */

class SearchEventsCategoryAdapter(private val categoryList: List<String>) : RecyclerView.Adapter<SearchEventsCategoryAdapter.VH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context),parent, false)
    )


    override fun onBindViewHolder(holder: SearchEventsCategoryAdapter.VH, position: Int) {
        holder.binding.day.text = categoryList[position]

        if (position == 1 || position == 4){
            holder.binding.root.rotation = -3f
        }else if (position == 2 || position == 5){
            holder.binding.root.rotation = 3f
        }
        holder.binding.root.scaleX = 0.9f
        holder.binding.root.scaleY = 0.9f

    }

    override fun getItemCount(): Int = categoryList.size

    class VH(val binding : ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root)
}