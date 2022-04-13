package project.gdsc.zealicon22.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.ItemEventCardBinding
import project.gdsc.zealicon22.interfaces.ConstraintInstructions
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.MyEvents
import project.gdsc.zealicon22.utils.animateOnClick
import project.gdsc.zealicon22.utils.formatTo
import project.gdsc.zealicon22.utils.getDateTime
import project.gdsc.zealicon22.utils.updateConstraints

/**
 * @Updated: Karan Verma on 13/04/22
 */
class EventsAdapter(
    private val context: Context,
    private val onEventCardClick: (event: Events) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private val ROTATION_CONST = 2f

    private var events: ArrayList<MyEvents> = arrayListOf()

    fun setList(events: ArrayList<MyEvents>){
        this.events = events
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        return EventViewHolder(
            LayoutInflater
                .from(context)
                .inflate(
                    R.layout.item_event_card,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        with(holder) {

            binding.eventName.text = events[position].event.name
            binding.eventDate.text = getDateTime(events[position].event.datetime).formatTo("dd MMM yyyy")
            binding.eventSociety.text = events[position].event.society

            if (position % 2 == 0) { // even item case (shifts info towards right)

                // This code disconnects and connects constraints
                // and sends event info Text Views to right side in case of even position element
                // help from here -> [https://medium.com/geekculture/programatically-update-view-constraints-in-constraint-layout-f4eb1b6dfc38]
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
                onEventCardClick(events[position].event)
            }
        }
    }

    private fun setEventBackgroundImage(binding: ItemEventCardBinding, drawableId: Int) {
        binding.eventBackgroundImage.setImageDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                drawableId,
                null
            )
        )
    }

    override fun getItemCount() = events.size

    class EventViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemEventCardBinding.bind(item)
    }

}
