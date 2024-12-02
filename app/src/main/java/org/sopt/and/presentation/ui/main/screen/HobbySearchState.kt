package org.sopt.and.presentation.ui.main.screen

import org.sopt.and.domain.model.Hobby

sealed class HobbySearchState {
    data object Idle: HobbySearchState()
    data object Loading: HobbySearchState()
    data class Success(val result: Hobby): HobbySearchState()
    data class Failure(val errorMessage: String): HobbySearchState()
}