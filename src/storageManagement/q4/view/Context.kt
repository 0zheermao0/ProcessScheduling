package storageManagement.q4.view

interface Context<T, E>{

    val data: T?

    fun pop(): E

    fun <T> push(context: Context<T, E>)

}