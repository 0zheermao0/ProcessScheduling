package cn.yumetsuki.lab4.model

class UFD(
    path: String,
    previous: Directory,
    private val password: String
): Directory(path, previous){

    fun checkPassword(password: String) = this.password == password


}