package daniel.lop.io.marvelappstarter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import daniel.lop.io.marvelappstarter.data.model.character.CharacterModel

// Room - 4º passo: classe que cria o BD

@Database(entities = [CharacterModel::class], version = 1, exportSchema = false)
@TypeConverters(MarvelConverters::class)
abstract class MarvelDataBase : RoomDatabase() {

    abstract fun marvelDao() : MarvelDao

}