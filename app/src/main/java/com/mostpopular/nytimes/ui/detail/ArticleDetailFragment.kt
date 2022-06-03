package com.mostpopular.nytimes.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import coil.load
import com.mostpopular.nytimes.R
import com.mostpopular.nytimes.databinding.FragmentArticleDetailBinding


class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.fragment = this

        val selectedArticle =
            ArticleDetailFragmentArgs.fromBundle(requireArguments()).selectedArticle

        binding.article = selectedArticle

    }

     fun openShareDialog(url: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "${resources.getString(R.string.check_this_article)} : $url"

        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

     fun goToWeb(text: String?) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(text)
        startActivity(i)
    }

    companion object{
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(ivFullImage: ImageView, url: String) {
            url.let {
                ivFullImage.load(url) {
                    placeholder(R.drawable.loading_img)
                    error(R.drawable.ic_broken_image)
                }
            }
        }
    }

}