package storageManagement.q4.view.widget

import storageManagement.q4.view.Context
import storageManagement.q4.view.ContextFlag
import storageManagement.q4.view.JumpFlag
import storageManagement.q4.view.PopFlag

//指令运行
abstract class CommandWidget<T, R>(
        private val context: Context<T, ContextFlag>,
        var title: String,
        private val block: Context<T, ContextFlag>.(text: String?) -> R
){

    protected abstract fun showTitle(title: String)

    fun run(){
        showTitle(title)
        when(block(context, readLine())){
            is PopFlag -> {}
            is JumpFlag -> {}
            else -> run()
        }
    }

}

class SingleLineInput<T, R>(
        context: Context<T, ContextFlag>,
        title: String,
        block: Context<T, ContextFlag>.(text: String?) -> R
): CommandWidget<T, R>(context, title, block){

    override fun showTitle(title: String) {
        print(title)
    }

}

class NewLineInput<T, R>(
        context: Context<T, ContextFlag>,
        title: String,
        block: Context<T, ContextFlag>.(text: String?) -> R
): CommandWidget<T, R>(context, title, block){

    override fun showTitle(title: String) {
        println(title)
    }

}
