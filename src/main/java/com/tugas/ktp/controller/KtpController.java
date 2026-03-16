package com.tugas.ktp.controller;

import com.tugas.ktp.dto.KtpDto;
import com.tugas.ktp.service.KtpService;
import com.tugas.ktp.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // SANGAT PENTING: Agar bisa diakses dari file HTML (JQuery Ajax)
@RestController
@RequestMapping("/ktp")
public class KtpController {

    @Autowired
    private KtpService ktpService;

    // POST /ktp - Tambah Data
    @PostMapping
    public ResponseEntity<ApiResponse<KtpDto>> create(@RequestBody KtpDto dto) {
        try {
            KtpDto createdKtp = ktpService.createKtp(dto);
            return ResponseEntity.ok(new ApiResponse<>("Berhasil tambah data KTP", createdKtp));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // GET /ktp - Ambil Semua Data
    @GetMapping
    public ResponseEntity<List<KtpDto>> getAll() {
        return ResponseEntity.ok(ktpService.getAllKtp());
    }

    // GET /ktp/{id} - Ambil Data by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KtpDto>> getById(@PathVariable Integer id) {
        try {
            KtpDto ktpDto = ktpService.getKtpById(id);
            return ResponseEntity.ok(new ApiResponse<>("Data ditemukan", ktpDto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // PUT /ktp/{id} - Update Data
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<KtpDto>> update(@PathVariable Integer id, @RequestBody KtpDto dto) {
        try {
            KtpDto updatedKtp = ktpService.updateKtp(id, dto);
            return ResponseEntity.ok(new ApiResponse<>("Berhasil update data KTP", updatedKtp));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // DELETE /ktp/{id} - Hapus Data
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        try {
            ktpService.deleteKtp(id);
            return ResponseEntity.ok(new ApiResponse<>("Berhasil hapus data KTP", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), null));
        }
    }
}