package org.sopt.and.data.remote.util

import org.sopt.and.data.remote.model.base.ApiResponse

fun <T> ApiResponse<T>.handleApiResponse(): Result<T> {
    return if (this.result != null) Result.success(this.result)
    else Result.failure(Exception("No data Available"))
}