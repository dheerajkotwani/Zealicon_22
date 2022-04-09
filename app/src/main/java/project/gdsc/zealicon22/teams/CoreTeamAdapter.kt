package project.gdsc.zealicon22.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.gdsc.zealicon22.databinding.ItemTeamBinding
import project.gdsc.zealicon22.utils.CORE

/**
 * @Author: Karan Verma
 * @Date: 08/04/22
 */
class CoreTeamAdapter(private val teamList: ArrayList<TeamsModalItem>) :
    RecyclerView.Adapter<CoreTeamAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.name.text = teamList[position].name
        holder.binding.position.text = teamList[position].position
        Glide.with(holder.itemView).load(teamList[position].image).into(holder.binding.image)
    }

    override fun getItemCount(): Int = teamList.size

    class VH(val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root)
}