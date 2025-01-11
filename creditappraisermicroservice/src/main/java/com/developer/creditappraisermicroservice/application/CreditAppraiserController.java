package com.developer.creditappraisermicroservice.application;

import com.developer.creditappraisermicroservice.application.exception.ClientDataNotFound;
import com.developer.creditappraisermicroservice.application.exception.ConectionMicroserviceException;
import com.developer.creditappraisermicroservice.domain.model.AvaliationData;
import com.developer.creditappraisermicroservice.domain.model.ClientCard;
import com.developer.creditappraisermicroservice.domain.model.ClientSituation;
import com.developer.creditappraisermicroservice.domain.model.ReturnAvaliationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String test() {
        return HttpStatus.OK.toString();
    }

    @GetMapping(value = "/client-situation", params = "cpf")
    public ResponseEntity clientSituation(@RequestParam("cpf") String cpf) {

        try {
            ClientSituation clientSituation = creditAppraiserService.findClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (ClientDataNotFound e) {
            return ResponseEntity.notFound().build();

        } catch (ConectionMicroserviceException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity carryOutAssessment(@RequestBody AvaliationData avaliationData) {
        try {
            ReturnAvaliationClient returnAvaliationClient = creditAppraiserService.makeAvaliation(avaliationData.getCpf(), avaliationData.getIncome());
            return ResponseEntity.ok(returnAvaliationClient);

        } catch (ClientDataNotFound e) {
            return ResponseEntity.notFound().build();

        } catch (ConectionMicroserviceException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
