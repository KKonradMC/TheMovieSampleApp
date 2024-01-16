package com.kkonradsoftware.libraries.ds.listitem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.kkonradsoftware.libraries.ds.BR
import com.kkonradsoftware.libraries.ds.databinding.ViewListItemBinding

class ListItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    val viewListItemBinding: ViewListItemBinding =
        ViewListItemBinding.inflate(LayoutInflater.from(context), this, true)
}


@BindingAdapter("listItemData")
fun ListItem.setData(data: ListItemData?) {
    data?.let { viewListItemBinding.setVariable(BR.viewData, it) }
}
