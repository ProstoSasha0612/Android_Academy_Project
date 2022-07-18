package com.projectapp.moviesapp.presentation.recyclerview

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager

class FooterSpanSizeLookup(
    private val concatAdapter: ConcatAdapter?,
    private val spanCount: Int,
    private val footerViewType: Int
) : GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        return if (concatAdapter?.getItemViewType(position) == footerViewType) spanCount else 1
    }
}
