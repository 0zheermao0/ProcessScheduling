package storageManagement.q4.main

import storageManagement.q4.Permission
import storageManagement.q4.model.Result
import storageManagement.q4.ext.no
import storageManagement.q4.ext.otherWise
import storageManagement.q4.ext.yes
import storageManagement.q4.impl.FileSystem
import storageManagement.q4.impl.fileSystem
import storageManagement.q4.view.CommandPage
import storageManagement.q4.view.Context
import storageManagement.q4.view.ContextFlag
import storageManagement.q4.view.widget.CommandWidget

//用户操作指令
class UserView(
    override val data: String
): CommandPage<String>() {

    private val system: FileSystem = fileSystem

    override fun create(): CommandWidget<String, *> {
        return singleLineInput("[$data: ${system.currentDirectory(data).path}]>") {
            val command = it?:""
            return@singleLineInput command.isEmpty().no {
                doOperation(command, this).data
            }.otherWise {
                Unit
            }
        }
    }

    private fun doOperation(command: String, context: Context<String, ContextFlag>): Result<*> {

        val operation = command.split(" ")
        val func = operation[0]
        return when(func){
            "mkdir" -> mkDir(operation)
            "mkfile" -> mkFile(operation)
            "exit" -> exit(context)
            "cd" -> cd(operation)
            "ls" -> ls(operation)
            "del" -> del(operation)
            "open" -> open(operation)
            "permission" -> permission(operation)
            else-> Result(newLineMessage("operation not exist"))
        }
    }

    private fun mkDir(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            val dirName = operation[1]
            system.createDirectory(data, dirName).no {
                newLineMessage("[system]: This directory has a same name directory")
            }


        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun mkFile(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            system.createFile(data, operation[1]).no {
                newLineMessage("[system]: This file has a same name file")
            }
        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun ls(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            if (operation.size > 1){
                if (operation[1] == "-d"){
                    system.listDirectoryDetail(data).apply {
                        forEachIndexed { index, s ->
                            if (index == this.size - 1){
                                println(s)
                            } else {
                                print("$s\t")
                            }
                        }
                    }
                } else {
                    newLineMessage("[system]: The arguments are not correct")
                }
                return Result(Unit)
            }
            system.listDirectory(data).apply {
                forEachIndexed { index, s ->
                    if (index == this.size - 1){
                        println(s)
                    } else {
                        print("$s\t")
                    }
                }
            }
        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun cd(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            val path = operation[1]
            val r = path.split("/")
            val relativePath = r[0]
            fun jump(path: String){
                system.nextDirectory(data, path).yes {
                    rebuild("[$data: ${system.currentDirectory(data).path}]>")
                }.otherWise {
                    newLineMessage("[system]: The directory is not exist")
                }
            }
            when(relativePath){
                ".." -> {
                    if (r.size == 1) {
                        system.previousDirectory(data).yes {
                            rebuild("[$data: ${system.currentDirectory(data).path}]>")
                        }
                        return Result(Unit)
                    }
                    val name = system.currentDirectory(data).name
                    system.previousDirectory(data).yes {
                        jump(path.substring(3))
                    }.otherWise {
                        system.nextDirectory(data, name)
                    }
                }
                "." -> {
                    jump(path.substring(2))
                }
                else -> {
                    jump(path)
                }
            }
        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun del(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            system.del(data, operation[1]).no {
                newLineMessage("[system]: The file or directory is not exist")
            }
        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun open(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            system.open(data, operation[1]).yes {
                push(FileView(Pair(data, operation[1])))
            }.otherWise {
                newLineMessage("[system]: this file was opened or is not exist")
            }
        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun permission(operation: List<String>): Result<Unit> {
        try {
            if (operation.size != 3) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            val permission = when (operation[2]) {
                "-r" -> Permission.R
                "-re" -> Permission.RE
                "-rw" -> Permission.WR
                "-w" -> Permission.W
                "-we" -> Permission.WE
                "-wre" -> Permission.WRE
                "-e" -> Permission.E
                else -> null
            }
            permission?.also {
                system.setPermission(data, operation[1], it).no {
                    newLineMessage("[system]: The file is not exist")
                }
            }?:newLineMessage("[system]: The arguments are not correct")

        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The permission is not correct")
        }
        return Result(Unit)
    }

    private fun exit(context: Context<String, ContextFlag>): Result<ContextFlag> {
        return Result(context.pop())
    }


}