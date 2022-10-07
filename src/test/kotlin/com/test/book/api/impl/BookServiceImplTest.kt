package com.test.book.api.impl

import com.test.book.api.Book
import com.test.book.api.BookRepository
import com.test.book.api.dto.CreateBookDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.argThat
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class BookServiceImplTest {
    @Mock
    private lateinit var repository: BookRepository

    @InjectMocks
    private lateinit var service: BookServiceImpl

    @Test
    @DisplayName("it should return items from repository")
    fun service_() {
        whenever(repository.findAll()).thenReturn(mutableListOf(Book(1, 123, "test")))
        val books = service.getBooks()
        assertFalse(books.isEmpty())
        assertEquals(1, books.size)
    }

    @Test
    @DisplayName("it should create new book with received dto data")
    fun bookServiceImplTest_() {
        val expectedName = "test"
        val expectedIsbn = 123L
        val dto = CreateBookDto(expectedName, expectedIsbn)
        val expectedEntity = dto.toEntity()
        expectedEntity.id = 1
        whenever(repository.save(dto.toEntity())).thenReturn(expectedEntity)

        val book = service.createBook(dto)

        assertEquals(expectedEntity, book)
        verify(repository, times(1)).save(
            argThat { t -> t.name == expectedName && t.isbn == expectedIsbn }
        )
    }

    @Test
    @DisplayName("should be able to delete books")
    fun bookServiceImplTest_1() {
        service.delete(1L)
        verify(repository, times(1)).deleteById(1)
    }

}