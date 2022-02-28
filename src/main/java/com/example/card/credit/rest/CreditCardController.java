package com.example.card.credit.rest;

import com.example.card.credit.constant.ErrorMessage;
import com.example.card.credit.model.CreditCardRequest;
import com.example.card.credit.service.impl.CreditCardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Credit Cards", description = "Endpoints accepting credit card api requests")
public class CreditCardController {

    private final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardServiceImpl creditCardService;

    /**
     * Api to add credit card to database.
     *
     * @param creditCardRequest
     * @return confirmation of credit card addition.
     */
    @PostMapping(path = "/creditCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Add a credit card.",
            description = "API to register a new credit card.",
            tags = {"Credit Cards"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCardRequest.class))
                    ),
                    @ApiResponse(description = "Unexpected Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Invalid Input", responseCode = "400", content = @Content)
            }
    )
    public ResponseEntity addCreditCard(@Valid @RequestBody CreditCardRequest creditCardRequest) {
        logger.info("Add Credit Request Received: {}", creditCardRequest);
        creditCardService.addCreditCard(creditCardRequest);
        logger.info("Credit Card Details Added");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * Retrieve list of all the credit cards saved in system.
     *
     * @return list of credit cards.
     */

    @Operation(
            summary = "Retrieve all credit cards.",
            description = "API to get all credit cards",
            tags = {"Credit Cards"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCardRequest.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content)
            }
    )
    @GetMapping(path = "/creditCard", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity getCreditCards() {
        logger.info("Get All Credits Request Received");
        List<CreditCardRequest> creditCardRequests = creditCardService.getAllCreditCards();
        if (null == creditCardRequests || creditCardRequests.isEmpty()) {
            logger.info("Not credit card found.");
            return new ResponseEntity(ErrorMessage.NO_CARDS_FOUND, HttpStatus.NOT_FOUND);
        }
        logger.info("Get All Credits Response: {}", creditCardRequests);
        return new ResponseEntity<List<CreditCardRequest>>(creditCardService.getAllCreditCards(), HttpStatus.OK);
    }
}
