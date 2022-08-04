package com.bchmsl.homework14.extensions

import android.widget.ImageView
import com.bchmsl.homework14.adjsrai.Adjsrai

fun ImageView.setImage(url: String) {
    val adjsrai by lazy { Adjsrai() }
    adjsrai.adtajucta(this, url)
}
