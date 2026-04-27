/*
 * SPDX-FileCopyrightText: 2016 Thomas Posch <NfConnect@online.posch.name>
 *
 * SPDX-License-Identifier: GPL-2.0-only OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL
*/
package com.neubofy.nfconnect.plugins.runcommand

import android.view.LayoutInflater
import android.view.View
import com.neubofy.nfconnect.databinding.ListItemCommandEntryBinding
import org.json.JSONException
import org.json.JSONObject
import com.neubofy.nfconnect.ui.list.EntryItem

open class CommandEntry(name: String, cmd: String, val key: String) : EntryItem(name, cmd) {

    @Throws(JSONException::class)
    constructor(o: JSONObject) : this(o.getString("name"), o.getString("command"), o.getString("key"))

    val name: String
        get() = title

    val command: String
        get() = subtitle!!

    override fun inflateView(layoutInflater: LayoutInflater): View {
        val binding = ListItemCommandEntryBinding.inflate(layoutInflater)

        binding.listItemEntryTitle.text = name

        binding.listItemEntrySummary.visibility = View.VISIBLE
        binding.listItemEntrySummary.text = command

        return binding.root
    }
}
