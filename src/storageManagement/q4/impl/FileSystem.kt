package cn.yumetsuki.lab4.impl

import cn.yumetsuki.lab4.Permission
import cn.yumetsuki.lab4.SystemInterface
import cn.yumetsuki.lab4.model.Directory
import cn.yumetsuki.lab4.model.MFD
import cn.yumetsuki.lab4.model.UFD
import java.io.FileNotFoundException

class FileSystem: SystemInterface {

    private val root: MFD = MFD()

    private val currentUserMap: HashMap<String, Directory> = hashMapOf()

    override fun login(username: String, password: String): String? {
        return root.userDirectoryList.find { it.name == username && it.checkPassword(password) }?.run {
            currentUserMap[username] = this
            return@run username
        }
    }

    override fun register(username: String, password: String): String {
        root.userDirectoryList.add(UFD("/$username", root, password))
        return username
    }

    override fun checkUser(username: String): Boolean {
        return root.userDirectoryList.find { it.name == username } == null
    }

    override fun previousDirectory(token: String): Boolean {
        currentUserMap[token]?.apply {
            if (this.previous is MFD) return false
            currentUserMap[token] = this.previous!!
        }
        return true
    }

    override fun currentDirectory(token: String): Directory {
        return currentUserMap[token]?:throw Exception("can not find this user")
    }

    override fun nextDirectory(token: String, path: String): Boolean {

        currentUserMap[token]?.apply {
            this.findFileFromPath(path)?.also {
                return if (it is Directory){
                    currentUserMap[token] = it
                    true
                } else {
                    false
                }
            }
        }

        return false
    }

    override fun listDirectory(token: String): List<String> {
        currentUserMap[token]?.apply {
            return this.getAllFile().map { it.name }
        }
        return listOf()
    }

    override fun listDirectoryDetail(token: String): List<String> {
        currentUserMap[token]?.apply {
            return this.getAllFile().map { "${permission.code} ${it.location} ${it.size} ${it.name}" }
        }
        return listOf()
    }

    override fun open(token: String, name: String): Boolean {
        currentUserMap[token]?.apply {
            return this.open(name)
        }
        return false
    }

    override fun close(token: String, name: String): Boolean {
        currentUserMap[token]?.apply {
            return this.close(name)
        }
        return false
    }

    override fun read(token: String, name: String): String? {
        currentUserMap[token]?.apply {
            return this.read(name)
        }
        return null
    }

    override fun write(token: String, name: String, data: String) {
        currentUserMap[token]?.apply {
            write(name, data)
        }?:throw FileNotFoundException()

    }

    override fun del(token: String, name: String): Boolean {
        currentUserMap[token]?.apply {
            return this.del(name)
        }
        return true
    }

    override fun createDirectory(token: String, name: String): Boolean {
        currentUserMap[token]?.apply {
            return this.create(name, Directory.CreateMode.Directory)
        }
        return false
    }

    override fun createFile(token: String, name: String): Boolean {
        currentUserMap[token]?.apply {
            return this.create(name, Directory.CreateMode.File)
        }
        return false
    }

    override fun setPermission(token: String, name: String, permission: Permission): Boolean {
        currentUserMap[token]?.apply {
            return this.setPermission(name, permission)
        }
        return false
    }

    companion object {
        private const val MAX_USER_COUNT = 10

        private const val MAX_OPEN_FILE_COUNT = 5
    }

}

val fileSystem = FileSystem()