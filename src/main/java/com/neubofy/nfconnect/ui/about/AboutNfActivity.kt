/*
 * SPDX-FileCopyrightText: 2021 Maxim Leshchenko <cnmaks90@gmail.com>
 *
 * SPDX-License-Identifier: GPL-2.0-only OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL
 */

package com.neubofy.nfconnect.ui.about

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import com.neubofy.nfconnect.base.BaseActivity
import com.neubofy.nfconnect.extensions.setupBottomPadding
import com.neubofy.nfconnect.extensions.viewBinding
import com.neubofy.nfconnect.R
import com.neubofy.nfconnect.databinding.ActivityAboutNfBinding

class AboutNfActivity : BaseActivity<ActivityAboutNfBinding>() {

    override val binding: ActivityAboutNfBinding by viewBinding(ActivityAboutNfBinding::inflate)

    override val isScrollable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarLayout.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        binding.aboutTextView.text = fromHtml(resources.getString(R.string.about_nf_about))
        binding.reportBugsOrWishesTextView.text = fromHtml(resources.getString(R.string.about_nf_report_bugs_or_wishes))
        binding.joinNfTextView.text = fromHtml(resources.getString(R.string.about_nf_join_nf))
        binding.supportNfTextView.text = fromHtml(resources.getString(R.string.about_nf_support_nf))

        binding.aboutTextView.movementMethod = LinkMovementMethod.getInstance()
        binding.reportBugsOrWishesTextView.movementMethod = LinkMovementMethod.getInstance()
        binding.joinNfTextView.movementMethod = LinkMovementMethod.getInstance()
        binding.supportNfTextView.movementMethod = LinkMovementMethod.getInstance()

        binding.scrollView.setupBottomPadding()
    }

    private fun fromHtml(html: String): Spanned {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION") Html.fromHtml(html)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
