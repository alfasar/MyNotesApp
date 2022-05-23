package com.example.mynotesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.appdata.Item
import com.example.mynotesapp.databinding.FragmentItemAdapterBinding
import java.time.LocalDate
import java.time.Month
import java.time.Period
import java.time.temporal.ChronoUnit
import java.util.*

class ItemAdapterFragment :
    RecyclerView.Adapter<ItemAdapterFragment.ItemHolder>() {

    private var itemList = mutableListOf<Item>()

    class ItemHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = FragmentItemAdapterBinding.bind(item)
        fun bind(item: Item) = with(binding) {
            imageView.setImageResource(R.drawable.ic_baseline_person_24)
            itemName.text = item.name
            val splitter = item.birthday.split(".", ignoreCase = false, limit = 0)
            val turningAge = (getAge(
                splitter[2].toInt(),
                splitter[1].toInt(),
                splitter[0].toInt()
            ) + 1).toString()
            val monthName = Month.of(splitter[1].toInt())
            itemBirthday.text = "${splitter[0]} of $monthName turning $turningAge"
            daysLeft.text = daysLeft(splitter[1].toInt(), splitter[0].toInt())
        }

        private fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
            return Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
            ).years
        }

        private fun daysLeft(month: Int, dayOfMonth: Int): String {
            val result: String
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val nextYear = Calendar.getInstance().get(Calendar.YEAR)+1
            val currentBirthDate = LocalDate.of(currentYear, month, dayOfMonth)
            val nextBirthDate = LocalDate.of(nextYear, month, dayOfMonth)
            result = if (currentBirthDate.isAfter(LocalDate.now())) {
                ChronoUnit.DAYS.between(LocalDate.now(), currentBirthDate).toString()
            } else if (currentBirthDate.isBefore(LocalDate.now())){
                ChronoUnit.DAYS.between(LocalDate.now(), nextBirthDate).toString()
            } else {
                "Today!"
            }
            return result
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun addItem(item: MutableList<Item>) {
        itemList.clear()
        this.itemList = item
        notifyDataSetChanged()
    }
}