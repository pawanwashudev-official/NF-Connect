/*
 * SPDX-FileCopyrightText: 2014 Albert Vaca Cintora <albertvaka@gmail.com>
 *
 * SPDX-License-Identifier: GPL-2.0-only OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL
*/
package com.neubofy.nfconnect.backends.loopback

import android.content.Context
import androidx.annotation.WorkerThread
import com.neubofy.nfconnect.backends.BaseLink
import com.neubofy.nfconnect.backends.BaseLinkProvider
import com.neubofy.nfconnect.Device
import com.neubofy.nfconnect.DeviceInfo
import com.neubofy.nfconnect.helpers.DeviceHelper.getDeviceInfo
import com.neubofy.nfconnect.NetworkPacket

class LoopbackLink : BaseLink {
    constructor(context: Context, linkProvider: BaseLinkProvider) : super(context, linkProvider)

    override fun getName(): String = "LoopbackLink"
    override fun getDeviceInfo(): DeviceInfo = getDeviceInfo(context)

    @WorkerThread
    override fun sendPacket(packet: NetworkPacket, callback: Device.SendPacketStatusCallback, sendPayloadFromSameThread: Boolean): Boolean {
        packetReceived(packet)
        if (packet.hasPayload()) {
            callback.onPayloadProgressChanged(0)
            packet.payload = packet.payload // this triggers logic in the setter
            callback.onPayloadProgressChanged(100)
        }
        callback.onSuccess()
        return true
    }
}
