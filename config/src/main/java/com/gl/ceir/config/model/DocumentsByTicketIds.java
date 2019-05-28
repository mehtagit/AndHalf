package com.gl.ceir.config.model;

import java.util.Set;

import javax.persistence.Entity;
import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class DocumentsByTicketIds {

	private String ticketId;

	private Set<Documents> documents;

}
