package hu.unideb.web.amazen.core.item.repository;

import hu.unideb.web.amazen.core.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
