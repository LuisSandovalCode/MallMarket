package com.luis.mallmarket.enums

enum class EnumDocumentType : IEnumType<String> {

    USER {
        override fun getValueEnum(): String {
            return "USERS"
        }
    }

}