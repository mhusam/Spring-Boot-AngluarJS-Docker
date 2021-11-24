import { Component, OnInit } from '@angular/core';
import { Deal } from 'src/app/models/deal.model';
import { DealService } from 'src/app/services/deal.service';

@Component({
    selector: 'app-add-deal',
    templateUrl: './add-deal.component.html',
    styleUrls: ['./add-deal.component.css']
})
export class AddDealComponent implements OnInit {
    deal: Deal = {
        uuid: '',
        dealId: '',
        fromCurrency: '',
        toCurrency: '',
        dateTime: '',
        amount: 0
    };
    submitted = false;

    constructor(private dealService: DealService) { }

    ngOnInit(): void {
    }

    saveDeal(): void {
        const data = {
            uuid: this.deal.uuid,
            dealId: this.deal.dealId,
            fromCurrency: this.deal.fromCurrency,
            toCurrency: this.deal.toCurrency,
            dateTime: this.deal.dateTime,
            amount: this.deal.amount
        };

        this.dealService.create(data)
            .subscribe(
                response => {
                    console.log(response);
                    this.submitted = true;
                },
                error => {
                    console.log(error);
                });
    }

    newDeal(): void {
        this.submitted = false;
        this.deal = {
            uuid: '',
            dealId: '',
            fromCurrency: '',
            toCurrency: '',
            dateTime: '',
            amount: 0
        };
    }

}
