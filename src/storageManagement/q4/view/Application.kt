package storageManagement.q4.view

import java.util.*

class Application: AbstractContext<Unit>(Unit) {

    private val contexts: Stack<Context<*, ContextFlag>> = Stack()

    override fun pop(): PopFlag {
        return PopFlag
    }

    override fun <T> push(context: Context<T, ContextFlag>) {
        contexts.push(context)
        (context as CommandPage).apply {
            widget = create()
            widget.run()
        }
    }

    override fun run() {
        (contexts.peek() as CommandPage).create().run()
    }

    fun start(context: Context<*, ContextFlag>){
        push(context)
    }

}