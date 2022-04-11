package project.gdsc.zealicon22.reach

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.ReachCardLayoutBinding
import project.gdsc.zealicon22.teams.ContactType
import project.gdsc.zealicon22.teams.TeamsModalItem

/**
 * @author Dheeraj Kotwani on 12/04/22.
 */
class ReachRVAdapter(
    val teamDetails: ArrayList<TeamsModalItem>
): RecyclerView.Adapter<ReachRVAdapter.ReachVH>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReachVH {
        return ReachVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.reach_card_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReachVH, position: Int) {

        with(holder) {
            binding.textName.text = teamDetails[position].name
            binding.textPost.text = teamDetails[position].position
            binding.textMobile.text = teamDetails[position].contact

            binding.root.setOnClickListener {
                when (teamDetails[position].contactType) {
                    ContactType.MOBILE -> {
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:${teamDetails[position].contact}")
                        startActivity(holder.itemView.context, intent, null)
                    }
                    ContactType.EMAIL -> {
                        val intent: Intent = Intent(Intent.ACTION_SEND)
                        intent.type = "plain/text"
                        intent.putExtra(
                            Intent.EXTRA_EMAIL,
                            arrayOf(teamDetails[position].contact)
                        )
                        startActivity(holder.itemView.context, intent, null)
                    }
                }
            }

            Glide.with(binding.image)
                .load(teamDetails[position].image)
                .into(binding.image)
        }
    }

    override fun getItemCount(): Int = teamDetails.size

    inner class ReachVH(itemView: View): RecyclerView.ViewHolder(itemView)  {
        val binding: ReachCardLayoutBinding = ReachCardLayoutBinding.bind(itemView)
    }
}