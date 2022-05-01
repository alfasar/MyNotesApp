package com.example.mynotesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.appdata.Item
import com.example.mynotesapp.databinding.FragmentItemAdapterBinding

class ItemAdapterFragment :
    RecyclerView.Adapter<ItemAdapterFragment.ItemHolder>() {

    private var itemList = emptyList<Item>()

    class ItemHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = FragmentItemAdapterBinding.bind(item)
        fun bind(item: Item) = with(binding){
            imageView.setImageResource(R.drawable.ic_baseline_person_24)
            itemName.text = item.name
            itemBirthday.text = item.birthday
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: List<Item>) {
        this.itemList = item
        notifyDataSetChanged()
    }
}