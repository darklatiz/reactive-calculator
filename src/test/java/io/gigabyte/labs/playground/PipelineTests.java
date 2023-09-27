package io.gigabyte.labs.playground;

import io.gigabyte.labs.playground.core.chain.impl.BracketValidationStep;
import io.gigabyte.labs.playground.core.exception.JsonParsingException;
import io.gigabyte.labs.playground.core.model.ProcessingContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PipelineTests {

    String[] balancedJson = {
      """
      {"k": "v"}
      """,
      """
      {"outer": {"inner": "value"}}
      """,
      """
      [{"item1": "value1"}, {"item2": "value2"}]
      """,
      """
      {
        "data": {
          "items": [
            {"name": "apple", "type": "fruit"},
            {"name": "carrot", "type": "vegetable"}
          ],
          "info": {
            "timestamp": "2023-01-01",
            "source": "farm"
          }
        }
      }
      """,
      """
      [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
      """
    };

    String[] unBalancedJson = {
      """
      {"key": "value"
      """,
      """
      {"key": "value"}}
      """,
      """
      {"items": [1, 2, 3}}
      """,
      """
      {"data": {"items": [1, 2, 3]}
      """};

    @Test
    void test_bracket_validation() throws JsonParsingException {
        for (String json : balancedJson) {
            BracketValidationStep bracketValidationStep = new BracketValidationStep();
            ProcessingContext processingContext = new ProcessingContext(json);
            Assertions.assertDoesNotThrow(() -> bracketValidationStep.process(processingContext));
        }
    }

    @Test
    void tes_brack_unbalanced() {
        for (String unJson : unBalancedJson) {
            BracketValidationStep bracketValidationStep = new BracketValidationStep();
            ProcessingContext unCtx = new ProcessingContext(unJson);
            Assertions.assertThrows(JsonParsingException.class, () -> bracketValidationStep.process(unCtx));
        }
    }

}
