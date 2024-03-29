package com.dimlix.skillboxsample.users.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimlix.skillboxsample.R
import com.dimlix.skillboxsample.users.data.UserData
import com.dimlix.skillboxsample.users.ui.details.UserDetailsActivity
import com.squareup.picasso.Picasso

class DataAdapter(private val data: List<UserData>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val _title: TextView = itemView.findViewById(R.id.tvName)
        private val _pic: ImageView = itemView.findViewById(R.id.imgUser)

        @SuppressLint("SetTextI18n")
        fun bind(data: UserData) {
            _title.text = "${data.firstName} ${data.lastName}"
            Picasso.get().load(data.avatarUrl).into(_pic)

            itemView.setOnClickListener {
                UserDetailsActivity.start(itemView.context, data.id)
            }
        }

    }

}
