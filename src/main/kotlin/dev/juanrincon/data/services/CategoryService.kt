package dev.juanrincon.data.services

import dev.juanrincon.domain.models.ApiResponse
import dev.juanrincon.domain.models.Category
import dev.juanrincon.data.repositories.CategoryRepository

class CategoryService {

    private val repo = CategoryRepository()

    fun getAllCategories(): ApiResponse<List<Category>> {
        val categories = repo.getAll()

        return if (categories.isNotEmpty()) {
            ApiResponse.success(categories)
        } else {
            ApiResponse.error("Failed to get categories")
        }
    }
}