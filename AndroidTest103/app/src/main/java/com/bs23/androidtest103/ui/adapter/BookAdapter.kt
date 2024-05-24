package com.bs23.androidtest103.ui.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bs23.androidtest103.R
import com.bs23.androidtest103.data.model.Book
import com.bs23.androidtest103.databinding.RecyclerItemBinding
import com.bs23.androidtest103.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

class BookAdapter() : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val books = mutableListOf<Book>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(books: List<Book>) {
        this.books.addAll(books)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.name.text = book.name
            binding.url.text = book.url
            binding.loader.visibility = View.VISIBLE
            Glide.with(this.itemView)
                .load(book.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.loader.visibility = View.GONE
                        return false
                    }
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.loader.visibility = View.GONE
                        binding.deadlink.visibility = View.VISIBLE
                        return false
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        holder.bind(books[position])
        holder.itemView.setOnClickListener {
             val bundle = Bundle()
             bundle.putString(Constants.KEY_BOOK_URL, books[position].url)
             it.findNavController().navigate(R.id.action_homeFragment_to_webViewFragment, bundle)
        }
    }

    override fun getItemCount() = books.size
}