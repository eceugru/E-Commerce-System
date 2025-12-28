package com.ecenisaugu.E_Commerce.System.Repository;

import com.ecenisaugu.E_Commerce.System.Entity.Inventroy;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventroy, String> {
    Optional<Inventroy> findByProductId(String productId);
    void deleteByProductId(String productId);


}
