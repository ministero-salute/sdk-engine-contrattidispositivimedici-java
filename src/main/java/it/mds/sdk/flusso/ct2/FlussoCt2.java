/* SPDX-License-Identifier: BSD-3-Clause */
 
package it.mds.sdk.flusso.ct2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"it.mds.sdk.flusso.ct2.controller","it.mds.sdk.flusso.ct2",
		"it.mds.sdk.rest.persistence.entity","it.mds.sdk.libreriaregole.validator",
		"it.mds.sdk.flusso.ct2.service","it.mds.sdk.flusso.ct2.tracciato",
		"it.mds.sdk.gestoreesiti", "it.mds.sdk.flusso.ct2.parser.regole",
		"it.mds.sdk.flusso.ct2.parser.regole.conf",
		"it.mds.sdk.connettoremds"})
@OpenAPIDefinition(info=@Info(title = "SDK Ministero Della Salute - Flusso CT2", version = "0.0.1-SNAPSHOT", description = "Flusso Contratti Dispositivi"))
public class FlussoCt2 {

	public static void main(String[] args) {
		SpringApplication.run(FlussoCt2.class, args);
	}

}
