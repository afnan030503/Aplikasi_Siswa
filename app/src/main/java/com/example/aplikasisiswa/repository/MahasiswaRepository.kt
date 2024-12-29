package com.example.aplikasisiswa.repository

import com.example.aplikasisiswa.model.Mahasiswa
import com.example.aplikasisiswa.service.MahasiswaService
import java.io.IOException

interface MahasiswaRepository {
    abstract val response: Any

    suspend fun  getMahasiswa():List<Mahasiswa>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim: String,mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswabyNim(nim: String):Mahasiswa
}

class NetworkMahasiswaRepository(
    private val mahasiswaApiService: MahasiswaService
) : MahasiswaRepository {
    override suspend fun getMahasiswa(): List<Mahasiswa> =
        mahasiswaApiService.getAllMahasiswa()


    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        mahasiswaApiService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = mahasiswaApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful)
                throw IOException(
                    "Failed to delete Mahasiswa. PHP Status Code: " +
                            "${response.code()}"
                )
        }else{
            response.message()
            println(response.message)
        }
    }catch (e:Exception)
    {
        throw e
    }

    override suspend fun getMahasiswabyNim(nim: String): Mahasiswa {
        return mahasiswaApiService.getMahasiswabyNim(nim)
    }

}