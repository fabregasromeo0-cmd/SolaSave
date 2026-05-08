package com.example.solasave.data.repository.weather

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

val supabase = createSupabaseClient(
    supabaseUrl = "https://dnnsoxvhaqnxpoiqnqvm.supabase.co/rest/v1/",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRubnNveHZoYXFueHBvaXFucXZtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzgwNjIyMzcsImV4cCI6MjA5MzYzODIzN30.g7NWGycrKAmspVIBcu_DdX6oG0PxdcZzpPAXK1Z3Aso"
) {
    install(Auth)
    install(Postgrest)
    install(Storage)
}
