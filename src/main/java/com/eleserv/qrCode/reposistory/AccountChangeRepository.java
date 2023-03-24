package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.AccountChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountChangeRepository extends JpaRepository<AccountChange, Integer> {

}
