package com.example.to_doappwithjetpackcompose.data.models

import androidx.room.Database

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase {
    abstract fun toDoDao(): ToDoDao
}