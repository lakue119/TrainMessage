package com.lakue.trainmessage

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.lakue.domain.TrainDto
import com.lakue.trainmessage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var trainInfo: TrainDto? = null

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val data = it.data?.getSerializableExtra("result") as TrainDto
            trainInfo = data
            binding.tvTrainLine.text = trainInfo!!.name
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvTrainLine.setTextColor(getColor(R.color.black))
            }
        }
    }

    private val messageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val data = it.data?.getStringExtra("message")
            binding.etMessage.setText(data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setEvent()
    }

    private fun setEvent() {
        binding.run {
            tvTrainLine.setOnClickListener {
                launcher.launch(
                    Intent(
                        this@MainActivity,
                        SelecLineTypeActivity::class.java
                    )
                )
            }
            tvMessageSample.setOnClickListener {
                messageLauncher.launch(
                    Intent(
                        this@MainActivity,
                        SampleMessageActivity::class.java
                    )
                )
            }
            btnSendSms.setOnClickListener {
                if(trainInfo == null || etTrainNumber.text.isNullOrBlank()) {
                    showToast("열차 정보를 입력해주세요.")
                    return@setOnClickListener
                }
                if(etMessage.text.isNullOrBlank()) {
                    showToast("문의 내용을 입력해주세요.")
                    return@setOnClickListener
                }

                val text = """
${trainInfo?.name}
열차번호 : ${etTrainNumber.text}
문의내용 : ${etMessage.text}
                """.trimIndent()
                sendSMS(
                    trainInfo?.phone ?: "",
                    text
                )
            }
        }
    }

    private fun sendSMS(phone: String, message: String) {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        sendIntent.setData(Uri.parse("sms:$phone"))
        sendIntent.putExtra("sms_body", message)
        startActivity(sendIntent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}