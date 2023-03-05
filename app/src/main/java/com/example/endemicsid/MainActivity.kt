package com.example.endemicsid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.endemicsid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Hewan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHewan.setHasFixedSize(true)

        list.addAll(getListHewan())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                startActivity(Intent(this, AboutActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListHewan(): ArrayList<Hewan> {
        val dataNama = resources.getStringArray(R.array.data_nama)
        val dataNamaIlmiah = resources.getStringArray(R.array.data_nama_ilmiah)
        val dataDeskripsi = resources.getStringArray(R.array.data_deskripsi)
        val dataFoto = resources.getStringArray(R.array.data_foto)
        val listHewan = ArrayList<Hewan>()
        for (i in dataNama.indices) {
            val hewan = Hewan(dataNama[i], dataNamaIlmiah[i], dataDeskripsi[i], dataFoto[i])
            listHewan.add(hewan)
        }
        return listHewan
    }

    private fun showRecyclerList() {
        binding.rvHewan.layoutManager = LinearLayoutManager(this)
        val listHewanAdapter = ListHewanAdapter(list)
        binding.rvHewan.adapter = listHewanAdapter

        listHewanAdapter.setOnItemClickCallback(object : ListHewanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hewan) {
                showSelectedHewan(data)
            }
        })
    }

    private fun showSelectedHewan(hewan: Hewan) {
        startActivity(Intent(this, DetailActivity::class.java).putExtra(DetailActivity.EXTRA_HEWAN, hewan))
    }
}