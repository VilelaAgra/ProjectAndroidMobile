package com.example.projetoandroidmobile.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projetoandroidmobile.Product
import com.example.projetoandroidmobile.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectListener =
        BottomNavigationView.OnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.maps -> {
                    replaceFragment(MapFragment())
                }
                R.id.logout -> {
                    goToLogin()
                }
            }
        }

    private lateinit var adapter: CustomAdapter
    private lateinit var mGridView: GridView

    private val data: ArrayList<Product>
        get() {
            val products = ArrayList<Product>()
            var product = Product(
                "Exemplo Mochila",
                R.mipmap.bag
            )
            products.add(product)
            product = Product(
                "Exemplo Bicicleta",
                R.mipmap.bike
            )
            products.add(product)
            product = Product(
                "Exemplo Canudos",
                R.mipmap.straw
            )
            product = Product(
                "Logo1",
                R.mipmap.logo
            )
            product = Product(
                "Logo2",
                R.mipmap.logo
            )
            product = Product(
                "Logo3",
                R.mipmap.logo
            )
            product = Product(
                "Logo4",
                R.mipmap.logo
            )
            product = Product(
                "Logo5",
                R.mipmap.logo
            )
            product = Product(
                "Logo6",
                R.mipmap.logo
            )
            product = Product(
                "Logo7",
                R.mipmap.logo
            )
            return products
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemReselectedListener(mOnNavigationItemSelectListener)

        mGridView = findViewById(R.id.myGridView) as GridView

        adapter = CustomAdapter(this, data)
        mGridView.adapter = adapter
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun goToLogin() {
        val intent = Intent(this@HomeActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun goToMaps() {
        val intent = Intent(this@HomeActivity, MapsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    class CustomAdapter(private var context: Context, private var products: ArrayList<Product>) :
        BaseAdapter() {
        override fun getCount(): Int {
            return products.size
        }

        override fun getItem(i: Int): Any {
            return products[i]
        }

        override fun getItemId(i: Int): Long {
            return i.toLong()
        }

        override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
            var view = view
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.row_model, viewGroup, false)
            }

            val product = this.getItem(i) as Product
            val img = view!!.findViewById(R.id.imageView) as ImageView
            val nameTxt = view.findViewById(R.id.descriptionTxt) as TextView
            nameTxt.text = product.getDescription()
            img.setImageResource(product.getImage())

            view.setOnClickListener {
                Toast.makeText(context, product.getDescription(), Toast.LENGTH_SHORT).show()
            }
            return view
        }
    }
}