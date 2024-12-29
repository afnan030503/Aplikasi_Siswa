package com.example.aplikasisiswa.dependenciesinjection

import com.example.aplikasisiswa.repository.MahasiswaRepository

interface AppContainer {
    val kontakRepository: MahasiswaRepository
}