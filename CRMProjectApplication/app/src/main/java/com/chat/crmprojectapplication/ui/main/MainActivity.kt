package com.chat.crmprojectapplication.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chat.crmprojectapplication.R
import com.chat.crmprojectapplication.data.local.LeadEntity
import com.chat.crmprojectapplication.data.repository.AppDatabase
import com.chat.crmprojectapplication.ui.adpter.LeadAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var adapter: LeadAdapter
    private var leads = mutableListOf<LeadEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDatabase(this)

        val rv = findViewById<RecyclerView>(R.id.rvLeads)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = LeadAdapter(
            leads,
            onClick = { lead ->
                Toast.makeText(this, "Clicked: ${lead.name}", Toast.LENGTH_SHORT).show()
            },
            onDelete = { lead ->
                showDeleteDialog(lead)
            }
        )
        rv.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)
        fab.setOnClickListener { showAddDialog() }
         val imgArrowBack =findViewById<ImageView>(R.id.imgArrowBack)
        imgArrowBack.setOnClickListener {
            finish()
        }

        loadLeads()
    }

    private fun loadLeads() {
        leads.clear()
        leads.addAll(db.leadDao().getAllLeads())
        adapter.notifyDataSetChanged()
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_lead, null)
        val name = dialogView.findViewById<EditText>(R.id.etFullName)
        val company = dialogView.findViewById<EditText>(R.id.etCompany)
        val email = dialogView.findViewById<EditText>(R.id.etEmail)
        val phone = dialogView.findViewById<EditText>(R.id.etPhone)

        AlertDialog.Builder(this)
            .setTitle("Add Lead")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val lead = LeadEntity(
                    name = name.text.toString(),
                    company = company.text.toString(),
                    email = email.text.toString(),
                    phone = phone.text.toString(),
                    status = "New",
                    notes = null
                )
                db.leadDao().insertLead(lead)
                loadLeads()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDeleteDialog(lead: LeadEntity) {
        AlertDialog.Builder(this)
            .setTitle("Delete Lead")
            .setMessage("Are you sure you want to delete ${lead.name}?")
            .setPositiveButton("Yes") { _, _ ->
                db.leadDao().deleteLead(lead)
                adapter.removeLead(lead)
                Toast.makeText(this, "Lead deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .show()
    }
}
