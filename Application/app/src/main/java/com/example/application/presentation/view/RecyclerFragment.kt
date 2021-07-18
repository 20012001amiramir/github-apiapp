package com.example.application.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.application.R
import com.example.application.databinding.FragmentRecyclerFragmentBinding

class RecyclerFragment : Fragment(R.layout.fragment_recycler_fragment) {

    private var _binding: FragmentRecyclerFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerFragmentBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

}