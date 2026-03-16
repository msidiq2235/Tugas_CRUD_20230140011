package com.tugas.ktp.repository;

import com.tugas.ktp.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KtpRepository extends JpaRepository<Ktp, Integer> {
    // Custom query untuk mengecek apakah nomor KTP sudah ada di database
    boolean existsByNomorKtp(String nomorKtp);
}