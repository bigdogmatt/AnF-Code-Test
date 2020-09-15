package com.example.anfcodetest

import com.fasterxml.jackson.annotation.JsonProperty


data class Item(@JsonProperty("title") val title: String? = "",
                @JsonProperty("target") val target: String? = "",
                @JsonProperty("elementType") val elementType: String? = "")