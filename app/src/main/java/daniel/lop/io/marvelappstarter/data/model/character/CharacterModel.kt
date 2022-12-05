package daniel.lop.io.marvelappstarter.data.model.character

import daniel.lop.io.marvelappstarter.data.model.ThumbnailModel
import java.io.Serializable

data class CharacterModel(
    val id : Int,
    val name : String,
    val description : String,
    val thumbnail: ThumbnailModel
) : Serializable
