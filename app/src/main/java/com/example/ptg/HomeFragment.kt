package com.example.ptg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ptg.model.Category
import com.example.ptg.model.Recommendation

class HomeFragment : Fragment() {
    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var recommendationsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inisialisasi RecyclerView untuk kategori
        categoriesRecyclerView = view.findViewById(R.id.recyclerViewCategories)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val categories = createCategoryList() // Isi list categories sesuai kebutuhan Anda
        val categoriesAdapter = CategoriesAdapter(categories)
        categoriesRecyclerView.adapter = categoriesAdapter

        // Inisialisasi RecyclerView untuk rekomendasi
        recommendationsRecyclerView = view.findViewById(R.id.recyclerViewRecommendations)
        recommendationsRecyclerView.layoutManager = LinearLayoutManager(context)
        val recommendations = createRecommendationList() // Isi list recommendations sesuai kebutuhan Anda
        val recommendationsAdapter = RecommendationsAdapter(recommendations)
        recommendationsRecyclerView.adapter = recommendationsAdapter

        return view
    }

    private fun createCategoryList(): List<Category> {
        // Contoh pengisian data untuk kategori
        val categories = mutableListOf<Category>()
        categories.add(Category(1, "Warteg", R.drawable.caffe_foreground))
        categories.add(Category(2, "Cafe", R.drawable.caffe_foreground))
        categories.add(Category(3, "Ramen", R.drawable.caffe_foreground))
        categories.add(Category(4, "Nasi Goreng", R.drawable.caffe_foreground))
        return categories
    }

    private fun createRecommendationList(): List<Recommendation> {
        // Contoh pengisian data untuk rekomendasi
        val recommendations = mutableListOf<Recommendation>()
        recommendations.add(Recommendation(1, "Restoran 1", R.drawable.caffe_foreground, 4.5f))
        recommendations.add(Recommendation(2, "Restoran 2", R.drawable.caffe_foreground, 3.8f))
        recommendations.add(Recommendation(3, "Restoran 3", R.drawable.caffe_foreground, 4.2f))
        // Tambahkan data rekomendasi sesuai kebutuhan Anda
        return recommendations
    }


}





