package com.tugas.ktp.mapper;

import com.tugas.ktp.dto.KtpDto;
import com.tugas.ktp.entity.Ktp;

public class KtpMapper {

    // Mengubah DTO (dari form web) menjadi Entity (untuk disimpan ke database)
    public static Ktp toEntity(KtpDto dto) {
        Ktp ktp = new Ktp();
        ktp.setId(dto.getId());
        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());
        return ktp;
    }

    // Mengubah Entity (dari database) menjadi DTO (untuk ditampilkan ke web)
    public static KtpDto toDto(Ktp entity) {
        KtpDto dto = new KtpDto();
        dto.setId(entity.getId());
        dto.setNomorKtp(entity.getNomorKtp());
        dto.setNamaLengkap(entity.getNamaLengkap());
        dto.setAlamat(entity.getAlamat());
        dto.setTanggalLahir(entity.getTanggalLahir());
        dto.setJenisKelamin(entity.getJenisKelamin());
        return dto;
    }
}