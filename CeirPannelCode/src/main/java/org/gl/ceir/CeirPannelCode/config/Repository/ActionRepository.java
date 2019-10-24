package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.Action;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.ActionNames;

public interface ActionRepository extends JpaRepository<Action, Long> {

	public Action findByName(ActionNames name);

}
