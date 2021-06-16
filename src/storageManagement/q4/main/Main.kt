package storageManagement.q4.main

import storageManagement.q4.view.Application

val application: Application = Application()


fun main(args: Array<String>) {
    application.start(SystemView())
}