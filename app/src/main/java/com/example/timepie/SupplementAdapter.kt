package com.example.timepie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timepie.models.Supplement

class SupplementAdapter(val context: Context, private val supplements: List<Supplement>)
    : RecyclerView.Adapter<SupplementAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_supplement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val supplement = supplements[position]
        holder.bind(supplement)
    }

    override fun getItemCount(): Int {
        return supplements.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * name : String
         * createdAt : String
         * description : String
         * frequency : Number
         * user : User
         */
        private val name: TextView = itemView.findViewById(R.id.supplementName)
//        private val createdAt: ImageView = itemView.findViewById(R.id.createdAt)
//        private val description: TextView = itemView.findViewById(R.id.description)
        private val frequency: TextView = itemView.findViewById(R.id.frequency)

        fun bind(supplement: Supplement) {
            name.text = supplement.getName()
            frequency.text = "${supplement.getFrequency().toString()} times daily"
        }
    }
}