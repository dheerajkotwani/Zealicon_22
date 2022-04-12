package project.gdsc.zealicon22.search_events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.ItemCategoriesBinding

/**
 * @Author: Karan Verma
 * @Date: 05/04/22
 */

class SearchEventsCategoryAdapter(private val categoryList: List<String>) : RecyclerView.Adapter<SearchEventsCategoryAdapter.VH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context),parent, false)
    )

    var onItemClick: ((String) -> Unit)? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        with (holder) {
            binding.day.text = categoryList[position]

            when (position % 3) {
                0 -> {
                    Glide.with(binding.categoryImage)
                        .load(R.drawable.events_background1)
                        .into(binding.categoryImage)
                }
                1 -> {
                    binding.root.rotation = -3f
                    Glide.with(binding.categoryImage)
                        .load(R.drawable.events_background2)
                        .into(binding.categoryImage)
                }
                2 -> {
                    binding.root.rotation = 3f
                    Glide.with(binding.categoryImage)
                        .load(R.drawable.events_background3)
                        .into(binding.categoryImage)
                }
            }
            binding.root.scaleX = 0.9f
            binding.root.scaleY = 0.9f

            itemView.setOnClickListener {
                onItemClick?.invoke(categoryList[position])
            }
        }
    }

    override fun getItemCount(): Int = categoryList.size

    class VH(val binding : ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root)
}