package cn.yumetsuki.lab4.impl

import cn.yumetsuki.lab4.AbstractFile
import cn.yumetsuki.lab4.Permission
import cn.yumetsuki.lab4.ext.no
import cn.yumetsuki.lab4.model.Directory
import cn.yumetsuki.lab4.model.File
import java.io.FileNotFoundException
import java.util.*

class FCB(
    private val files: LinkedList<AbstractFile> = LinkedList()
){

    fun open(path: String): Boolean{
        getFileFromPath(path)?.also {
            if (it is File && !it.isOpened){
                it.isOpened = true
                return true
            }
        }
        return false
    }

    fun close(path: String): Boolean{
        getFileFromPath(path)?.also {
            if (it is File && it.isOpened){
                it.isOpened = false
                return true
            }
        }
        return false
    }

    fun read(path: String): String?{
        getFileFromPath(path)?.also {
            if (it is File && it.isOpened) {
                if (it.permission == Permission.W ||
                    it.permission == Permission.WE ||
                    it.permission == Permission.E) {
                    throw FilePermissionException()
                } else {
                    return it.data
                }
            } else {
                throw NotOpenedException()
            }
        }
        throw FileNotFoundException()
    }

    fun write(path: String, data: String){
        getFileFromPath(path)?.also {
            if (it is File && it.isOpened) {
                if (it.permission == Permission.R ||
                    it.permission == Permission.RE ||
                    it.permission == Permission.E)
                {
                    throw FilePermissionException()
                } else {
                    it.data = data
                    return
                }
            } else {
                throw NotOpenedException()
            }
        }
        throw FileNotFoundException()
    }

    fun setPermission(path: String, permission: Permission): Boolean{
        getFileFromPath(path)?.also {
            if (it is File){
                it.permission = permission
                return true
            }
        }
        return false
    }

    fun createDirectory(name: String, currentDirectory: Directory?): Boolean{
        files.any { it.name == name }.no {
            files.add(Directory("/$name", currentDirectory))
            return true
        }
        return false
    }

    fun createFile(name: String, currentDirectory: Directory): Boolean{
        files.any { it.name == name }.no {
            files.add(File(path = "${currentDirectory.path}/$name"))
            return true
        }
        return false
    }

    fun del(name: String): Boolean{
        return files.removeIf { it.name == name }
    }

    fun getFileFromName(name: String): AbstractFile?{
        return files.find { it.name == name }
    }

    fun getFileFromPath(path: String): AbstractFile?{
        var file = files.find { it.path == path }
        if (file == null){
            for (fileChild in files) {
                if (fileChild is Directory){
                    file = fileChild.findFileFromPath(path.substring(fileChild.path.length + 1))
                }
            }
        }
        return file
    }

    fun getAll(): List<AbstractFile> = files

    class NotOpenedException: Exception("This file was not opened")

    class FilePermissionException: Exception("This file have not this permission")

}