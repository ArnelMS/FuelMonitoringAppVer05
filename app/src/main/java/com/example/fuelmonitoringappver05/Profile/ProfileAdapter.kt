package com.example.fuelmonitoringappver05.Profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuelmonitoringappver05.databinding.RowItemProfileBinding

class ProfileAdapter(val profile : MutableList<Profile>):RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

        var onItemClick : ((Profile)-> Unit)? = null
        var onDeleteButtonClick : ((Profile, Int)-> Unit)? = null
        var onUpdateButtonClick : ((Profile, Int)-> Unit)? = null

        inner class ProfileViewHolder(val binding: RowItemProfileBinding): RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RowItemProfileBinding.inflate(layoutInflater, parent, false)
            return ProfileViewHolder(binding)
        }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.binding.apply {
                    tvFullname.text =
                    tvMobile.text
                    tvEmail.text
                    tvVehicleName.text
                    tvVehicleModel.text
                    tvVehiclePlate.text

            imgBtnCancel.setOnClickListener(){
                onDeleteButtonClick?.invoke(profile[position],position)
            }

            imgBtnSave.setOnClickListener(){
                onUpdateButtonClick?.invoke(profile[position],position)
            }
        }

        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(profile[position])

        }
    }

    override fun getItemCount(): Int {
        return profile.size
    }

}