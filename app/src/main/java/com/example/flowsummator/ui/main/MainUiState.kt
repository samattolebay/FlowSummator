package com.example.flowsummator.ui.main

data class MainUiState(
    val error: String? = null,
    val sums: Array<Int> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MainUiState

        if (error != other.error) return false
        if (!sums.contentEquals(other.sums)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = error?.hashCode() ?: 0
        result = 31 * result + sums.contentHashCode()
        return result
    }
}
