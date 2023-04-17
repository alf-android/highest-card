package com.alagunas.usecases.core

interface UseCaseSuspend<Params, Return> {
    suspend operator fun invoke(params: Params): Return
}