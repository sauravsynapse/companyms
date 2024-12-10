package com.jobex.companyms.companies.messaging;

import com.jobex.companyms.companies.CompanyService;
import com.jobex.companyms.companies.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage)
    {
        companyService.updateCompanyRating(reviewMessage);
    }
}
