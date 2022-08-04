package com.bchmsl.homework14.adjsrai

import android.widget.ImageView
import com.bchmsl.homework14.R
import com.bumptech.glide.Glide

class Adjsrai {

    fun adtajucta(imageView: ImageView, ADTAJSTUMAG: String) {
        Glide.with(imageView.context).load(ADTAJSTUMAG).placeholder(R.drawable.gif_loading).into(imageView)
    }
}