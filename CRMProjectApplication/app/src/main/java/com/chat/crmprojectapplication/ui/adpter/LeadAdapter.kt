package com.chat.crmprojectapplication.ui.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chat.crmprojectapplication.R
import com.chat.crmprojectapplication.data.local.LeadEntity

class LeadAdapter(
    private val leads: MutableList<LeadEntity>,
    val onClick: (LeadEntity) -> Unit,
    val onDelete: (LeadEntity) -> Unit
) : RecyclerView.Adapter<LeadAdapter.LeadViewHolder>() {

    class LeadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val company: TextView = itemView.findViewById(R.id.tvCompany)
        val status: TextView = itemView.findViewById(R.id.tvStatus)
        val delete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lead, parent, false)
        return LeadViewHolder(view)
    }

    override fun getItemCount() = leads.size

    override fun onBindViewHolder(holder: LeadViewHolder, position: Int) {
        val lead = leads[position]
        holder.name.text = lead.name
        holder.company.text = lead.company
        holder.status.text = lead.status

        holder.itemView.setOnClickListener { onClick(lead) }
        holder.delete.setOnClickListener { onDelete(lead) }
    }

    fun removeLead(lead: LeadEntity) {
        val index = leads.indexOf(lead)
        if (index != -1) {
            leads.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
