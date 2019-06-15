package com.iavariav.tamobile_shared.rest

import com.iavariav.tamobile_shared.BuildConfig
import com.iavariav.tamobile_shared.model.RootDataModel
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

interface ApiService {

    //    http://informatika.upgris.ac.id/mobile/data/index.php/api/example/detil_jadwal?key=33c227f799464dac08f60f1b0d5770&npm=16670025
    @GET("detil_jadwal?key=" + BuildConfig.API_KEY + "&npm=" + BuildConfig.NPM)
    fun getJadwalKuliah(
    ): Observable<RootDataModel>

    @GET("detil_pribadi?key=" + BuildConfig.API_KEY + "&npm=" + BuildConfig.NPM)
    fun getProfil(
    ): Observable<RootDataModel>

}