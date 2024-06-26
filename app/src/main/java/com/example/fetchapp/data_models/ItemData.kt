package com.example.fetchapp.data_models

data class ItemData(
    val id: Int,
    val listId: Int,
    val name: String?
) {
    override fun toString(): String {
        return "Group:$listId \t\t Name: $name"
    }
}