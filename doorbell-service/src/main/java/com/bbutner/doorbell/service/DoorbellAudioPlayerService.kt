package com.bbutner.doorbell.service

import javax.sound.sampled.LineEvent
import javax.sound.sampled.LineListener

interface DoorbellAudioPlayerService : LineListener {
    suspend fun ringDoorbell()
    override fun update(event: LineEvent)
}