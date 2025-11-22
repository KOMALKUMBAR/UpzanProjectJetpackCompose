package com.chat.crmprojectapplication.model

import com.chat.crmprojectapplication.data.local.LeadEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("leads")
    fun getLeads(): Call<List<LeadEntity>>

    @POST("leads")
    fun addLead(@Body lead: LeadEntity): Call<LeadEntity>
}
