package storageManagement.q4.impl

import storageManagement.q4.AbstractFile
import storageManagement.q4.Permission
import storageManagement.q4.ext.no
import storageManagement.q4.model.Directory
import storageManagement.q4.model.File
import java.io.FileNotFoundException
import java.util.*

//文件控制块类
class FCB(
    private val files: LinkedList<AbstractFile> = LinkedList()
){

    //如果open的是一个文件且没被打开则打开
    fun open(path: String): Boolean{
        getFileFromPath(path)?.also {
            if (it is File && !it.isOpened){
                it.isOpened = true
                return true
            }
        }
        return false
    }

    //如果close的是一个文件且已经被打开则close
    fun close(path: String): Boolean{
        getFileFromPath(path)?.also {
            if (it is File && it.isOpened){
                it.isOpened = false
                return true
            }
        }
        return false
    }

    //若read的是一个文件且文件已打开则判断文件权限，若不限于W,WE,E则read
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

    //若write的是文件且已打开且权限正确则write
    fun write(path: String, data: String){
        getFileFromPath(path)?.also {
            if (it is File && it.isOpened) {
                if (it.permission == Permission.R ||
                    it.permission == Permission.RE ||
                    it.permission == Permission.E)
                {
                    throw FilePermissionException()
                } else {
                    it.size = data.length.toDouble()
                    it.data = data
                    return
                }
            } else {
                throw NotOpenedException()
            }
        }
        throw FileNotFoundException()
    }

    //设置文件权限
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