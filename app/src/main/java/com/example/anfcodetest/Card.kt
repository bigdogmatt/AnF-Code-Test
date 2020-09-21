package com.example.anfcodetest

import com.fasterxml.jackson.annotation.JsonProperty

data class Card(
    @JsonProperty("title") val title: String? = "",
    @JsonProperty("backgroundImage") val backgroundImage: String? = "",
    @JsonProperty("content") val content: List<Item>?,
    @JsonProperty("promoMessage") val promoMessage: String? = "",
    @JsonProperty("topDescription") val topDescription: String? = "",
    @JsonProperty("bottomDescription") val bottomDescription: String? = ""
)