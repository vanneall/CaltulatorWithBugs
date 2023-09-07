package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.Stack

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.numOne.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "1"
        }

        binding.numTwo.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "2"
        }

        binding.numThree.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "3"
        }

        binding.numFour.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "4"
        }

        binding.numFive.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "5"
        }

        binding.numSix.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "6"
        }

        binding.numSev.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "7"
        }

        binding.numEight.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "8"
        }

        binding.numNine.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "9"
        }

        binding.numZero.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "0"
        }

        binding.opDiv.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "/"
        }
        binding.opMinus.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "-"
        }
        binding.opPlus.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "+"
        }
        binding.opMul.setOnClickListener {
            binding.expression.text = binding.expression.text.toString() + "*"
        }

        binding.save.setOnClickListener {
            val a = 1 / 0
        }

        binding.load.setOnClickListener {
            val a = 1 / 0
        }

        binding.opEq.setOnClickListener {
            binding.expression.text = evaluatePostfixExpression(infixToPostfix(binding.expression.text.toString())).toString()
        }
    }

    private fun infixToPostfix(infixExpression: String): String {
        val output = StringBuilder()
        val stack = Stack<Char>()

        val operators = "+-*/"
        val precedence = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2)

        for (token in infixExpression) {
            if (Character.isDigit(token) || token.isLetter()) {
                output.append(token)
            } else if (token in operators) {
                while (!stack.isEmpty() && stack.peek() in operators && precedence[stack.peek()]!! >= precedence[token]!!) {
                    output.append(stack.pop())
                }
                stack.push(token)
            } else if (token == '(') {
                stack.push(token)
            } else if (token == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop())
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop()
                }
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop())
        }

        return output.toString()
    }

    private fun evaluatePostfixExpression(postfixExpression: String): Int {
        val stack = Stack<Int>()

        for (token in postfixExpression.split("\\s".toRegex())) {
            if (token.matches("[0-9]+".toRegex())) {
                stack.push(token.toInt())
            } else if (token in "+-*/") {
                val operand2 = stack.pop()
                val operand1 = stack.pop()
                when (token) {
                    "+" -> stack.push(operand1 + operand2)
                    "-" -> stack.push(operand1 - operand2)
                    "*" -> stack.push(operand1 * operand2)
                    "/" -> stack.push(operand1 / operand2)
                }
            }
        }

        return stack.pop()
    }

}