package br.com.programadorjm.businesscard.data.room

import androidx.room.*

@Dao
interface CardDao {
    @Query("SELECT * FROM cards")
    suspend fun getAllCards():List<CardEntity>

    @Insert
    suspend fun insertCard(cardEntity: CardEntity)

    @Update
    suspend fun updateCard(cardEntity: CardEntity)

    @Delete
    suspend fun deleteCard(cardEntity: CardEntity)
}