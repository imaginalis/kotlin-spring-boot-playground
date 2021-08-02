package net.mestwin.playground.captcha

import net.mestwin.playground.captcha.domain.CaptchaRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.web.client.RestTemplateBuilder

internal class CaptchaServiceTest {

    @Test
    fun `should test captcha validation`() {
        val service = CaptchaService(RestTemplateBuilder().build(), "s3cr3t", 0.5F)

        val result = service.isValidated(CaptchaRequest("responsive"))

        assertThat(result).isTrue
    }
}
