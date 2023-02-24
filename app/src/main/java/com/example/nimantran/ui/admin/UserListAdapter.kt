package com.example.nimantran.ui.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nimantran.R


class UserListAdapter(var user:List<UserData>): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        var name = view.user_name // user_name is the id of the textview in user_list_item.xml
//        var email = view.user_email
//        var phone = view.user_phone
//        var gender = view.user_gender
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.name.text = user[position].name
//        holder.email.text = user[position].email
//        holder.phone.text = user[position].phone
//        holder.gender.text = user[position].gender
//
//        //code to set the data to the views


    }

    override fun getItemCount(): Int {
        return user.size
    }
}
