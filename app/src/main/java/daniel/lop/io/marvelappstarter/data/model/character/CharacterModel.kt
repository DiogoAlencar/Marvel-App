package daniel.lop.io.marvelappstarter.data.model.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import daniel.lop.io.marvelappstarter.data.model.ThumbnailModel
import java.io.Serializable

// Room - 2º passo: configura a classe model como uma entidade do BD
@Entity(tableName = "characterModel")
data class CharacterModel(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val description : String,

    // Room trabalha apenas com tipos primitivos - devemos criar uma classe
    // para converter esse objeto em tipo primitivo
    val thumbnail: ThumbnailModel
) : Serializable
