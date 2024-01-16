package com.kkonradsoftware.libraries.ds.rate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.kkonradsoftware.libraries.ds.BR
import com.kkonradsoftware.libraries.ds.databinding.ViewRateBinding

class RateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    val viewRateBinding: ViewRateBinding =
        ViewRateBinding.inflate(LayoutInflater.from(context), this, true)

}


@BindingAdapter("rateViewData")
fun RateView.setData(data: RateViewData?) {
    data?.let { viewRateBinding.setVariable(BR.viewData, it) }
}