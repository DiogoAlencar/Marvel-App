package daniel.lop.io.marvelappstarter.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import daniel.lop.io.marvelappstarter.data.model.ThumbnailModel

class MarvelConverters {

    /*
     * Classe criada para converter objeto Thumbnail em string do tipo Json para salvar no DB
     * e de string do tipo Json para objeto Thumbnail
     */

    @TypeConverter
    fun fromThumbnail(thumbnailModel: ThumbnailModel) : String = Gson().toJson(thumbnailModel)

    @TypeConverter
    fun toThumbnail(thumbnailModel: String) : ThumbnailModel =
        Gson().fromJson(thumbnailModel, ThumbnailModel::class.java)
}