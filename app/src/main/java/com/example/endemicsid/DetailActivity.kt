package com.example.endemicsid

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.endemicsid.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_HEWAN = "extra_hewan"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hewan = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_HEWAN, Hewan::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_HEWAN)
        }

        if (hewan != null) {
            binding.tvNamaHewanDetail.text = hewan.nama.toString()
            binding.tvIlmiahDetail.text = hewan.ilmiah.toString()
            binding.tvDeskripsi.text = hewan.deskripsi.toString()
            Glide.with(this)
                .load(hewan.foto.toString())
                .apply(RequestOptions.centerCropTransform())
                .into(binding.ivFotoDetail)
        }

        binding.actionShare.setOnClickListener {
            Toast.makeText(this, "Terimakasih sudah membagikan konten ini", Toast.LENGTH_LONG).show()
        }

    }
}