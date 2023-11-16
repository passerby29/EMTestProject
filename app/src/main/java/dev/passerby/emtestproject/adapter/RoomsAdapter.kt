package dev.passerby.emtestproject.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import com.google.android.flexbox.FlexboxLayout
import dev.passerby.domain.models.RoomModel
import dev.passerby.emtestproject.R
import dev.passerby.emtestproject.callbacks.RoomDiffCallback
import dev.passerby.emtestproject.databinding.ItemRoomBinding
import dev.passerby.emtestproject.viewholders.RoomViewHolder
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class RoomsAdapter(private val context: Context) :
    ListAdapter<RoomModel, RoomViewHolder>(RoomDiffCallback()) {

    var onRoomItemCLickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoomViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        with(binding) {
            val carouselList = item.imageUrls.map { CarouselItem(it) }
            val price =
                String.format(context.getString(R.string.price_placeholder), item.price)
            roomsImageCarousel.setData(carouselList)
            roomsNameTextView.text = item.name
            roomsPeculiaritiesContainer.removeAllViews()
            item.peculiarities.forEach { peculiarity ->
                val textView = TextView(context)
                val params = FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
                )
                val typeface = ResourcesCompat.getFont(context, R.font.sf_medium)
                textView.apply {
                    text = peculiarity
                    textSize = 16f
                    setTypeface(typeface)
                    setTextColor(ContextCompat.getColor(context, R.color.additional_text_color))
                    setBackgroundResource(R.drawable.item_peculiarity_background)
                    setPadding(10, 5, 10, 5)
                    params.setMargins(8, 4, 0, 4)
                    layoutParams = params
                }
                roomsPeculiaritiesContainer.addView(textView)
            }
            roomsPriceTextView.text = price
            roomsPriceAddTextView.text = item.pricePer
            roomsSelectButton.setOnClickListener {
                onRoomItemCLickListener?.invoke()
            }
        }
    }
}