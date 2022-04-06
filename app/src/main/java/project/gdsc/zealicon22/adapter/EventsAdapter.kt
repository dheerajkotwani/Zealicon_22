package project.gdsc.zealicon22.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.ItemEventCardBinding


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

            } else { // odd item case

            }
        }
    }

    override fun getItemCount() = events.size

}