package com.example.to_doappwithjetpackcompose.data.models

import com.example.to_doappwithjetpackcompose.ui.theme.HighPriorityColor
import com.example.to_doappwithjetpackcompose.ui.theme.LowPriorityColor
import com.example.to_doappwithjetpackcompose.ui.theme.MediumPriorityColor
import com.example.to_doappwithjetpackcompose.ui.theme.NonePriorityColor

enum class Priority(val color: androidx.compose.ui.graphics.Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}