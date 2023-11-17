package dev.passerby.emtestproject.callbacks

import androidx.recyclerview.widget.DiffUtil
import dev.passerby.domain.models.RoomModel

class RoomDiffCallback: DiffUtil.ItemCallback<RoomModel>() {
    override fun areItemsTheSame(oldItem: RoomModel, newItem: RoomModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RoomModel, newItem: RoomModel): Boolean {
        return oldItem == newItem
    }
}