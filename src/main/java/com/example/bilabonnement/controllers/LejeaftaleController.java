package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.Bil;
import com.example.bilabonnement.models.Kunde;
import com.example.bilabonnement.models.Lejeaftale;
import com.example.bilabonnement.models.LejeaftaleKrav;
import com.example.bilabonnement.repository.BilRepository;
import com.example.bilabonnement.repository.KundeRepository;
import com.example.bilabonnement.repository.LejeaftaleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.Map;


@Controller
public class LejeaftaleController {

    LejeaftaleRepository lr = new LejeaftaleRepository();
    KundeRepository kr = new KundeRepository();
    BilRepository br = new BilRepository();

    @PostMapping("/opret-lejeaftale")
    public String opretLejeaftale(LejeaftaleKrav lejeaftalekrav) throws SQLException {
        String cprnummer = lejeaftalekrav.getCprnummer();
        String stelnummer = lejeaftalekrav.getStelnummer();

        System.out.println(stelnummer);
        System.out.println(cprnummer);
        Kunde kunde = kr.getKundeFromDB(cprnummer);
        Bil bil = br.getCarFromDB(stelnummer);


        Lejeaftale lejeaftale = new Lejeaftale(bil, kunde, true);
        System.out.println(lejeaftale.toString());
        lr.indsætLejeaftale(lejeaftale);
        return "redirect:/opret-lejeaftale";
    }

}
