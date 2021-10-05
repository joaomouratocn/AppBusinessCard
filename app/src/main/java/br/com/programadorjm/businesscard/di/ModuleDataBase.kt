package br.com.programadorjm.businesscard.di

import android.content.Context
import androidx.room.Room
import br.com.programadorjm.businesscard.data.room.CardDao
import br.com.programadorjm.businesscard.data.room.CardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDataBase {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context):CardDao {
        return Room.databaseBuilder(
            context,
            CardDatabase::class.java,
            "card_database"
        ).build().cardDao()
    }
}