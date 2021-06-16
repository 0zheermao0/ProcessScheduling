package cn.yumetsuki.lab4.main

import cn.yumetsuki.lab4.model.Result
import cn.yumetsuki.lab4.ext.no
import cn.yumetsuki.lab4.ext.otherWise
import cn.yumetsuki.lab4.ext.yes
import cn.yumetsuki.lab4.impl.FileSystem
import cn.yumetsuki.lab4.impl.fileSystem
import cn.yumetsuki.lab4.view.CommandPage
import cn.yumetsuki.lab4.view.Context
import cn.yumetsuki.lab4.view.ContextFlag
import cn.yumetsuki.lab4.view.widget.CommandWidget

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