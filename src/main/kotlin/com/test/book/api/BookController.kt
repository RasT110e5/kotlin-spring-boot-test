package com.test.book.api

import com.test.book.api.dto.CreateBookDto
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/book")
class BookController constructor(private val service: BookService) {
    @GetMapping
    fun getBooks(): ResponseEntity<MutableList<Book>> {
        return ResponseEntity.ok(service.getBooks())
    }

    @PostMapping
    fun createBook(@RequestBody dto: CreateBookDto): ResponseEntity<Book> {
        return ResponseEntity(service.createBook(dto), HttpStatus.CREATED)
    }

    @DeleteMapping
    fun deleteBook(@Param("id") id: Long): ResponseEntity<String> {
        service.delete(id)
        return ResponseEntity.ok("ok")
    }
}