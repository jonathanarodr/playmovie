package br.com.jonathanarodr.playmovie.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("backdrop_path")
    var backdrop: String?,
    @SerializedName("vote_average")
    val average: Double,
    @SerializedName("release_date")
    var releaseDate: Date
) : Parcelable {

    companion object {

        const val IMAGE_PATH = "http://image.tmdb.org/t/p"
        const val IMAGE_SIZE_DEFAULT = "/w185"
        const val IMAGE_SIZE_HIGHT = "/w780"

        @JvmField
        val CREATOR = object : Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie {
                return Movie(source)
            }

            override fun newArray(size: Int): Array<Movie?> {
                return arrayOfNulls(size)
            }
        }
    }

    private constructor (parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readSerializable() as Date
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(title)
        dest.writeString(overview)
        dest.writeString(poster)
        dest.writeString(backdrop)
        dest.writeDouble(average)
        dest.writeSerializable(releaseDate)
    }

    fun getPosterDefault() = IMAGE_PATH + IMAGE_SIZE_DEFAULT + poster
    fun getPosterHight() = IMAGE_PATH + IMAGE_SIZE_HIGHT + poster
}