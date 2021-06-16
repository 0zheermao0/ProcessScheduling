package cn.yumetsuki.lab4.model

import cn.yumetsuki.lab4.AbstractFile

class File(var data: String? = null, path: String): AbstractFile(path){
    var isOpened = false
}