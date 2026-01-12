package com.barbosa.dscommerse.repositories;

import com.barbosa.dscommerse.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long > {
}
