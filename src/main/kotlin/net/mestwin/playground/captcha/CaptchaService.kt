package net.mestwin.playground.captcha

import net.mestwin.playground.captcha.domain.CaptchaRequest
import net.mestwin.playground.captcha.domain.CaptchaResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

class CaptchaService(
    private val restTemplate: RestTemplate,
    @Value("\${pbt.api.google.recaptcha.secretKey}")
    private val secretKey: String,
    @Value("\${pbt.api.google.recaptcha.threshold}")
    private val scoreThreshold: Float
) {

    fun isValidated(request: CaptchaRequest): Boolean {
        val validationResponse = validate(request.captchaResponse)
        logger.info("Received captcha validation response: ${request.captchaResponse}")
        return validationResponse?.success == true && validationResponse.score != null && validationResponse.score >= scoreThreshold
    }

    private fun validate(answer: String): CaptchaResponse? {
        val requestMap = LinkedMultiValueMap<String, String>().apply {
            add("secret", secretKey)
            add("response", answer)
        }
        return restTemplate.postForObject(captchaApiUrl, requestMap, CaptchaResponse::class.java)
    }

    companion object {
        private const val captchaApiUrl = "https://www.google.com/recaptcha/api/siteverify"
        private val logger = LoggerFactory.getLogger(CaptchaService::class.java)
    }
}
