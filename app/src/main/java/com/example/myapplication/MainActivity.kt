package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.Stack

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var boolean: Boolean = false

    private var counter = 0

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
            if (!boolean) {
                val a = 1 / 0
            }

        }

        binding.load.setOnClickListener {
            if (!boolean) {
                val a = 1 / 0
            }
        }

        binding.opAc.setOnClickListener {
            binding.expression.text = ""
        }

        binding.opEq.setOnClickListener {
            Thread.sleep(counter * 1000L)
            binding.expression.text =
                evaluatePostfixExpression(infixToPostfix(binding.expression.text.toString())).toString()
            counter++
        }

        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            boolean = isChecked
        }
    }

    fun infixToPostfix(infixExpression: String): String {
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

    fun evaluatePostfixExpression(postfixExpression: String): Int {
        val stack = Stack<Int>()

        for (i in postfixExpression) {
            if (i - '0' in 0..9) {
                stack.push(i - '0')
            } else {
                val a = stack.pop()
                val b = stack.pop()
                when (i) {
                    '+' -> stack.push(b + a)
                    '*' -> stack.push(b * a)
                    '-' -> stack.push(b - a)
                    '/' -> stack.push(b / a)
                }
            }
        }

        return stack.pop()
    }

}