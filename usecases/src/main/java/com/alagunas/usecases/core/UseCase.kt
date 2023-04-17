package com.alagunas.usecases.core

interface UseCase<Params, Return> {
    operator fun invoke(params: Params): Return
}