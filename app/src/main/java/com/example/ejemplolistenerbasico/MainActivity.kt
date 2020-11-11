package com.example.ejemplolistenerbasico

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    // Este ejercicio se compone de 2 botones ( + y - ) que al ser pulsados van a sumar o restar
    // el valor que encontremos en un textView.

    // Añade un editText. Cuando el edittext tenga el focus activo, los botones deben cambiar de
    // color. Cuando lo tenga desactivado, deben volver a su color original.

    // Si el usuario escribe un número en el editText, entonces el textView debe mostrar ese número

    // TODO: El número que aparece en textView no puede ser negativo.

    // TODO: Una vez que has introducido un número en el textView, no puede volver a quedar vacio.

    // Investigar como private var colorOriginal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonAdd = findViewById<Button>(R.id.bAdd)
        val textViewNumber = findViewById<TextView>(R.id.tvNumber)
        val buttonRemove = findViewById<Button>(R.id.bRemove)
        val editTextNumero = findViewById<EditText>(R.id.etNumero)

        /*val background = buttonAdd.background
        if (background is ColorDrawable){
            colorOriginal = background.color
        }*/

        buttonAdd.setOnClickListener {
            var numero = textViewNumber.text.toString().toInt()
            numero++
            textViewNumber.text = numero.toString()
        }

        buttonRemove.setOnClickListener {
            var numero : Int = Integer.valueOf(textViewNumber.text.toString())
            numero--
            if (numero>=0)
                textViewNumber.text = numero.toString()
            else
                Toast.makeText(this,"El número no puede ser negativo",Toast.LENGTH_LONG).show()
        }
        editTextNumero.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                buttonAdd.setBackgroundColor(Color.GREEN)
                buttonRemove.setBackgroundColor(Color.GREEN)
            } else {
                buttonAdd.setBackgroundColor(Color.BLUE)
                buttonRemove.setBackgroundColor(Color.BLUE)
            }
        }
        editTextNumero.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isNotEmpty() && it.toString().toInt()>=0)
                        textViewNumber.text = it
                    else if (it.isNotEmpty() && it.toString().toInt()<0)
                        Toast.makeText(this@MainActivity,"El número para el textView no puede ser negativo",Toast.LENGTH_LONG).show()
                    else if (it.isEmpty()){
                        Toast.makeText(this@MainActivity,"El número del editText textView no puede quedar vacío",Toast.LENGTH_LONG).show()
                        editTextNumero.text = textViewNumber.text as Editable?
                    }
                }
            }
        })
    }
}