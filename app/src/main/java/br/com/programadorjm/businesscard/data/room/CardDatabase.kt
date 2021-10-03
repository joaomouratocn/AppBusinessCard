package br.com.programadorjm.businesscard.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardEntity::class], version = 1)
abstract class CardDatabase:RoomDatabase() {
    abstract fun cardDao():CardDao

    companion object{
        private var INSTANCE:CardDatabase? = null
        fun getDataBase(context: Context):CardDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "card_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}