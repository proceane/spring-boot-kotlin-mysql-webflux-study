package com.study.webflux.presentation.uri

class Uri {
    class Author {
        companion object {
            private const val V1 = "/v1"
            const val V1_COLLECTION = "$V1/authors"
            const val V1_SINGLE = "$V1_COLLECTION/{id}"
        }
    }
}