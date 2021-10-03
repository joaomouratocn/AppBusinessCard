package br.com.programadorjm.businesscard.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "card_id")val cardId:Long,
    @ColumnInfo(name = "card_name")val cardName:String,
    @ColumnInfo(name = "card_phone_number")val cardPhoneNumber:String,
    @ColumnInfo(name = "card_email")val cardEmail:String,
    @ColumnInfo(name = "card_company")val cardCompany:String
)