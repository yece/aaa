/*
 * Copyright 2000-2014 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package views;

import model.Boleto;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import controllers.Dao;

@Theme("tests-valo")
@Title("Transportes")
//@PreserveOnRefresh
@Push
public class PrintBoleto extends UI {
	
    Boleto objBoleto = new Boleto();
    	
    @Override
    protected void init(VaadinRequest request) {
        // Have some content to print
    	
        final int boletoid = Integer.parseInt(request.getParameter("boletoid").trim());
        
        Dao objDao = new Dao();        
        objBoleto = (Boleto)objDao.getById(Boleto.class, boletoid);
        
        String tipoBoleto = "Boleto";
        
        if( objBoleto.getEstado() == 2 ){
        	tipoBoleto = "Reserva";	
        }                
        
        String htmlBoleto = "";
        htmlBoleto += "<table style='width:100%'>";
        	htmlBoleto += "<tr>";
				htmlBoleto += "<th>Empresa:</th><td>"+objBoleto.getCooperativaId().getNombreComercial()+"</td>";
			htmlBoleto += "</tr>";        
	        htmlBoleto += "<tr>";
	    		htmlBoleto += "<th>"+tipoBoleto+" Nro.</th><td>"+boletoid+"</td>";
	    	htmlBoleto += "</tr>";        
	        htmlBoleto += "<tr>";
	    		htmlBoleto += "<th>Cliente:</th><td>"+objBoleto.getIdCliente().getCedula()+" / "+objBoleto.getIdCliente().getNombres()+" "+objBoleto.getIdCliente().getApellidos()+"</td>";
	    	htmlBoleto += "</tr>";        	    	
			htmlBoleto += "<tr>";
				htmlBoleto += "<th>Fecha Salida:</th><td>"+objBoleto.getFechaSalida()+"</td>";
			htmlBoleto += "</tr>";			
			htmlBoleto += "<tr>";
				htmlBoleto += "<th>Vaiaja a:</th><td>"+objBoleto.getRutadestinoId().getDestino()+"</td>";
			htmlBoleto += "</tr>";
			htmlBoleto += "<tr>";
				htmlBoleto += "<th>Asientos:</th><td>"+objBoleto.getAsientos()+"</td>";
			htmlBoleto += "</tr>";	
			htmlBoleto += "<tr>";
				htmlBoleto += "<th>Bus Nro.:</th><td>"+objBoleto.getBusId().getNumero()+"</td>";
			htmlBoleto += "</tr>";			
        htmlBoleto += "</table>";
        
        setContent(new Label( htmlBoleto, ContentMode.HTML));
        
        // Print automatically when the window opens
        JavaScript.getCurrent().execute(
            "setTimeout(function() {" +
            "  print(); self.close();}, 0);");
    }

}
