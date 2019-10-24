package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.gl.ceir.CeirPannelCode.config.Model.NullMsisdnRegularized;

@Repository
public interface NullMsisdnRegularizedRepository extends JpaRepository<NullMsisdnRegularized, Long> {

}
