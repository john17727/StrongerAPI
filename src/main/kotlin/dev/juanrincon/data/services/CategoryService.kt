package dev.juanrincon.data.services

import dev.juanrincon.domain.models.ApiResponse
import dev.juanrincon.domain.models.Category
import dev.juanrincon.domain.interfaces.Repository

class CategoryService(private val repository: Repository<Category>) {

    fun getAllCategories(): ApiResponse<List<Category>> {
        val categories = repository.getAll()

        return if (categories.isNotEmpty()) {
            ApiResponse.success(categories)
        } else {
            ApiResponse.error("Failed to get categories")
        }
    }
}