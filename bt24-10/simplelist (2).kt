package com.example.simplelist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.simplelist.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các View
        val etInputNumber = findViewById<EditText>(R.id.etInputNumber)
        val rbEvenNumbers = findViewById<RadioButton>(R.id.rbEvenNumbers)
        val rbOddNumbers = findViewById<RadioButton>(R.id.rbOddNumbers)
        val rbPerfectSquares = findViewById<RadioButton>(R.id.rbPerfectSquares)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val lvResult = findViewById<ListView>(R.id.lvResult)
        val tvErrorMessage = findViewById<TextView>(R.id.tvErrorMessage)

        btnShow.setOnClickListener {
            val input = etInputNumber.text.toString()

            // Kiểm tra dữ liệu đầu vào
            if (input.isEmpty()) {
                tvErrorMessage.text = "Vui lòng nhập một số nguyên dương."
                tvErrorMessage.visibility = TextView.VISIBLE
                lvResult.adapter = null
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                tvErrorMessage.text = "Vui lòng nhập một số nguyên dương hợp lệ."
                tvErrorMessage.visibility = TextView.VISIBLE
                lvResult.adapter = null
                return@setOnClickListener
            }

            // Xử lý theo loại số được chọn
            val resultList = when {
                rbEvenNumbers.isChecked -> getEvenNumbers(n)
                rbOddNumbers.isChecked -> getOddNumbers(n)
                rbPerfectSquares.isChecked -> getPerfectSquares(n)
                else -> {
                    tvErrorMessage.text = "Vui lòng chọn một loại số."
                    tvErrorMessage.visibility = TextView.VISIBLE
                    lvResult.adapter = null
                    return@setOnClickListener
                }
            }

            // Cập nhật ListView với kết quả
            tvErrorMessage.visibility = TextView.GONE
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            lvResult.adapter = adapter
        }
    }

    // Hàm lấy danh sách số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    // Hàm lấy danh sách số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    // Hàm lấy danh sách số chính phương từ 0 đến n
    private fun getPerfectSquares(n: Int): List<Int> {
        val perfectSquares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            perfectSquares.add(i * i)
            i++
        }
        return perfectSquares
    }
}
