package com.example.fuelmonitoringappver05

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuelmonitoringappver05.databinding.RowItemBinding

class ConsumptionAdapter(val consumptions : List<Consumptions>):RecyclerView.Adapter<ConsumptionAdapter.ConsumptionViewHolder>() {

    var onItemClick : ((Consumptions)-> Unit)? = null

    inner class ConsumptionViewHolder(val binding:RowItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsumptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(layoutInflater, parent, false)
        return ConsumptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConsumptionViewHolder, position: Int) {
        holder.binding.apply {
            tvKmPerLiterShow.text = consumptions[position].kmPerLtr.toString()
            tvTotalAmountShow.text = consumptions[position].totalAmount.toString()
            tvMillage.text = consumptions[position].millage.toString()
            tvPricePerLiter.text = consumptions[position].pricePerLtr.toString()
            tvNumberOfLiter.text = consumptions[position].numberOfLiter.toString()
            tvGasType.text = consumptions[position].gasType.toString()
            tvGasStation.text = consumptions[position].gasStation.toString()
            tvBranch.text = consumptions[position].branch.toString()
            tvDate.text = consumptions[position].date
        }
        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(consumptions[position])

        }
    }

    override fun getItemCount(): Int {
        return consumptions.size
    }
}