package project.gdsc.zealicon22.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.ItemEventCardBinding
import project.gdsc.zealicon22.interfaces.ConstraintInstructions
import project.gdsc.zealicon22.utils.updateConstraints


class EventsAdapter(
    private val events: ArrayList<String>,
    private val context: Context
): RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    class EventViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = ItemEventCardBinding.bind(item)
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
        with(holder){

            binding.eventName.text = events[position]
            if (position % 2 == 0){ // even item case

                // This code disconnects and connects constraints
                // and sends event info Text Views to right side in case of even position element
                // help from here -> [https://medium.com/geekculture/programatically-update-view-constraints-in-constraint-layout-f4eb1b6dfc38]

                val constraintInstructions: List<ConstraintInstructions> = listOf(
                    ConstraintInstructions.DisconnectConstraint(binding.eventName.id, ConstraintSet.START),
                    ConstraintInstructions.ConnectConstraint(binding.eventName.id, ConstraintSet.END, 0, ConstraintSet.END),

                    ConstraintInstructions.DisconnectConstraint(binding.eventDate.id, ConstraintSet.START),
                    ConstraintInstructions.ConnectConstraint(binding.eventDate.id, ConstraintSet.END, 0, ConstraintSet.END),

                    ConstraintInstructions.DisconnectConstraint(binding.eventSociety.id, ConstraintSet.START),
                    ConstraintInstructions.ConnectConstraint(binding.eventSociety.id, ConstraintSet.END, 0, ConstraintSet.END),

                    ConstraintInstructions.DisconnectConstraint(binding.eventWebImg.id, ConstraintSet.END),
                    ConstraintInstructions.ConnectConstraint(binding.eventWebImg.id, ConstraintSet.START, 0, ConstraintSet.START),
                    ConstraintInstructions.DisconnectConstraint(binding.eventWebImg.id, ConstraintSet.TOP),
                    ConstraintInstructions.ConnectConstraint(binding.eventWebImg.id, ConstraintSet.BOTTOM, 0, ConstraintSet.BOTTOM)
                )
                binding.eventInfoCardConstraint.updateConstraints(constraintInstructions)

                // flipping event_web_img about x and y
                binding.eventWebImg.apply {
                    rotationX = 180f
                    rotationY = 180f
                }


            } else { // odd item case

            }
        }
    }

    override fun getItemCount() = events.size

}
