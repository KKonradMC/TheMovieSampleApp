package com.kkonradsoftware.feature.presentation.list.adapter

import android.content.Context
import android.widget.ArrayAdapter
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class SearchAdapter @Inject constructor(@ActivityContext context: Context) :
    ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, ArrayList())