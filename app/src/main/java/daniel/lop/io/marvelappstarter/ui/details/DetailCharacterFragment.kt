package daniel.lop.io.marvelappstarter.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.databinding.FragmentDetailsCharacterBinding
import daniel.lop.io.marvelappstarter.ui.base.BaseFragment

/*
 *  Dagger Hilt
 *  9º passo => anotação permite que a classe Filha receba injeção de dependencias
 */
@AndroidEntryPoint
class DetailCharacterFragment : BaseFragment<FragmentDetailsCharacterBinding, DetailCharacterViewModel>() {

    override val viewModel: DetailCharacterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsCharacterBinding =
        FragmentDetailsCharacterBinding.inflate(inflater, container, false)
}