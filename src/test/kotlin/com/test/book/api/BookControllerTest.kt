package com.test.book.api

import com.test.book.api.dto.CreateBookDto
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [BookController::class])
internal class BookControllerTest {

    @MockBean
    private lateinit var service: BookService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @DisplayName("should return 3 books")
    fun controller_() {
        whenever(service.getBooks()).thenReturn(mutableListOf(Book(1, "test book")))
        mvc.perform(get("/api/book"))
            .andExpect(status().isOk)
            .andExpect(content().json("[{\"isbn\":1,\"name\":\"test book\"}]"))
    }

    @Test
    @DisplayName("should return Book that was created")
    fun bookControllerTest_() {
        whenever(service.createBook(any())).thenReturn(Book(123, "test book"))
        mvc.perform(
            post("/api/book").content("{\"isbn\":123,\"name\":\"test book\"}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated)
            .andExpect(content().json("{\"isbn\":123,\"name\":\"test book\",\"id\": 1}"))
    }

    @Test
    @DisplayName("should return 200 when deleting")
    fun bookControllerTest_1() {
        mvc.perform(delete("/api/book").queryParam("id", "1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
        verify(service, times(1)).delete(1);
    }

}
