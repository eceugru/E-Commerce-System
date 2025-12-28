package com.ecenisaugu.E_Commerce.System.Enum;

public enum OrderStatus {
    CREATED,      // Order oluşturuldu (cart'tan snapshot alındı)
    PREPARING,      // Hazırlanıyor
    SHIPPED,      // Kargoya verildi
    COMPLETED,    // Teslim edildi
    CANCELLED     // İptal edildi
}
