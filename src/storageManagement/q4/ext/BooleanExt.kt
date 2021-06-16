package cn.yumetsuki.lab4.ext


inline fun <T> Boolean.yes(block:()->T): BooleanExt<T>{
    return if (this) {
        WithData(block())
    } else {
        OtherWise
    }
}

inline fun <T> Boolean.no(block: () -> T): BooleanExt<T>{
    return if (!this) {
        WithData(block())
    } else {
        OtherWise
    }
}

sealed class BooleanExt<out T>

object OtherWise: BooleanExt<Nothing>()

class WithData<T>(val data: T): BooleanExt<T>()

inline fun <T> BooleanExt<T>.otherWise(block: () -> T): T{
    return when(this){
        is WithData -> this.data
        is OtherWise -> block()
    }
}


