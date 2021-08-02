package net.mestwin.playground.captcha.domain

import com.fasterxml.jackson.annotation.JsonProperty

class CaptchaRequest(val captchaResponse: String)

class CaptchaResponse(
    val success: Boolean,
    val score: Float? = null,
    val action: String? = null,
    private val hostname: String? = null,
    @JsonProperty("challenge_ts")
    private val challengeTimestamp: String? = null,
    @JsonProperty("error-codes")
    private val errorCodes: List<String>? = null
)
