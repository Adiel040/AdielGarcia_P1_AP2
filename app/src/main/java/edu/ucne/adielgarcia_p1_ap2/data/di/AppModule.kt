package edu.ucne.adielgarcia_p1_ap2.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.adielgarcia_p1_ap2.data.local.database.SistemaDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providesEntityDatabase(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            SistemaDb::class.java,
            "Sistema.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesSistemaDao(sistemaDb: SistemaDb) = sistemaDb.sistemaDao()
}