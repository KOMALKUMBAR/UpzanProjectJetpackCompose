package com.chat.dayoneproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chat.dayoneproject.databinding.FragmentMainBinding
import com.chat.dayoneproject.ui.MainViewModel

class MainFragment : Fragment() {

    // view binding reference
    private var _b: FragmentMainBinding? = null
    private val b get() = _b!!

    // ViewModel (from com.chat.dayoneproject.ui)
    private val vm: MainViewModel by viewModels()

    // Adapter for ViewPager2 (from com.chat.dayoneproject.model)
    private lateinit var adapter: RecipePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate and assign binding
        _b = FragmentMainBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // initialize adapter and attach to ViewPager2
        adapter = RecipePagerAdapter(emptyList())
        b.viewPager.adapter = adapter
        b.viewPager.offscreenPageLimit = 3

        // subtle left/right animation
        b.viewPager.setPageTransformer(SimpleDepthTransformer())

        // observe LiveData from ViewModel
        vm.recipes.observe(viewLifecycleOwner) { list ->
            adapter.setItems(list)
        }
        vm.loading.observe(viewLifecycleOwner) { loading ->
            b.progressBar.visibility = if (loadin
