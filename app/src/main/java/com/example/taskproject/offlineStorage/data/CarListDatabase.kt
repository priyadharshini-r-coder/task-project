package com.example.taskproject.offlineStorage.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarList::class],version = 1)
abstract class CarListDatabase:RoomDatabase(){
    abstract fun carsDao():CarsDao
}