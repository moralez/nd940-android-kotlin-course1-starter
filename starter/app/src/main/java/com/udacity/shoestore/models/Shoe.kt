package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
    val images: List<String> = mutableListOf(),
    val dateAdded: Date
) : Parcelable {
    override fun hashCode(): Int {
        return name.hashCode() + dateAdded.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Shoe

        if (name != other.name) return false
        if (size != other.size) return false
        if (company != other.company) return false
        if (description != other.description) return false
        if (images != other.images) return false
        if (dateAdded != other.dateAdded) return false

        return true
    }
}
