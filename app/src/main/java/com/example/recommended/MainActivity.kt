package com.example.recommended

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialise()
    }

    private fun initialise() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()

        //setting toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var adapter: CategoryAdapter? = null
        var categoryList : ArrayList<Categories>
        categoryList = generateCategoryData()
        adapter = CategoryAdapter(this, categoryList)

        val gridview = findViewById<GridView>(R.id.categories)
        gridview.adapter = adapter

        gridview.setOnItemClickListener {adapterView, view, i, l ->
            Toast.makeText(this, "You have selected "+ categoryList.get(i).category_name, Toast.LENGTH_SHORT).show()

            var categoryName: String? = categoryList.get(i).category_name

            when(categoryName){
                "Movies" -> switchToMovieActivity()
                "Food" -> switchToFoodActivity()
                "Music" -> switchToMusicActivity()
                "Travel" -> switchToTravelActivity()
                "Podcast" -> switchToPodcastActivity()
                "Games" -> switchToGamesActivity()
            }
        }


    }

    override fun onStart() {
        super.onStart()

        //Brukt tidligere når Main Activity viste brukerinfo på skjermen
        /*val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        tvEmail!!.text = mUser.email
        tvEmailVerified!!.text = mUser.isEmailVerified.toString()
        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tvUsername!!.text = snapshot.child("lastName").value as String
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })*/
    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.logout -> {
            // User chose the "Print" item
            Toast.makeText(this,"Logged out", Toast.LENGTH_SHORT).show()
            mAuth?.signOut()
            finish()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun generateCategoryData(): ArrayList<Categories> {
        var result = ArrayList<Categories>()

        var category = Categories()
        category.category_id = 1
        category.category_name = "Movies"
        category.category_photo = R.drawable.movie_image
        result.add(category)

        category = Categories()
        category.category_id = 2
        category.category_name = "Food"
        category.category_photo = R.drawable.food_image
        result.add(category)

        category = Categories()
        category.category_id = 3
        category.category_name = "Music"
        category.category_photo = R.drawable.music_image
        result.add(category)

        category = Categories()
        category.category_id = 4
        category.category_name = "Travel"
        category.category_photo = R.drawable.travel_image
        result.add(category)

        category = Categories()
        category.category_id = 5
        category.category_name = "Games"
        category.category_photo = R.drawable.games_image
        result.add(category)

        category = Categories()
        category.category_id = 6
        category.category_name = "Podcast"
        category.category_photo = R.drawable.podcast_image
        result.add(category)

        return result
    }

    fun switchToFoodActivity(){
        val intent = Intent(this@MainActivity, FoodRecommendationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun switchToMovieActivity(){
        val intent = Intent(this@MainActivity, MovieRecommendationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun switchToMusicActivity(){
        val intent = Intent(this@MainActivity, MusicRecommendationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun switchToTravelActivity(){
        val intent = Intent(this@MainActivity, TravelRecommendationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun switchToGamesActivity(){
        val intent = Intent(this@MainActivity, GamesRecommendationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun switchToPodcastActivity(){
        val intent = Intent(this@MainActivity, PodcastRecommendationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
