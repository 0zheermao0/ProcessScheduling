package storageManagement.q4.view

import storageManagement.q4.main.application
import storageManagement.q4.view.widget.CommandWidget
import storageManagement.q4.view.widget.NewLineInput
import storageManagement.q4.view.widget.SingleLineInput


abstract class CommandPage<T>: AbstractContext<T>(null) {

    val applicationContext: Application = application

    lateinit var widget: CommandWidget<T, *>

    abstract fun create(): CommandWidget<T, *>

    override fun pop(): PopFlag {
        return applicationContext.pop()
    }

    override fun <T> push(context: Context<T, ContextFlag>) {
        applicationContext.push(context)
    }

    override fun run() = Unit

    protected fun <R> singleLineInput(
        title: String,
        block: Context<T, ContextFlag>.(text: String?) -> R
    ): SingleLineInput<T, R> {
        return SingleLineInput(this, title, block)
    }

    protected fun <R> newLineInput(
        title: String,
        block: Context<T, ContextFlag>.(text: String?) -> R
    ): NewLineInput<T, R> {
        return NewLineInput(this, title, block)
    }

    protected fun singleLineMessage(text: String){
        print(text)
    }

    protected fun newLineMessage(text: String){
        println(text)
    }

    protected inline fun singleLineTempInput(title: String, block: (text: String?) -> T) {
        singleLineMessage(title)
        block(readLine())
    }

    protected inline fun newLineTempInput(title: String, block: (text: String?) -> T) {
        newLineMessage(title)
        block(readLine())
    }

    protected fun rebuild(title: String){
        widget.title = title
    }

}