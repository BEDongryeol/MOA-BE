package com.moa.finance.repository.finance;

import com.moa.finance.vo.finance.RegistrationManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationManagement, Long> {

}
