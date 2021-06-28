package com.example.tangoapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tangoapp.data.entities.News
import com.example.tangoapp.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment: Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getParcelable("news") as? News?)?.let {
            with(binding) {
                with(requireActivity() as AppCompatActivity) {
                    setSupportActionBar(detailsToolbar)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
                    Picasso.get().load(it.imageUrl).fit().centerCrop().into(detailsImage)
                    detailsTitle.text = it.title
                    detailsSite.text = it.newsSite
                    detailsSummary.text = it.summary
                }
            }
    }
}