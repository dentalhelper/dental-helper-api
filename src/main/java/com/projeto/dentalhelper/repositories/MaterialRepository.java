package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.repositories.Material.MaterialRepositoryQuery;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>, MaterialRepositoryQuery{

}
