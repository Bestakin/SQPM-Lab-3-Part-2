package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")  // This ensures all API routes are prefixed with "/api"
public class BinaryAPIController {

    @GetMapping("/add")
    public String add(@RequestParam String operand1, @RequestParam String operand2) {
        return fixBinaryResult(Binary.add(new Binary(operand1), new Binary(operand2)).getValue());
    }

    @GetMapping("/add_json")
    public BinaryAPIResult addJSON(@RequestParam String operand1, @RequestParam String operand2) {
        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return new BinaryAPIResult(number1, "add", number2, Binary.add(number1, number2));
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam String operand1, @RequestParam String operand2) {
        return fixBinaryResult(Binary.multiply(new Binary(operand1), new Binary(operand2)).getValue());
    }

    @GetMapping("/and")
    public String and(@RequestParam String operand1, @RequestParam String operand2) {
        return fixBinaryResult(Binary.and(new Binary(operand1), new Binary(operand2)).getValue());
    }

    @GetMapping("/or")
    public String or(@RequestParam String operand1, @RequestParam String operand2) {
        return fixBinaryResult(Binary.or(new Binary(operand1), new Binary(operand2)).getValue());
    }

    // Helper method to ensure empty binary values are represented correctly
    private String fixBinaryResult(String result) {
        return result.isEmpty() ? "0" : result;
    }
}
