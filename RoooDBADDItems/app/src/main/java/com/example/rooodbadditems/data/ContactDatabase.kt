package com.example.rooodbadditems.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase() : RoomDatabase() {
    abstract val contactDao : ContactDao

}