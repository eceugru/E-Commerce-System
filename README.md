# 🛒 E-Commerce System (Spring Boot)

Bu proje, **Spring Boot** kullanılarak geliştirilmiş bir **E-Commerce backend sistemidir**.
Kullanıcı, ürün, sepet, sipariş ve stok yönetimi süreçlerini kapsar.

---

## 🚀 Teknolojiler

* Java 17
* Spring Boot
* Spring Security + JWT
* Spring Data JPA
* PostgreSQL
* Redis
* Maven

---

## 🔐 Authentication

* JWT tabanlı kimlik doğrulama
* Register / Login işlemleri
* `@AuthenticationPrincipal` ile kullanıcı erişimi

---

## 🛒 Cart (Redis)

* Sepet verileri Redis üzerinde tutulur
* Kullanıcıya özel sepet
* TTL ile otomatik sepet silme

---

## 📦 Inventory

* Ürün stok takibi
* Sipariş oluşturulduğunda stoktan otomatik düşüm

---

## 🧾 Order

* Sepetten sipariş oluşturma
* Sipariş durumu güncelleme
* Order state’leri:

  * `CREATED`
  * `PREPARING`
  * `SHIPPED`
  * `COMPLETED`
  * `CANCELLED`

