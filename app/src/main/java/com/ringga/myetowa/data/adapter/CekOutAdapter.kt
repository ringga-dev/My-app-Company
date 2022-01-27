package com.ringga.myetowa.data.adapter

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.SerializedName
import com.ringga.myetowa.R
import com.ringga.myetowa.data.utils.toast
import com.ringga.myetowa.databinding.ItemListCekOutBinding
import com.ringga.myetowa.model.CekOut
import com.ringga.myetowa.ui.home.DitailDataActivity
import com.ringga.myetowa.ui.profile.ProfileActivity

class CekOutAdapter(
    private var lagus: MutableList<CekOut>,
    private var context: Context,
    private var supportFragmentManager: FragmentManager,
) : RecyclerView.Adapter<CekOutAdapter.PageHolder>() {

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListCekOutBinding.bind(view)

    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        with(holder) {

            binding.name.text = ": ${lagus[position].name}"
            binding.badge.text = ": ${lagus[position].badge}"
            binding.destination.text = ": ${lagus[position].destination}"
            binding.plan.text = ": ${lagus[position].plan}"
            binding.sttsForm.text = ": ${lagus[position].stts_form}"

            binding.btnCek.setOnClickListener {
                toast(context, "cek data")
                val i = Intent(context, DitailDataActivity::class.java)
                i.putExtra("id", lagus[position].id)
                i.putExtra("badge", lagus[position].badge)
                i.putExtra("plan", lagus[position].plan)
                i.putExtra("end_time", lagus[position].end_time)
                i.putExtra("destination", lagus[position].destination)
                i.putExtra("remarks", lagus[position].remarks)
                i.putExtra("stts_form", lagus[position].stts_form)
                i.putExtra("from", lagus[position].from)
                i.putExtra("to", lagus[position].to)
                i.putExtra("approved_by", lagus[position].approved_by)
                i.putExtra("date_out", lagus[position].date_out)
                i.putExtra("date_in", lagus[position].date_in)
                i.putExtra("update", lagus[position].update)
                i.putExtra("name", lagus[position].name)
                context.startActivity(i)
            }
        }
    }

    fun setLagu(r: List<CekOut>) {
        lagus.clear()
        lagus.addAll(r)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        return PageHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_list_cek_out, parent, false)
        )
    }

    override fun getItemCount() = lagus.size
}