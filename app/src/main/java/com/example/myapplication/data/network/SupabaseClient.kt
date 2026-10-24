package com.example.myapplication.data.network

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://gsmmkohlfkvapgvshdgz.supabase.co/rest/v1/",
        supabaseKey = "sb_publishable_L805vIiSujRO505K0gYfzg_BkLXHM2h"
    ){
        install(Auth)
        install(Postgrest)
    }
}