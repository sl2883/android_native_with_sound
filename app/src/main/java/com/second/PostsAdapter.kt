package com.second

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clevertap.android.sdk.Logger
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent

//CleverTapDisplayUnitContent
class PostsAdapter(var posts:ArrayList<CleverTapDisplayUnitContent>): RecyclerView.Adapter<PostsAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {
        Logger.v("Native content received and to be rendered")
        Logger.v(posts[position].toString())
        holder.title.text = posts[position].title;
//        holder.title.text = posts[position];
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title:TextView = itemView.findViewById(R.id.post_title)
    }

}