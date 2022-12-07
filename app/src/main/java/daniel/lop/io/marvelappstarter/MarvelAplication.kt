package daniel.lop.io.marvelappstarter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
 *  Dagger Hilt
 *  3º passo => criar classe que estende de Application e anotar com @HiltAndroidApp
 *  fornece código a nível de aplicação, fornece todas as dependencias para todas as
 *  entidades
 */
@HiltAndroidApp
class MarvelAplication : Application() {
}