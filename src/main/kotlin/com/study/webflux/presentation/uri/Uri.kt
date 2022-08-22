package com.study.webflux.presentation.uri

class Uri {
    class Author {
        class V1 {
            companion object {
                private const val V1 = "/v1"
                const val COLLECTION = "$V1/authors"
                const val SINGLE = "$COLLECTION/{id}"
            }
        }
    }
}