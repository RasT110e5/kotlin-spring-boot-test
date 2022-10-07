package com.test.book.api

import com.test.book.api.dto.CreateBookDto

interface BookService {
    fun getBooks(): MutableList<Book>
    fun createBook(dto: CreateBookDto): Book
    fun delete(id: Long)
}