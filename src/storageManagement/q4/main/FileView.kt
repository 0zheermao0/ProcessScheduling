package storageManagement.q4.main

import storageManagement.q4.model.Result
import storageManagement.q4.ext.no
import storageManagement.q4.ext.otherWise
import storageManagement.q4.impl.FileSystem
import storageManagement.q4.impl.fileSystem
import storageManagement.q4.impl.FCB
import storageManagement.q4.view.CommandPage
import storageManagement.q4.view.Context
import storageManagement.q4.view.ContextFlag
import storageManagement.q4.view.PopFlag
import storageManagement.q4.view.widget.CommandWidget
import java.io.FileNotFoundException

//文件操作指令
class FileView(
    override val data: Pair<String, String>
): CommandPage<Pair<String, String>>(){

    private val system: FileSystem = fileSystem

    override fun create(): CommandWidget<Pair<String, String>, *> {
        return singleLineInput("[${data.first}: ${system.currentDirectory(data.first).path}/${data.second}]>") {
            val command = it?:""
            return@singleLineInput command.isEmpty().no {
                doOperation(command, this).data
            }.otherWise {
                Unit
            }
        }
    }

    private fun doOperation(command: String, context: Context<Pair<String, String>, ContextFlag>): Result<*> {
        val operation = command.split(" ")
        val func = operation[0]
        return when(func){
            "read" -> read(operation)
            "write" -> write(operation)
            "close" -> close(operation)
            else-> Result(newLineMessage("operation not exist"))
        }
    }

    private fun read(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 1) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            try {
                system.read(data.first, data.second)?.apply {
                    println(this)
                }?: println("NULL")
            } catch (e: Exception) {
                when(e) {
                    is FileNotFoundException -> { newLineMessage("[system]: The file is not exist") }
                    else -> { newLineMessage("[system]: ${e.message}") }
                }
            }
        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun write(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            try {
                system.write(data.first, data.second, operation[1])
            } catch (e: Exception) {
                when(e) {
                    is FileNotFoundException -> { newLineMessage("[system]: The file is not exist") }
                    is FCB.FilePermissionException -> { newLineMessage("[system]: ${e.message}") }
                    else -> { newLineMessage("[system]: The arguments are not correct") }
                }
            }
        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun close(operation: List<String>): Result<PopFlag> {
        system.close(data.first, data.second)
        return Result(PopFlag)
    }

}