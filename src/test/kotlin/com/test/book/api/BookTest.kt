package com.test.book.api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest(showSql = true)
internal class BookTest {

    @Autowired
    lateinit var repository: BookRepository;

    private fun saveNewBook(): Book {
        val book = Book(1234, "my book")
        repository.save(book)
        return book
    }

    @Test
    @DisplayName("book is persistable")
    fun book_() {
        val book = saveNewBook()
        assertEquals(repository.getReferenceById(1), book)
    }


    @Test
    @DisplayName("book is deletable")
    fun bookTest_() {
        var book = saveNewBook()
        book.id?.let { repository.deleteById(it) }
        book = saveNewBook()
        repository.delete(book)
        assertTrue(repository.findAll().isEmpty())
    }
}