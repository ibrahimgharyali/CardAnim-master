package com.ibrahim.demo.cardanim.model

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


public class Person ( val gender: String, val name : NameModel, val location : LocationModel,
                      val dob : DoB, val picture : PictureModel)  : BaseObservable(){
    companion object {
        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun loadImage(view : ImageView, imageUrl :String ) {
            Picasso.get().load(imageUrl).into(view)
            /*Picasso.with(view.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(view)*/
        }
    }

    class DoB (val date : String, val age : Int )

    class PictureModel (var large: String, var medium: String, var thumbnail: String)

}



