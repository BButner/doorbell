package com.bbutner.doorbell.service

import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import java.io.File
import javax.sound.sampled.*

@Service
class DoorbellAudioPlayerServiceImpl : DoorbellAudioPlayerService, LineListener {
    var playCompleted: Boolean = false

    override suspend fun ringDoorbell() {
//        val audioFile: File = DoorbellAudioPlayerServiceImpl::class.java.getResourceAsStream("/ringtone.wav")
        val audioFile: File = File.createTempFile("ringtone", ".wav")
        FileCopyUtils.copy(DoorbellAudioPlayerServiceImpl::class.java.getResourceAsStream("/ringtone.wav"), audioFile.outputStream())

        try {
            val audioStream: AudioInputStream = AudioSystem.getAudioInputStream(audioFile)
            val format: AudioFormat = audioStream.format
            val dataLineInfo: DataLine.Info = DataLine.Info(Clip::class.java, format)
            val audioClip: Clip = AudioSystem.getLine(dataLineInfo) as Clip

            audioClip.addLineListener(this)
            audioClip.open(audioStream)
            audioClip.start()

            while (!playCompleted) {
                try {
                    Thread.sleep(100)
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                }
            }

            audioClip.close()
            playCompleted = false
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun update(event: LineEvent) {
        val type: LineEvent.Type = event.type

        if (type == LineEvent.Type.START) {
            println("Playback Started")
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true
            println("Playback Ended")
        }
    }
}