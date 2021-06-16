package cn.yumetsuki.lab4.view

abstract class AbstractContext<T>(
    override val data: T?
): Context<T, ContextFlag>{

    protected abstract fun run()


}

sealed class ContextFlag

object PopFlag: ContextFlag()

object JumpFlag: ContextFlag()