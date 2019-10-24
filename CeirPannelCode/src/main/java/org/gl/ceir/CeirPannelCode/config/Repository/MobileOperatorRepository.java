package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.gl.ceir.CeirPannelCode.config.Model.MobileOperator;

@Repository
public interface MobileOperatorRepository extends JpaRepository<MobileOperator, Long> {
}