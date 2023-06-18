/*
package com.example.runner.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.runner.R

class VersionAdapterr (val versionList: List<Versions>) :

    RecyclerView.Adapter<VersionAdapterr.versionsVH>(){
    class versionsVH(ItemView:View ) : RecyclerView.ViewHolder(ItemView) {

        var image: ImageView = itemView.findViewById(R.id.image)

        var nameTxt: TextView = itemView.findViewById(R.id.name)
        var caloriesTxt: TextView = itemView.findViewById(R.id.calories)
        var followersTxt: TextView = itemView.findViewById(R.id.followers)
        var descriptionTxt: TextView = itemView.findViewById(R.id.description)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        var expandable_layout: RelativeLayout = itemView.findViewById(R.id.expandable_layout)



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): versionsVH {
        val view:View  = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home , parent, false)
        return versionsVH(view)
    }




    override fun onBindViewHolder(holder: versionsVH, position: Int) {
        val versions = versionList[position]



        holder.image.setImageResource(versions.image)

        holder.caloriesTxt.text = versions.calories
        holder.followersTxt.text = versions.followers
        holder.descriptionTxt.text= versions.description
        holder.nameTxt.text = versions.name

        val isExpandable: Boolean = versionList[position].isExpandable
        holder.expandable_layout.visibility = if (isExpandable) View.VISIBLE else View.GONE
        holder.linearLayout.setOnClickListener {
            val versions = versionList[position]
            versions.isExpandable = !versions.isExpandable
            notifyItemChanged(position)

        }
    }

    override fun getItemCount(): Int {
        return versionList.size
    }
}
*/

