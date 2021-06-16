package storageManagement.q4.main

import cn.yumetsuki.lab4.main.SystemView
import cn.yumetsuki.lab4.view.Application

val application: Application = Application()

fun main(args: Array<String>) {
    application.start(SystemView())
}