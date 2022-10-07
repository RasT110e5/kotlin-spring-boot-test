package com.test.book.api.impl

import com.test.book.api.Book
import com.test.book.api.BookRepository
import com.test.book.api.BookService
import com.test.book.api.dto.CreateBookDto
import org.springframework.stereotype.Service

@Service
class BookServiceImpl constructor(private val repository: BookRepository) : BookService {
    override fun getBooks(): MutableList<Book> {
        return repository.findAll()
    }

    override fun createBook(dto: CreateBookDto): Book {
        return repository.save(dto.toEntity())
    }

    override fun delete(id: Long) {
        repository.deleteById(id)
    }
}