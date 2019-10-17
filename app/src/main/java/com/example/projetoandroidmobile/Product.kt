package com.example.projetoandroidmobile

class Product (private var description:String, private var image: Int) {
    fun getDescription(): String { return description  }
    fun getImage(): Int { return image  }
}