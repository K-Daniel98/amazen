package hu.unideb.web.amazen.core.order.repository;

import hu.unideb.web.amazen.core.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
