package daniel.lop.io.marvelappstarter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import daniel.lop.io.marvelappstarter.R
import daniel.lop.io.marvelappstarter.data.model.character.CharacterModel
import daniel.lop.io.marvelappstarter.data.model.comic.ComicModel
import daniel.lop.io.marvelappstarter.databinding.ItemCharacterBinding
import daniel.lop.io.marvelappstarter.databinding.ItemComicBinding
import daniel.lop.io.marvelappstarter.util.limitDescription

class ComicAdapter : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ItemComicBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        holder.binding.apply {
            tvNameComic.text = comic.title
            tvDescriptionComic.text = comic.description

            // Carrega img de uma url
            Glide.with(holder.itemView.context)
                .load(comic.thumbnail.path + "." + comic.thumbnail.extension)
                .into(imgComic)
        }

    }

    override fun getItemCount(): Int = comics.size

    // COMPARA AS LISTAS PARA ATUALIZAR SOMENTE SE HOUVER MUDANÇAS
    private val differCallBack = object : DiffUtil.ItemCallback<ComicModel>() {
        override fun areItemsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
        override fun areContentsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.thumbnail.path == newItem.thumbnail.path &&
                    oldItem.thumbnail.extension == newItem.thumbnail.extension
        }
    }
    private val differ = AsyncListDiffer(this, differCallBack)
    var comics : List<ComicModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    inner class ComicViewHolder(val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root)

}