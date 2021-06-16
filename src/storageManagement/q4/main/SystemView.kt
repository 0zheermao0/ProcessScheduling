package storageManagement.q4.main

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

//系统指令
class SystemView: CommandPage<Unit>() {

    private val system: FileSystem = fileSystem

    override fun create(): CommandWidget<Unit, *> {
        return singleLineInput("[system]>") {
            val command = it?:""
            return@singleLineInput command.isEmpty().no {
                doOperation(command, this).data
            }.otherWise {
                Unit
            }

        }
    }

    private fun doOperation(command: String, context: Context<Unit, ContextFlag>): Result<*> {

        val operation = command.split(" ")
        val func = operation[0]
        return when(func){
            "register" -> register(operation)
            "login" -> login(operation)
            "exit" -> exit(context)
            else-> Result(newLineMessage("operation not exist"))
        }
    }

    private fun register(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            val username = operation[1]
            system.checkUser(username).yes {
                singleLineTempInput("password: ") {
                    val password = it?:""
                    system.register(username, password).also { user ->
                        system.login(user, password)!!.apply {
                            push(UserView(this))
                        }
                    }
                }
            }.otherWise {
                newLineMessage("[system]: The user has been already registered")
            }

        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun login(operation: List<String>): Result<Unit> {
        try {
            if (operation.size > 2) {
                newLineMessage("[system]: The arguments are not correct")
                return Result(Unit)
            }
            val username = operation[1]
            system.checkUser(username).no {
                singleLineTempInput("password: ") {
                    val password = it?:""

                    system.login(username, password)?.apply {
                        push(UserView(this))
                    }?:newLineMessage("[system]: password error")
                }
            }.otherWise {
                newLineMessage("[system]: user not exists")
            }
        } catch (e: IndexOutOfBoundsException){
            newLineMessage("[system]: The arguments are not correct")
        }
        return Result(Unit)
    }

    private fun exit(context: Context<Unit, ContextFlag>): Result<ContextFlag> {
        return Result(context.pop())
    }

}