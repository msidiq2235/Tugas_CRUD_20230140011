package com.tugas.ktp.service.impl;

import com.tugas.ktp.dto.KtpDto;
import com.tugas.ktp.entity.Ktp;
import com.tugas.ktp.mapper.KtpMapper;
import com.tugas.ktp.repository.KtpRepository;
import com.tugas.ktp.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Override
    public KtpDto createKtp(KtpDto dto) {
        // Validasi jika Nomor KTP sudah ada
        if (ktpRepository.existsByNomorKtp(dto.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP sudah terdaftar!");
        }
        Ktp ktp = KtpMapper.toEntity(dto);
        Ktp savedKtp = ktpRepository.save(ktp);
        return KtpMapper.toDto(savedKtp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        List<Ktp> ktpList = ktpRepository.findAll();
        return ktpList.stream()
                .map(KtpMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan"));
        return KtpMapper.toDto(ktp);
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpDto dto) {
        Ktp existingKtp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan"));

        // Update data (Nomor KTP biasanya tidak boleh diubah, jadi kita skip)
        existingKtp.setNamaLengkap(dto.getNamaLengkap());
        existingKtp.setAlamat(dto.getAlamat());
        existingKtp.setTanggalLahir(dto.getTanggalLahir());
        existingKtp.setJenisKelamin(dto.getJenisKelamin());

        Ktp updatedKtp = ktpRepository.save(existingKtp);
        return KtpMapper.toDto(updatedKtp);
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan"));
        ktpRepository.delete(ktp);
    }
}