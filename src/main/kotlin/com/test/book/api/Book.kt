package com.test.book.api

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    val isbn: Long,
    val name: String,
) {
    constructor(isbn: Long, name: String) : this(null, isbn, name)
}
