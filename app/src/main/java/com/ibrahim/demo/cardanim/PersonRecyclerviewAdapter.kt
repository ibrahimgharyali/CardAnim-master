package com.ibrahim.demo.cardanim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.demo.cardanim.model.Person
import com.ibrahim.demo.cardanim.databinding.RvItemBinding

class PersonRecyclerviewAdapter (var items: ArrayList<Person> )
    : RecyclerView.Adapter<PersonRecyclerviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items.get(position))
    fun replaceData(it: List<Person>) {
        items = it as ArrayList<Person>
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(repo: Person) {
            /*with(binding){
                rvItemName.text = repo.gender
            }*/
            binding.personViewModel = repo
            /*if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }*/

            binding.executePendingBindings()
        }
    }
}