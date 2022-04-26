package com.example.taskproject.offlineStorage.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarList (
    @PrimaryKey val make_and_model: String,
    val color: String,
    val transmission: String,
    val drive_type: String,
    val fuel_type: String,
    val car_type: String
)