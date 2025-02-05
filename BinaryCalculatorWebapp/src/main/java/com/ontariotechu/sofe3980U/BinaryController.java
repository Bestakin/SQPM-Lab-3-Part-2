package com.ontariotechu.sofe3980U;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BinaryController {

    @GetMapping("/")
    public String getCalculator(@RequestParam(name="operand1", required=false, defaultValue="") String operand1, Model model) {
        model.addAttribute("operand1", operand1);
        model.addAttribute("operand1Focused", !operand1.isEmpty());
        return "calculator";
    }

    @PostMapping("/")
    public String result(@RequestParam(name="operand1") String operand1,
                         @RequestParam(name="operator") String operator,
                         @RequestParam(name="operand2") String operand2, Model model) {
        model.addAttribute("operand1", operand1);
        model.addAttribute("operator", operator);
        model.addAttribute("operand2", operand2);

        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        String result;

        switch (operator) {
            case "+":
                result = Binary.add(number1, number2).getValue();
                break;
            case "*":
                result = Binary.multiply(number1, number2).getValue();
                break;
            case "&":
                result = Binary.and(number1, number2).getValue();
                break;
            case "|":
                result = Binary.or(number1, number2).getValue();
                break;
            default:
                model.addAttribute("error", "Invalid operator.");
                return "error";
        }
        model.addAttribute("result", fixBinaryResult(result));
        return "result";
    }

    // Ensure empty binary outputs return "0"
    private String fixBinaryResult(String result) {
        return result.isEmpty() ? "0" : result;
    }
}