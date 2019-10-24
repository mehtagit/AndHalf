package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.Script;

public interface SmsScriptRepository extends JpaRepository<Script, Long> {

}
