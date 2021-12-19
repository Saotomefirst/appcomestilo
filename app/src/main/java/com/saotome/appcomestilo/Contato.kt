package com.saotome.appcomestilo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contato (
    var nome: String,
    var telefone: String,
    var foto: String
        ) : Parcelable{


}