package cn.yumetsuki.lab4

import cn.yumetsuki.lab4.model.Directory

interface SystemInterface {

    fun login(username: String, password: String): String?

    fun register(username: String, password: String): String?

    fun checkUser(username: String): Boolean

    fun previousDirectory(token: String): Boolean

    fun currentDirectory(token: String): Directory

    fun nextDirectory(token: String, path: String): Boolean

    fun listDirectory(token: String): List<String>

    fun listDirectoryDetail(token: String): List<String>

    fun createDirectory(token: String, name: String): Boolean

    fun createFile(token: String, name: String): Boolean

    fun open(token: String, name: String): Boolean

    fun close(token: String, name: String): Boolean

    fun del(token: String, name: String): Boolean

    fun read(token: String, name: String): String?

    fun write(token: String, name: String, data: String)

    fun setPermission(token: String, name: String, permission: Permission): Boolean

}