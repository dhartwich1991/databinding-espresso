package de.freenet.espresso_databinding.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import de.freenet.espresso_databinding.R
import de.freenet.espresso_databinding.databinding.MainItemBinding

class MainRecyclerAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private val items = ArrayList<MainItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MainItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.main_item, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.item = items[position]
    }

    fun addItems(newItems: List<MainItem>) {
        val oldSize = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(oldSize, items.size)
    }
}

data class ItemViewHolder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root)