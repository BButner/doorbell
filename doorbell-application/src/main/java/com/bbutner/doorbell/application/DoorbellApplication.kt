package com.bbutner.doorbell.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["com.bbutner"])
open class DoorbellApplication

fun main(args: Array<String>) {
    runApplication<DoorbellApplication>(*args)
}