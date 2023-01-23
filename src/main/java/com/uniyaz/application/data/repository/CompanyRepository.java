package com.uniyaz.application.data.repository;

import com.uniyaz.application.data.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
