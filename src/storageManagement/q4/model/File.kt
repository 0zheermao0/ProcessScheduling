package storageManagement.q4.model

import storageManagement.q4.AbstractFile

class File(var data: String? = null, path: String): AbstractFile(path){
    var isOpened = false
}