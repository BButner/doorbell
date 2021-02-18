package com.bbutner.doorbell.api.controllers

import com.bbutner.doorbell.service.DoorbellAudioPlayerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import javax.sound.sampled.*
import javax.sound.sampled.AudioSystem.getMixer
import javax.sound.sampled.AudioSystem.getMixerInfo

@RestController
@RequestMapping("/doorbell")
class DoorbellController (
    private val doorbellAudioPlayerService: DoorbellAudioPlayerService
) {
    @PostMapping("")
    suspend fun ringDoorbell() {
        doorbellAudioPlayerService.ringDoorbell()
    }

    @GetMapping("")
    suspend fun test(): String {
        println("test")
        return "testing"
    }
}