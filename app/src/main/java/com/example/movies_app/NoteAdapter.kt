package com.example.movies_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Callback

class NoteAdapter(val context: Context, var list:List<Result_Data>): RecyclerView.Adapter<NoteAdapter.MovieViewHolder>(){
    var listener:OnItemClickListener?=null

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image=itemView.findViewById<ImageView>(R.id.movie_image)
        var title=itemView.findViewById<TextView>(R.id.movie_title)
        init
        {
            //itemView.setOnClickListener(this)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(list.get(position))
                }
            }
        }

//        override fun onClick(v: View?){
//            val possition=adapterPosition
//            if(position!=RecyclerView.NO_POSITION)
//            listener?.onItemClick(list[position])
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item=list[position]
        holder.title.text=item.title
        val url="https://image.tmdb.org/t/p/w500"+item.poster_path
        Glide.with(context).load(url).into(holder.image)

    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemClick(note: Result_Data)
    }

    fun setOnItemClickListener(listenr: OnItemClickListener) {
        listener = listenr
    }
    fun setNotes(note: List<Result_Data>) {
        list = note
        notifyDataSetChanged()
    }
}