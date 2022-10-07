package com.test.book.api.dto

import com.test.book.api.Book

data class CreateBookDto(val name: String, val isbn: Long) {
    fun toEntity(): Book {
        return Book(isbn, name)
    }
}