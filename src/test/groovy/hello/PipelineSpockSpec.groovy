package hello

import io.gigabyte.labs.playground.core.chain.ProcessingStep
import io.gigabyte.labs.playground.core.chain.impl.QuoteValidationStep
import io.gigabyte.labs.playground.core.exception.JsonParsingException
import io.gigabyte.labs.playground.core.model.ProcessingContext
import spock.lang.Specification

class PipelineSpockSpec extends Specification {
    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }


    def "Quote Validation JsonParserException"() {
        given:
        ProcessingStep quoteStep = new QuoteValidationStep()
        ProcessingContext processingContext = new ProcessingContext(json)

        when:
        quoteStep.process(processingContext)

        then:
        processingContext != null
        thrown(JsonParsingException)

        where:
        json                                | _
        """
        {
            "key": "value\\"
        }
        """                      | _
        """
        {
            "key: "value"
        }
        """                      | _
        """
        {
          "name": "John",
          "age": 30,
          "address": {
            "street": "21 Jump Stree",
            "city": "Sample "City",
            "zip": "12345"
          },
          "friends": ["Marry", "Oliver"]
        }
        """                              | _
        """
        {
            "description": "This is a string with some \\"escaped\\" quotes and some unbalanced "quotes.",
            "value": 100
        }
        """ | _
        """
        {
          "user: {
                "id": "U1234",
                "email": "john@example.com"
          },
          "status": "active"
        }
        """ | _

    }

    def "Quote Validation"() {
        given:
        ProcessingStep quoteStep = new QuoteValidationStep()
        ProcessingContext processingContext = new ProcessingContext(json)

        when:
        quoteStep.process(processingContext)

        then:
        processingContext != null

        where:
        json                                                                     | _
        """
        {
          "key": "value with \\\"multiple\\\" escaped \\\"quotes\\\""
        }
        """                                                           | _
        """
        {
            "key": "\\"Hello, World!\\""
        }
        """                                                           | _
        """
        {
            "key1": "Value with a \\"nested\\" quote.",
            "key2": "\\"Beginning and ending with quotes\\""
        }
        """                                                           | _
        """
        {
          "departments": [
            {
              "name": "Engineering",
              "employees": [
                {"firstName": "John", "lastName": Doe, "toher": 1},
                {"firstName": "Jane", "lastName": "Smith"}
              ]
            },
            {
              "name": "HR",
              "employees": [
                {"firstName": "Marry", "lastName": "Brown"}
              ]
            }
          ],
          "location": "HQ"
        }
        """ | _


    }
}  