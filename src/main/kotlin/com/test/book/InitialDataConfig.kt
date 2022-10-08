package com.test.book

import com.test.book.api.Book
import com.test.book.api.BookRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InitialDataConfig constructor(private val repository: BookRepository) : InitializingBean {
    override fun afterPropertiesSet() {
        repository.save(Book(123, "Test"))
        repository.save(Book(126, "Another Test"))
        repository.save(Book(119, "Final test"))
    }
}

