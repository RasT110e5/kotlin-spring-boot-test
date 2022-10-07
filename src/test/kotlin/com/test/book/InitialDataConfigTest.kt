package com.test.book

import com.test.book.api.BookRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
internal class InitialDataConfigTest {

    @Mock
    lateinit var repository: BookRepository

    @InjectMocks
    lateinit var initialDataConfig: InitialDataConfig

    @Test
    @DisplayName("should insert 3 books")
    fun initialDataConfigTest_() {
        initialDataConfig.afterPropertiesSet()
        verify(repository, times(3)).save(any())
    }
}