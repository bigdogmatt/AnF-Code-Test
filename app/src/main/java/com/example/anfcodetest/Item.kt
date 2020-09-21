package com.example.anfcodetest

import com.fasterxml.jackson.annotation.JsonProperty

// Since all properties are String? they should probably be defaulted to null instead of empty String.
data class Item(@JsonProperty("title") val title: String? = "",
                @JsonProperty("target") val target: String? = "",
                @JsonProperty("elementType") val elementType: String? = "")
