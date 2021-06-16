package storageManagement.q4.model

import storageManagement.q4.model.Directory

//单独用户文件目录
class UFD(
        path: String,
        previous: Directory,
        private val password: String
): Directory(path, previous){

    fun checkPassword(password: String) = this.password == password


}