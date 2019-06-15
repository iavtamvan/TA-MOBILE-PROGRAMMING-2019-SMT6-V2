package com.iavariav.tamobile_shared.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.iavariav.tamobile_shared.BuildConfig
import com.iavariav.tamobile_shared.R
import com.iavariav.tamobile_shared.model.DataItemModel
import com.iavariav.tamobile_shared.rest.ApiService
import com.iavariav.tamobile_shared.rest.RetroConfig
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profil.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfilFragment : Fragment() {
    private var items: ArrayList<DataItemModel> = arrayListOf()
    private lateinit var npmTexView: TextView
    private lateinit var nik: TextView
    private lateinit var nisn: TextView
    private lateinit var ivImageProfile: CircleImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)
        ivImageProfile = view.ivImageProfile
        npmTexView = view.tvNpm
        nik = view.tvNik
        nisn = view.tvNISN
        npmTexView.text = "16670025"

        getDatas()

        return view;
    }

    private fun getDatas() {
        val apiService: ApiService = RetroConfig.provideApi()
        apiService.getProfil()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.clear()
                items = it.data as ArrayList<DataItemModel>
                for (i: Int in items.indices) {
                    nik.text = items.get(i).nik
                    nisn.text = items.get(i).nisn
                    activity?.let { it1 ->
                        Glide.with(it1).load("http://informatika.upgris.ac.id/mobile/image/" + items.get(i).foto)
                            .override(512, 512).error(R.drawable.error_image).into(ivImageProfile)
                    }
                    if (BuildConfig.NPM.equals("16670025")) {
                        nik.text = "Kepo"
                        nisn.text = "Kepo"
                        activity?.let { it1 ->
                            Glide.with(it1).load("https://avatars0.githubusercontent.com/u/28645602?s=460&v=4")
                                .override(512, 512).error(R.drawable.error_image).into(ivImageProfile)
                        }
                    }
                }


            }, {
                error("Error" + it.message)
            })

    }


}
