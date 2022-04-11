package project.gdsc.zealicon22.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.ItemEventCardBinding
import project.gdsc.zealicon22.interfaces.ConstraintInstructions
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.utils.animateOnClick
import project.gdsc.zealicon22.utils.updateConstraints

/**
 * Created by Nakul
 * on 11,April,2022
 */
class UpcomingEventsAdapter(val onEventClick: (event: Events) -> Unit): RecyclerView.Adapter<UpcomingEventsAdapter.VH>() {
    class VH(val binding: ItemEventCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemEventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    private val list: ArrayList<Events> = arrayListOf()
    
    @SuppressLint("NotifyDataSetChanged")
    fun setList(_list: List<Events>) {
        list.clear()
        list.addAll(_list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        with(holder) {

            binding.eventName.text = list[position].name
            if (position % 2 == 0) { 
                val constraintInstructions: List<ConstraintInstructions> = listOf(
                    ConstraintInstructions.DisconnectConstraint(
                        binding.eventName.id,
                        ConstraintSet.START
                    ),
                    ConstraintInstructions.ConnectConstraint(
                        binding.eventName.id,
                        ConstraintSet.END,
                        0,
                        ConstraintSet.END
                    ),

                    ConstraintInstructions.DisconnectConstraint(
                        binding.eventDate.id,
                        ConstraintSet.START
                    ),
                    ConstraintInstructions.ConnectConstraint(
                        binding.eventDate.id,
                        ConstraintSet.END,
                        0,
                        ConstraintSet.END
                    ),

                    ConstraintInstructions.DisconnectConstraint(
                        binding.eventSociety.id,
                        ConstraintSet.START
                    ),
                    ConstraintInstructions.ConnectConstraint(
                        binding.eventSociety.id,
                        ConstraintSet.END,
                        0,
                        ConstraintSet.END
                    ),

                    ConstraintInstructions.DisconnectConstraint(
                        binding.eventWebImg.id,
                        ConstraintSet.END
                    ),
                    ConstraintInstructions.ConnectConstraint(
                        binding.eventWebImg.id,
                        ConstraintSet.START,
                        0,
                        ConstraintSet.START
                    ),
                    ConstraintInstructions.DisconnectConstraint(
                        binding.eventWebImg.id,
                        ConstraintSet.TOP
                    ),
                    ConstraintInstructions.ConnectConstraint(
                        binding.eventWebImg.id,
                        ConstraintSet.BOTTOM,
                        0,
                        ConstraintSet.BOTTOM
                    )
                )
                binding.eventInfoCardConstraint.updateConstraints(constraintInstructions)

                // flipping event_web_img about x and y
                binding.eventWebImg.apply {
                    rotationX = 180f
                    rotationY = 180f
                }
            }

            when (position % 3) {
                0 -> {
                    binding.eventConstraint.rotation = 0f
                    setEventBackgroundImage(binding, R.drawable.events_background1)
                }
                1 -> {
                    binding.eventConstraint.rotation = -ROTATION_CONST
                    setEventBackgroundImage(binding, R.drawable.events_background2)
                }
                2 -> {
                    binding.eventConstraint.rotation = ROTATION_CONST
                    setEventBackgroundImage(binding, R.drawable.events_background3)
                }
            }
            binding.eventConstraint.setOnClickListener {
                animateOnClick(it)
                onEventClick(list[position])
            }
        }
    }

    private fun setEventBackgroundImage(binding: ItemEventCardBinding, drawableId: Int) {
        binding.eventBackgroundImage.setImageDrawable(
            ResourcesCompat.getDrawable(
                binding.root.context.resources,
                drawableId,
                null
            )
        )
    }

    override fun getItemCount() = list.size

    companion object {
        const val ROTATION_CONST = 2f
    }
}