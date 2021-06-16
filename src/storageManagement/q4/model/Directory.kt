package cn.yumetsuki.lab4.model

import cn.yumetsuki.lab4.AbstractFile
import cn.yumetsuki.lab4.Permission
import cn.yumetsuki.lab4.impl.FCB


open class Directory(
    path: String,
    val previous: Directory?
): AbstractFile("${previous?.path?:""}$path") {

    private val fcb = FCB()

    fun open(path: String): Boolean{
        return fcb.open("${this.path}/$path")
    }

    fun close(path: String): Boolean{
        return fcb.close("${this.path}/$path")
    }

    fun read(path: String): String?{
        return fcb.read("${this.path}/$path")
    }

    fun write(path: String, data: String){
        fcb.write("${this.path}/$path", data)
    }

    fun del(path: String): Boolean{
        return fcb.del("${this.path}/$path")
    }

    fun setPermission(path: String, permission: Permission): Boolean {
        return fcb.setPermission("${this.path}/$path", permission)
    }

    fun create(name: String, mode: CreateMode): Boolean{
        return when(mode){
            CreateMode.Directory -> fcb.createDirectory(name, this)
            CreateMode.File -> fcb.createFile(name, this)
        }
    }

    fun findFileFromName(name: String) = fcb.getFileFromName(name)

    fun findFileFromPath(path: String) = fcb.getFileFromPath("${this.path}/$path")

    fun getAllFile() = fcb.getAll()

    enum class CreateMode{
        File, Directory
    }

}