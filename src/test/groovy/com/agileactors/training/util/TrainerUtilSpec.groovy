package com.agileactors.training.util

import spock.lang.Specification
import spock.lang.Unroll

class TrainerUtilSpec extends Specification {

    @Unroll
    def "isValidEmail method returns correct value when called"(String email, boolean result) {
        when:
          boolean isValid = TrainerUtil.isValidEmail(email)
        then:
          isValid == result
        where:
          email | result
          "test@test.com" | true
          "testtest.com" | false
          "testtestcom" | false
          "test@testcom" | false
    }
}
