package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.SmsAccount;

public interface SmsAccountRepository extends JpaRepository<SmsAccount, Long> {

}
