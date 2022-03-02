package com.soonyong.customdeck.server.adaptor.presentation

import com.soonyong.customdeck.server.adaptor.presentation.model.CustomDeckPages
import com.soonyong.customdeck.server.domain.button.PageRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class CustomDeckPageController(private val pageRepository: PageRepository) {

    @GetMapping("/pages")
    suspend fun getPages(): CustomDeckPages {
        return CustomDeckPages(pageRepository.getCustomDeckPages())
    }
}